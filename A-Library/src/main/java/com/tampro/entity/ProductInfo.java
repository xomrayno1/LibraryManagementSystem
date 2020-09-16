package com.tampro.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	private String name;
	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private Category category;
	private String imgUrl;
	
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	@ManyToOne
	@JoinColumn(name = "authors_id",nullable = false)
	private Authors authors;
	@ManyToOne
	@JoinColumn(name = "company_id",nullable = false)
	private Publisher company;
	
	
	public ProductInfo() {
		
	}
	
	
	public ProductInfo(int id) {
		super();
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Authors getAuthors() {
		return authors;
	}
	public void setAuthors(Authors authors) {
		this.authors = authors;
	}
	public Publisher getCompany() {
		return company;
	}
	public void setCompany(Publisher company) {
		this.company = company;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
