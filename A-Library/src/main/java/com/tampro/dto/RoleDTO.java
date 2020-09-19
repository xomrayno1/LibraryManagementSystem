package com.tampro.dto;

import java.util.Date;
import java.util.Set;

public class RoleDTO {
	private Integer id;
	private Set<AuthDTO> auths;
	//private Set<UserRolesDTO> userRolesDTO;
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	private String name;
	private String description;
	private int idRole;
	
	
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	public RoleDTO(int id) {
		super();
		this.id = id;
	}
	public RoleDTO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<AuthDTO> getAuths() {
		return auths;
	}
	public void setAuths(Set<AuthDTO> auths) {
		this.auths = auths;
	}
	/*	public Set<UserRolesDTO> getUserRolesDTO() {
		return userRolesDTO;
	}
	public void setUserRolesDTO(Set<UserRolesDTO> userRolesDTO) {
		this.userRolesDTO = userRolesDTO;
	} */
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
