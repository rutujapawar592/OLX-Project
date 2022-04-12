package com.olx.masterdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "STATUS")
public class StatusEntity {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "Status_Name")
	private String statusName;
	public StatusEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StatusEntity(int id, String statusName) {
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
		return "StatusEntity [id=" + id + ", statusName=" + statusName + "]";
	}
	
	
}
