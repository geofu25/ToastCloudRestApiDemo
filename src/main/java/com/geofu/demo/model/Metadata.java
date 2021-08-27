package com.geofu.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@JsonInclude(Include.ALWAYS)
@Data
public class Metadata {

	protected String os_distro;
	protected String os_version;
	protected String description;
	protected String project_domain;
	protected String hypervisor_type;
	protected String monitoring_agent;
	protected String image_name;
	protected String volume_size;
	protected String os_architecture;
	protected String login_username;
	protected String os_type;
	protected String tc_env;

}