package com.olx.advertise.service;

import org.springframework.stereotype.Service;


public interface MasterDataDelegate {
	
	public String getCategoryDescription(int CateId,  String authToken);
	public String getStatusName(int StatusId,  String authToken);
}
