package com.misc.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
/** PAth parameter based API version**/
	@GetMapping(value = "api/contact/v1",produces = MediaType.APPLICATION_JSON_VALUE)
	public Contact getContcat() {
		return new Contact("Tom",12345);
	}
	@GetMapping(value = "api/contact/v2",produces = MediaType.APPLICATION_JSON_VALUE)
	public ContactV2 getContcat2() {
		return new ContactV2("Tom",12345);
	}
	
	/** Query Request parameter based versioning**/
	@GetMapping(value = "api/contact",produces = MediaType.APPLICATION_JSON_VALUE,params ="version=v1")
	public Contact getContcatQueryV1() {
		return new Contact("Tom",12345);
	}
	@GetMapping(value = "api/contact",produces = MediaType.APPLICATION_JSON_VALUE,params = "version=v2")
	public ContactV2 getContcatQueryV2() {
		return new ContactV2("Tom",12345);
	}
	/** Header Request parameter based versioning**/
	@GetMapping(value = "api/contact",produces = MediaType.APPLICATION_JSON_VALUE,headers = "version=v1")
	public Contact getContcatHeaderV1() {
		return new Contact("Tom",12345);
	}
	@GetMapping(value = "api/contact",produces = MediaType.APPLICATION_JSON_VALUE,headers = "version=V2")
	public ContactV2 getContcatHeaderV2() {
		return new ContactV2("Tom",12345);
	}
	/** Content Nigotation based versioning**/
	@GetMapping(value = "api/contact",produces ="application/contact.v1+json")//old
	public Contact getContcatNigtationV1() {
		return new Contact("Tom",12345);
	}
	@GetMapping(value = "api/contact",produces ="application/contact.v2+json")//new
	public ContactV2 getContcatNigotationV2() {
		return new ContactV2("Tom",12345);
	}
	
}
	class Contact{
		private String name;
		private int mobile;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getMobile() {
			return mobile;
		}
		public void setMobile(int mobile) {
			this.mobile = mobile;
		}
		public Contact(String name, int mobile) {
			super();
			this.name = name;
			this.mobile = mobile;
		}
		public Contact() {
			super();
		}
		@Override
		public String toString() {
			return "Contact [name=" + name + ", mobile=" + mobile + "]";
		}
		
		
	}
	class ContactV2{
		private String name;
		private int mobileNumber;
		
		
		public ContactV2() {
			super();
		}


		public ContactV2(String name, int mobileNumber) {
			super();
			this.name = name;
			this.mobileNumber = mobileNumber;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public int getMobileNumber() {
			return mobileNumber;
		}


		public void setMobileNumber(int mobileNumber) {
			this.mobileNumber = mobileNumber;
		}


		@Override
		public String toString() {
			return "ContactV2 [name=" + name + ", mobileNumber=" + mobileNumber + "]";
		}
		
	}

