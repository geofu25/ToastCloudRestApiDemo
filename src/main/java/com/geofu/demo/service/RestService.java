package com.geofu.demo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.geofu.demo.model.TokenReponse;

@Service
public interface RestService {
	
	public Map<String, Object> getToken(Map<String, Object> map);
	
	public String getUrlAddress(TokenReponse tokenReponse, String type, String region);
	
	public Map<String, Object> getInstance(Map<String, Object> map);
}
