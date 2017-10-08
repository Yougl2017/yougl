package com.dao.bean;
// Generated 2017-9-2 18:40:30 by Hibernate Tools 5.2.3.Final



/**
 * Company generated by hbm2java
 */

public class Company implements java.io.Serializable {

	private String companyId;
	private String companyName;
	private String companyAddress;
	private String companyContacts;
	private String companyPhone;
	private String companyRamark;

	public Company() {
	}

	public Company(String companyId, String companyName, String companyContacts, String companyPhone) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyContacts = companyContacts;
		this.companyPhone = companyPhone;
	}

	public Company(String companyId, String companyName, String companyAddress, String companyContacts,
			String companyPhone, String companyRamark) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyContacts = companyContacts;
		this.companyPhone = companyPhone;
		this.companyRamark = companyRamark;
	}



	
	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}


	public String getCompanyContacts() {
		return this.companyContacts;
	}

	public void setCompanyContacts(String companyContacts) {
		this.companyContacts = companyContacts;
	}


	public String getCompanyPhone() {
		return this.companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyRamark() {
		return this.companyRamark;
	}

	public void setCompanyRamark(String companyRamark) {
		this.companyRamark = companyRamark;
	}

}
