package com.olx.masterdata.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Master Status DTO")
public class Status {
	@ApiModelProperty(value = "Id")
	private int id;
	@ApiModelProperty(value = "Status Name")
	private String statusName;
	
	
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Status(int id, String statusName) {
		super();
		this.id = id;
		this.statusName = statusName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getStatusName() {
		return statusName;
	}


	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}


	@Override
	public String toString() {
		return "Status [id=" + id + ", statusName=" + statusName + "]";
	}
	
	
}
