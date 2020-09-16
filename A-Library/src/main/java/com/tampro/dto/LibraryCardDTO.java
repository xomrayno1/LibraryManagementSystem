package com.tampro.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LibraryCardDTO {
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDay;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endDay;
	private String description;
	private ReadersDTO readersDTO;
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	private int status ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDay() {
		return startDay;
	}
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
	public Date getEndDay() {
		return endDay;
	}
	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ReadersDTO getReadersDTO() {
		return readersDTO;
	}
	public void setReadersDTO(ReadersDTO readersDTO) {
		this.readersDTO = readersDTO;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
}
