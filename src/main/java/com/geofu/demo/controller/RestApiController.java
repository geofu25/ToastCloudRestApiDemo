package com.geofu.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geofu.demo.service.RestService;

@RestController
@RequestMapping("/api/v1")
public class RestApiController {
	
	@Autowired
	RestService restService;
	
	/**
     * REST API 호출 - 토큰 발급
     *  
     * @param request
     * @param paramMap
     * @param response
     */
	@PostMapping(value="/getToken")
	public Map<String, Object> getToken(HttpServletRequest request, @RequestBody Map<String, Object> paramMap,  HttpServletResponse response) throws Exception {
		return restService.getToken(paramMap);
	}
	
	/**
     * REST API 호출 - VPC 목록 호출
     *  
     * @param request
     * @param paramMap
     * @param response
     */
	@PostMapping(value="/vpcs")
	public Map<String, Object> getVpcs(HttpServletRequest request, @RequestBody Map<String, Object> paramMap,  HttpServletResponse response) throws Exception {
		return restService.getToken(paramMap);
	}
	
	/**
     * REST API 호출 - 포트 목록 호출
     *  
     * @param request
     * @param paramMap
     * @param response
     */
	@PostMapping(value="/ports")
	public Map<String, Object> getPorts(HttpServletRequest request, @RequestBody Map<String, Object> paramMap,  HttpServletResponse response) throws Exception {
		return restService.getToken(paramMap);
	}
	
	/**
     * REST API 호출 - 인스턴스 목록 호출
     *  
     * @param request
     * @param paramMap
     * @param response
     */
	@PostMapping(value="/instancs")
	public Map<String, Object> getInstaces(HttpServletRequest request, @RequestBody Map<String, Object> paramMap,  HttpServletResponse response) throws Exception {
		return restService.getToken(paramMap);
	}
	
}
