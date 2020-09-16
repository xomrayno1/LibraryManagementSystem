package com.tampro.dto;

import java.util.Date;
import java.util.Set;

public class CallCardDTO {
	private int id;
	private LibraryCardDTO libaryCardDTO;
	private UsersDTO usersDTO;
	private Date  dateIssue; //ngay muon
	private int status; // hoàn thành , chưa hoàn thành
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	private Set<CallCardDetailDTO> cardDetailDTOs;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Set<CallCardDetailDTO> getCardDetailDTOs() {
		return cardDetailDTOs;
	}
	public void setCardDetailDTOs(Set<CallCardDetailDTO> cardDetailDTOs) {
		this.cardDetailDTOs = cardDetailDTOs;
	}
	public LibraryCardDTO getLibaryCardDTO() {
		return libaryCardDTO;
	}
	public void setLibaryCardDTO(LibraryCardDTO libaryCardDTO) {
		this.libaryCardDTO = libaryCardDTO;
	}
	public UsersDTO getUsersDTO() {
		return usersDTO;
	}
	public void setUsersDTO(UsersDTO usersDTO) {
		this.usersDTO = usersDTO;
	}
	public Date getDateIssue() {
		return dateIssue;
	}
	public void setDateIssue(Date dateIssue) {
		this.dateIssue = dateIssue;
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

}
