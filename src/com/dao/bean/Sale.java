package com.dao.bean;
// Generated 2017-9-2 18:43:59 by Hibernate Tools 5.2.3.Final

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Sale generated by hbm2java
 */

public class Sale implements java.io.Serializable {

	private Serializable lsh;
	private String sgs;
	private String name;
	private String sphone;
	private String address;
	private String zffs;
	private String jszt;
	private Date jssj;
	private Date dhsj;
	private BigDecimal zje;
	private BigDecimal srje;
	private BigDecimal yhje;
	private String czyh;
	private String tlsh;
	private String bz;

	public Sale() {
	}

	public Sale(Serializable lsh, String name, String sphone) {
		this.lsh = lsh;
		this.name = name;
		this.sphone = sphone;
	}

	public Sale(Serializable lsh, String sgs, String name, String sphone, String address, String zffs, String jszt,
			Date jssj, Date dhsj, BigDecimal zje, BigDecimal srje, BigDecimal yhje, String czyh, String tlsh,
			String bz) {
		this.lsh = lsh;
		this.sgs = sgs;
		this.name = name;
		this.sphone = sphone;
		this.address = address;
		this.zffs = zffs;
		this.jszt = jszt;
		this.jssj = jssj;
		this.dhsj = dhsj;
		this.zje = zje;
		this.srje = srje;
		this.yhje = yhje;
		this.czyh = czyh;
		this.tlsh = tlsh;
		this.bz = bz;
	}


	public Serializable getLsh() {
		return this.lsh;
	}

	public void setLsh(Serializable lsh) {
		this.lsh = lsh;
	}


	public String getSgs() {
		return this.sgs;
	}

	public void setSgs(String sgs) {
		this.sgs = sgs;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSphone() {
		return this.sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZffs() {
		return this.zffs;
	}

	public void setZffs(String zffs) {
		this.zffs = zffs;
	}


	public String getJszt() {
		return this.jszt;
	}

	public void setJszt(String jszt) {
		this.jszt = jszt;
	}


	public Date getJssj() {
		return this.jssj;
	}

	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}


	public Date getDhsj() {
		return this.dhsj;
	}

	public void setDhsj(Date dhsj) {
		this.dhsj = dhsj;
	}

	public BigDecimal getZje() {
		return this.zje;
	}

	public void setZje(BigDecimal zje) {
		this.zje = zje;
	}


	public BigDecimal getSrje() {
		return this.srje;
	}

	public void setSrje(BigDecimal srje) {
		this.srje = srje;
	}


	public BigDecimal getYhje() {
		return this.yhje;
	}

	public void setYhje(BigDecimal yhje) {
		this.yhje = yhje;
	}


	public String getCzyh() {
		return this.czyh;
	}

	public void setCzyh(String czyh) {
		this.czyh = czyh;
	}


	public String getTlsh() {
		return this.tlsh;
	}

	public void setTlsh(String tlsh) {
		this.tlsh = tlsh;
	}


	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}