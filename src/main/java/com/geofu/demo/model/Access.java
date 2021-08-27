package com.geofu.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.ALWAYS)
@Data
public class Access {
	
	protected Token 	token;
	protected List<ServiceCatalog> 	serviceCatalog;
	protected User 	user;
	protected Metadata 	metadata;
 
}
