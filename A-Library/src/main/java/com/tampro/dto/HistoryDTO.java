package com.tampro.dto;

import java.util.Date;

public class HistoryDTO {
	private int id;
	private String actionName;
	private UsersDTO usersDTO;
	private ProductInfoDTO productInfoDTO;
	private int quantity;
	private int type; //1. xuất hàng  ,2.nhập hàng , 3.mượn , 4.trả
					//Anh a vừa xuất hàng , Anh a vừa nhập hàng, Anh b vừa tạo phiếu mượn , anh b vừa trả sách tên anh abc
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public UsersDTO getUsersDTO() {
		return usersDTO;
	}
	public void setUsersDTO(UsersDTO usersDTO) {
		this.usersDTO = usersDTO;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public ProductInfoDTO getProductInfoDTO() {
		return productInfoDTO;
	}
	public void setProductInfoDTO(ProductInfoDTO productInfoDTO) {
		this.productInfoDTO = productInfoDTO;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	
	
}
