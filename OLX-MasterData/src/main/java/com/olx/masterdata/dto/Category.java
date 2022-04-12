package com.olx.masterdata.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Master Category DTO")
public class Category {
	@ApiModelProperty(value = "Id")
	private int id;
	@ApiModelProperty(value = "Category Name")
	private String Categoryname;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int id, String categoryname) {
		super();
		this.id = id;
		Categoryname = categoryname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryname() {
		return Categoryname;
	}
	public void setCategoryname(String categoryname) {
		Categoryname = categoryname;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", Categoryname=" + Categoryname + "]";
	}
	
	
}
