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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

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
	@ApiOperation(value = "토큰 생성", notes = "사용자 정보를 입력 받아 토큰 반환")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "url", value = "토큰 url", required = true),
		@ApiImplicitParam(name = "username", value = "사용자 아이디", required = true),
		@ApiImplicitParam(name = "password", value = "사용자 비밀번호", required = true),
		@ApiImplicitParam(name = "tenantId", value = "Tenant ID", required = true)
	})
	public Map<String, Object> getToken(HttpServletRequest request, @RequestBody Map<String, Object> paramMap,  HttpServletResponse response) throws Exception {
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
	@ApiOperation(value = "인스턴스 목록", notes = "인스턴스 목록 정보 반환")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "url", value = "토큰 url", required = true),
		@ApiImplicitParam(name = "username", value = "사용자 아이디", required = true),
		@ApiImplicitParam(name = "password", value = "사용자 비밀번호", required = true),
		@ApiImplicitParam(name = "tenantId", value = "Tenant ID", required = true),
		@ApiImplicitParam(name = "regions", value = "Region 정보", required = true)
	})
	public Map<String, Object> getInstaces(HttpServletRequest request, @RequestBody Map<String, Object> paramMap,  HttpServletResponse response) throws Exception {
		return restService.getInstance(paramMap);
	}
	
}
