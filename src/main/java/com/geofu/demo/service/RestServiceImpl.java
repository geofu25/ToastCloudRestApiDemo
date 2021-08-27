package com.geofu.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.geofu.demo.model.Endpoint;
import com.geofu.demo.model.HostResponse;
import com.geofu.demo.model.ServiceCatalog;
import com.geofu.demo.model.TokenReponse;
import com.geofu.demo.model.TokenRequest;
import com.google.gson.Gson;

@Service
public class RestServiceImpl implements RestService {
	
	private static final Logger log = LogManager.getLogger(RestServiceImpl.class);

	private TokenRequest initialize(String tenantId, String username, String password)  {     

    	TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.getAuth().setTenantId(tenantId);
        tokenRequest.getAuth().getPasswordCredentials().setUsername(username);
        tokenRequest.getAuth().getPasswordCredentials().setPassword(password);

        return tokenRequest;
    }
	
	@Override
	public Map<String, Object> getToken(Map<String, Object> map) {
		
		Map<String, Object> resultMap = new HashMap<>();
		String code = "9999";
		Gson gsonObj = new Gson();
		TokenReponse tokenReponse = null;

		String tenantId = "";
	    String username = "";
	    String password = "";
		
		try {
			
			if(map == null || map.isEmpty()) {
				code = "9006";
			
			}else {
				tenantId = (String) map.get("tenantId");
				username = (String) map.get("username");
				password = (String) map.get("password");
				
				if(StringUtils.isEmpty(tenantId) 
					|| StringUtils.isEmpty(username) 
					|| StringUtils.isEmpty(password)) {

					code = "9006";
				}else {
					TokenRequest tokenRequest = initialize(tenantId, username, password);
				    String response = requestToken(tokenRequest, (String) map.get("url"));
				    tokenReponse = gsonObj.fromJson(response, TokenReponse.class);
					code = "0000";
				}
			}
			
		}catch (Exception e) {
			code = "9999";
			log.debug("[Error] getToken error has occured" + e);
		}
		
		resultMap.put("token", tokenReponse);
		resultMap.put("code", code);
		return resultMap;
		
	}

	@Override
	public String getUrlAddress(TokenReponse tokenReponse, String type, String region) {
		boolean result = false;
		String address = "";
		for(ServiceCatalog serviceCatalog : tokenReponse.getAccess().getServiceCatalog()) {
			if(result) break;
			if(type.equals(serviceCatalog.getType())) {
				for(Endpoint endpoint : serviceCatalog.getEndpoints()) {
					if(region.equals(endpoint.getRegion())) {
						address = endpoint.getPublicURL();
						result = true;
						break;
					}
				}
			}
		}
		return address;
	}
	
	@Override
	public Map<String, Object> getInstance(Map<String, Object> map) {
		
		Map<String, Object> ret_map = new HashMap<String, Object>();
		Map<String, Object> token_map = new HashMap<String, Object>();
		
		String code = "9999";
		
		Gson gsonObj = new Gson();
		
		TokenReponse response_token = null;
		HostResponse response_host = null;
		
		try {
			
			token_map = getToken(map);
			response_token = (TokenReponse) token_map.get("token");
			String address = getUrlAddress(response_token, "compute", (String) map.get("regions"));
			
			String ret = request(address+"/servers/detail", response_token.getAccess().getToken().getId());
			response_host = gsonObj.fromJson(ret, HostResponse.class);
			code = "0000";
			
		}catch(Exception e) {
			code = "9999";
			log.debug("[Error] getInstance error has occured" + e);
		}
		
		ret_map.put("code", code);
		ret_map.put("instance", response_host);
		
		return ret_map;
	}
	
	private String requestToken(TokenRequest tokenRequest, String url) {
        String identityUrl = url + "/tokens";

        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<TokenRequest> httpEntity
            = new HttpEntity<TokenRequest>(tokenRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        
        // 토큰 요청
        ResponseEntity<String> response
            = restTemplate.exchange(identityUrl, HttpMethod.POST, httpEntity, String.class);
        
        return response.getBody();
    }

	
    private String request(String address, String token) {
        
        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", token);
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");

        HttpEntity<Object> httpEntity= new HttpEntity<Object>(headers);
        RestTemplate restTemplate = new RestTemplate();
        
        // 토큰 요청
        ResponseEntity<String> response
            = restTemplate.exchange(address, HttpMethod.GET, httpEntity, String.class);

        return response.getBody();
    }

}
