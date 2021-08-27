package com.geofu.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.ALWAYS)
@Data
public class User {
	
	protected String 	id;
	protected String 	username;
	protected String 	name;
	protected List<Role> 			roles;
	protected List<RolesLink> 	roles_links;
 
}
