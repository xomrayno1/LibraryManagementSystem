package com.tampro.dto;

import java.util.Date;
import java.util.Set;

public class UsersDTO {

	private int id;
	private String username;
	private String password;
	private String cmnd;
	private String sdt;
	private String fullName;
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	private Set<UserRolesDTO>  userRolesDTOs;
	private Set<InvoiceDTO> invoiceDTOs;
	private int idRoles;
	
	public int getIdRoles() {
		return idRoles;
	}
	public void setIdRoles(int idRoles) {
		this.idRoles = idRoles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	public Set<UserRolesDTO> getUserRolesDTOs() {
		return userRolesDTOs;
	}
	public void setUserRolesDTOs(Set<UserRolesDTO> userRolesDTOs) {
		this.userRolesDTOs = userRolesDTOs;
	}
	public Set<InvoiceDTO> getInvoiceDTOs() {
		return invoiceDTOs;
	}
	public void setInvoiceDTOs(Set<InvoiceDTO> invoiceDTOs) {
		this.invoiceDTOs = invoiceDTOs;
	}

	
	
	
	
}
