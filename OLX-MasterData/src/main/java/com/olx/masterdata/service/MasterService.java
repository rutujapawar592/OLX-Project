package com.olx.masterdata.service;

import java.util.List;

import com.olx.masterdata.dto.Category;
import com.olx.masterdata.dto.Status;

public interface MasterService {
	public abstract List<Category> getAllCategory();
	public abstract List<Status> getAllStatus();
	public String getCategoryDescription(int CateId);
	public String getStatusName(int StatusId);
}
