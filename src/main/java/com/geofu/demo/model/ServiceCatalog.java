package com.geofu.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.ALWAYS)
@Data
public class ServiceCatalog {
	
	protected List<Endpoint> 	endpoints;
	protected String 	type;
	protected String 	name;
 
}
