package com.geofu.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@JsonInclude(Include.ALWAYS)
@Data
public class Flavor {

	protected String id;
	protected String disk;
	protected String name;
	protected String ram;
	protected String vcpus;
	protected List<Link> links;

}