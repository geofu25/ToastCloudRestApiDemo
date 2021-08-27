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
			log.debug(e);
		}
		
		resultMap.put("token", tokenReponse);
		resultMap.put("code", code);
		return resultMap;
		
	}

	@Override
	public String getUrlAddress(TokenReponse tokenReponse, String type, String region) {
		// TODO Auto-generated method stub
		return null;
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

}
