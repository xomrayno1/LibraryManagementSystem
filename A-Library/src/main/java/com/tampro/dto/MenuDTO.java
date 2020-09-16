package com.tampro.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MenuDTO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String url;
	private int orderIndex;
	private int parentId;
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	private List<MenuDTO> child;
	private String idMenu;
	private Map<Integer,Integer> mapAuth;
	//private Set<AuthDTO> auths;
	
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
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
	public List<MenuDTO> getChild() {
		return child;
	}
	public void setChild(List<MenuDTO> child) {
		this.child = child;
	}
	public String getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(String idMenu) {
		this.idMenu = idMenu;
	}
	public Map<Integer, Integer> getMapAuth() {
		return mapAuth;
	}
	public void setMapAuth(Map<Integer, Integer> mapAuth) {
		this.mapAuth = mapAuth;
	}
	
	
	
	
	
}
