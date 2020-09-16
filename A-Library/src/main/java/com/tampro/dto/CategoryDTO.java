package com.tampro.dto;

import java.util.Date;
import java.util.Set;

public class CategoryDTO {
	private Integer id;
	private String name;
	private String code;
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	private Set<ProductInfoDTO> productsDTOs;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Set<ProductInfoDTO> getProductsDTOs() {
		return productsDTOs;
	}
	public void setProductsDTOs(Set<ProductInfoDTO> productsDTOs) {
		this.productsDTOs = productsDTOs;
	}
	
	
	
}
