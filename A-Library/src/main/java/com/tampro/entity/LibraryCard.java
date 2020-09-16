package com.tampro.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class LibraryCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date startDay;
	private Date endDay;
	private String description;
	@OneToOne
	@JoinColumn(name = "readers_id")
	private Readers readers;
	@OneToMany(mappedBy = "libaryCard")
	private Set<CallCard> callCards;
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	private int status;
	

	public LibraryCard() {
		
	}

	public LibraryCard(int id) {
		
		this.id = id;
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

	

	public Set<CallCard> getCallCards() {
		return callCards;
	}

	public void setCallCards(Set<CallCard> callCards) {
		this.callCards = callCards;
	}

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

	public Readers getReaders() {
		return readers;
	}

	public void setReaders(Readers readers) {
		this.readers = readers;
	}

	
	
	
	
}
