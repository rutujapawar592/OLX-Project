package com.olx.advertise.service;




import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MasterDataDelegateImpl  implements MasterDataDelegate {

	@Autowired
	LoginServiceDelegate loginServiceDelegate;
	@Autowired
	RestTemplate restTemplate;
	@Override
	public String getCategoryDescription(int CateId, String authToken) {
		// TODO Auto-generated method stub
		
		if(loginServiceDelegate.isTokenValid(authToken)) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);	
			HttpEntity entity = new HttpEntity(headers);
			ResponseEntity<String> response = 
					restTemplate.exchange("http://API-SERVICE/olx/materData/CategoryId/" + CateId, HttpMethod.GET, entity, String.class);
			return response.getBody();
			
		}
		return null;
	}
	@Override
	public String getStatusName(int StatusId, String authToken) {
		// TODO Auto-generated method stub
		if(loginServiceDelegate.isTokenValid(authToken)) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);	
			HttpEntity entity = new HttpEntity(headers);
			ResponseEntity<String> response = 
					restTemplate.exchange("http://API-SERVICE/olx/materData/StatusId/" + StatusId, HttpMethod.GET, entity, String.class);
			return response.getBody();
			
		}
		return null;
	}
	
}
