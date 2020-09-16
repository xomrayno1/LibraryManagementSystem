package com.tampro.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CallCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "libaryCard_id")
	private LibraryCard libaryCard;
	@ManyToOne
	@JoinColumn(name = "users_id")
	private Users users;
	private Date  dateIssue; //ngay muon
	private int status; // hoàn thành , chưa hoàn thành
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	@OneToMany(mappedBy = "callCard")
	private Set<CallCardDetail> callCardDetails;
	
	
	
	public CallCard() {
		
	}
	public CallCard(int id) {
		
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LibraryCard getLibaryCard() {
		return libaryCard;
	}
	public void setLibaryCard(LibraryCard libaryCard) {
		this.libaryCard = libaryCard;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
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
	public Set<CallCardDetail> getCallCardDetails() {
		return callCardDetails;
	}
	public void setCallCardDetails(Set<CallCardDetail> callCardDetails) {
		this.callCardDetails = callCardDetails;
	}

}
