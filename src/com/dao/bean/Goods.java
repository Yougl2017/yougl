package com.dao.bean;
// Generated 2017-8-5 22:14:18 by Hibernate Tools 5.2.3.Final


/**
 * Goods generated by hbm2java
 */
//@Entity
//@Table(name = "Goods", schema = "dbo", catalog = "hhbl")
public class Goods implements java.io.Serializable {

	private int  goodsId;
	private String goodsName;
	private String goodsPy;
	private String goodsPrice;
	private String goodsSpecs;
	private String goodsTypeid;
	private String goodsType;
	private String goodsIsusable;
	private String goodsRemark;

	public Goods() {
	}

	public Goods(String goodsTypeid, String goodsIsusable) {
		this.goodsTypeid = goodsTypeid;
		this.goodsIsusable = goodsIsusable;
	}

	public Goods(Integer goodsId,String goodsName, String goodsPrice, String goodsSpecs, String goodsTypeid, String goodsType,
			String goodsIsusable, String goodsRemark,String goodsPy) {
		this.goodsId= goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsSpecs = goodsSpecs;
		this.goodsTypeid = goodsTypeid;
		this.goodsType = goodsType;
		this.goodsIsusable = goodsIsusable;
		this.goodsRemark = goodsRemark;
		this.goodsPy= goodsPy;
	}

	//@Id
	//@GeneratedValue(strategy = IDENTITY)

	//@Column(name = "Goods_id", unique = true, nullable = false, precision = 18, scale = 0)
	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	//@Column(name = "Goods_name", length = 80)
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	//@Column(name = "Goods_price", precision = 10)
	public String getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	//@Column(name = "Goods_specs", length = 50)
	public String getGoodsSpecs() {
		return this.goodsSpecs;
	}

	public void setGoodsSpecs(String goodsSpecs) {
		this.goodsSpecs = goodsSpecs;
	}

	//@Column(name = "Goods_typeid", nullable = false, length = 10)
	public String getGoodsTypeid() {
		return this.goodsTypeid;
	}

	public void setGoodsTypeid(String goodsTypeid) {
		this.goodsTypeid = goodsTypeid;
	}

	//@Column(name = "Goods_type", length = 50)
	public String getGoodsType() {
		return this.goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	//@Column(name = "Goods_isusable", nullable = false, length = 1)
	public String getGoodsIsusable() {
		return this.goodsIsusable;
	}

	public void setGoodsIsusable(String goodsIsusable) {
		this.goodsIsusable = goodsIsusable;
	}

	//(name = "Goods_remark", length = 100)
	public String getGoodsRemark() {
		return this.goodsRemark;
	}

	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}

	public String getGoodsPy() {
		return goodsPy;
	}

	public void setGoodsPy(String goodsPy) {
		this.goodsPy = goodsPy;
	}

}
