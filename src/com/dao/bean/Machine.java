package com.dao.bean;
// Generated 2018-5-7 22:48:56 by Hibernate Tools 5.2.3.Final


/**
 * Machine generated by hbm2java
 */

public class Machine  {

	private Integer id;
	private String name;
	private double price;
	private String type;

	public Machine() {
	}

	public Machine(Integer id,String name, double price, String type) {
		this.id=id;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
