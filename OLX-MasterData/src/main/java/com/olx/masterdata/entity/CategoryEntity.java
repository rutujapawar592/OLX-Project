package com.olx.masterdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class CategoryEntity {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "Category_Name")
	private String Categoryname;
	
	public CategoryEntity(){
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryEntity(int id, String categoryname) {
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
