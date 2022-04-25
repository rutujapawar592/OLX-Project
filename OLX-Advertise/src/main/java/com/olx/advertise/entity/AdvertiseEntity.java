package com.olx.advertise.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ADVERTISE")
public class AdvertiseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String title;
	private String description;
	private double price;
	private int category;
	@Column(name="category_name")
	private String categoryName;
	@Column(name="created_date")
	private LocalDate createdDate;
	@Column(name="modified_date")
	private LocalDate modifiedDate;
	@Column(name="username")
	private String username;
	@Column(name="status_Id")
	private int statusId;
	@Column(name="status_Name")
	private String statusName;
	
	public AdvertiseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdvertiseEntity(int id, String title, String description, double price, String categoryName,
			LocalDate createdDate, LocalDate modifiedDate, String username, int statusId, String statusName) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.categoryName = categoryName;
		this.createdDate = LocalDate.now();
		this.modifiedDate = LocalDate.now();
		this.statusId = statusId;
		this.statusName = statusName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
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
		return "AdvertiseEntity [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", categoryName=" + categoryName + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", statusName=" + statusName + ", username=" + username + "]";
	}
	
}
