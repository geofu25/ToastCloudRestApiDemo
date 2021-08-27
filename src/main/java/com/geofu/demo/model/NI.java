package com.geofu.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@JsonInclude(Include.ALWAYS)
@Data
public class NI {

	@SerializedName("OS-EXT-IPS-MAC:mac_addr")
	protected String mac_address;
	protected String addr;
	
	@SerializedName("OS-EXT-IPS:type")
	protected String ip_type;
	
	protected int version;

}