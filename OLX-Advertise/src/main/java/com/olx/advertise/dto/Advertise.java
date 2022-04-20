package com.olx.advertise.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor

@ApiModel(value = "Advertise DTO")
public class Advertise {

	@ApiModelProperty(value = "Id")
	private int Id;
	@ApiModelProperty(value = "Title")
	private String title;
	@ApiModelProperty(value = "description")
	private String description;
	@ApiModelProperty(value = "price")
	private double price;
	@ApiModelProperty(value = "category")
	private int category;
	@ApiModelProperty(value = "categoryName")
	private String categoryName;
	@ApiModelProperty(value = "createdDate")
	private LocalDate createdDate;
	@ApiModelProperty(value = "modifiedDate")
	private LocalDate modifiedDate;
	@ApiModelProperty(value = "active")
	private String active;
	@ApiModelProperty(value = "username")
	private String username;
	private int statusId;
	private String statusName;

	public Advertise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Advertise(int Id, String title, String description, double price, int category, int statusId,
			String username) {
		super();
		this.Id = Id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.createdDate = LocalDate.now();
		this.modifiedDate = LocalDate.now();
		this.statusId = statusId;
		this.username = username;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = LocalDate.now();
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = LocalDate.now();
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "Advertise [Id=" + Id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", categoryName=" + categoryName + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", statusName=" + statusName + ", username=" + username + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Advertise advertiseDto = (Advertise) obj;
		if (this.title.equals(advertiseDto.getTitle()))
			return true;
		return false;
	}

}
