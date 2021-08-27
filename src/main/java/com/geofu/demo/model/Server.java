package com.geofu.demo.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@JsonInclude(Include.ALWAYS)
@Data
public class Server {

	@SerializedName("OS-EXT-STS:task_state")
	protected boolean os_ext_sts_task_state;
	
	@SerializedName("addresses")
	protected Object addresses;
	
	protected List<Link> links;
	protected Image image;
	
	@SerializedName("OS-EXT-STS:vm_state")
	protected String os_ext_sts_vm_state;
	
	@SerializedName("OS-EXT-USG:launched_at")
	protected String launched_at;
	
	protected Flavor flavor;
	
	protected String id;
	protected String user_id;
	protected String name;
	protected String tenant_id;
	
	@SerializedName("OS-DCF:diskConfig")
	protected String os_dcf_disk_config;
	
	protected String accessIPv4;
	protected String accessIPv6;
	
	@SerializedName("OS-EXT-STS:power_state")
	protected String os_ext_sts_power_state;
	
	@SerializedName("OS-EXT-AZ:availability_zone")
	protected String os_ext_az;
	
	protected String config_drive;
	protected String status;
	
	protected String host_id;
	
	@SerializedName("OS-SRV-USG:terminated_at")
	protected String os_srv_usg_terminated_at;
	
	protected String key_name;
	
	protected Metadata metadata;
	
	protected Date 	created_at;
	protected Date 	updated_at;
	
}