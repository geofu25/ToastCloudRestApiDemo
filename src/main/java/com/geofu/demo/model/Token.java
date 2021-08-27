package com.geofu.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.ALWAYS)
@Data
public class Token {
	
	protected String 	id;
	protected String 	expires;
	protected Tenant 	tenant;
	protected String 	issued_at;
	
 
}
