package com.geofu.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.ALWAYS)
@Data
public class Tenant {
	
	protected String 	id;
	protected String 	name;
	protected String 	groupId;
	protected String 	description;
	protected boolean 	enabled;
	protected String 	project_domain;
	
 
}
