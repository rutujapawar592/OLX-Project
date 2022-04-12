package com.olx.advertise.service;

import java.time.LocalDate;
import java.util.List;

import com.olx.advertise.dto.Advertise;

public interface AdvertiseService {
	public Advertise postAdvertise(Advertise Advertise);
	public Advertise updateAdvertise(Advertise Advertise);
	public List<Advertise> getAllAdvertiseByUser();
	public List<Advertise> getAdvertiseByUser();
	public List<Advertise> filterAdvertise(String searchText, Integer categoryId, String postedBy,String dateCondition, LocalDate onDate,LocalDate fromDate,
			LocalDate toDate, String sortedBy, int startIndex, int records);
	public Advertise SearchAdvertiseByText(String searchText);
	public Advertise returnAdvertise(int id);
	

}
