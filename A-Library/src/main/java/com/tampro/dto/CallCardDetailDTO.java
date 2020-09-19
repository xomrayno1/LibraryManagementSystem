package com.tampro.dto;

import java.util.Date;

public class CallCardDetailDTO {
	private int id;
	private int callCardId;
	private ProductInfoDTO productInfoDTO;
	private Date returnDate; // ngayTra
	private Date dueDate;
	private int status; // đang mượn , đã trả
	private LibraryCardDTO libraryCardDTO;
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	
	private Date fromDate ;
	private Date toDate;
	
	
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCallCardId() {
		return callCardId;
	}
	public void setCallCardId(int callCardId) {
		this.callCardId = callCardId;
	}
	
	public ProductInfoDTO getProductInfoDTO() {
		return productInfoDTO;
	}
	public void setProductInfoDTO(ProductInfoDTO productInfoDTO) {
		this.productInfoDTO = productInfoDTO;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public LibraryCardDTO getLibraryCardDTO() {
		return libraryCardDTO;
	}
	public void setLibraryCardDTO(LibraryCardDTO libraryCardDTO) {
		this.libraryCardDTO = libraryCardDTO;
	}
	
	
}
