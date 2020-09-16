package com.tampro.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ProductInfoDTO {

	private int id;
	private Integer cateId;
	private String code;
	private String name;
	private String imgUrl;
	private MultipartFile multipartFile;
	private int activeFlag;
	private Date createDate;
	private Date updateDate;
	private AuthorDTO authorDTO;
	private Integer authorId;
	private CategoryDTO categoryDTO;
	private PublisherDTO publisherDTO;
	private Integer publisherId;
	
	
	
	
	public ProductInfoDTO(int id) {
		super();
		this.id = id;
	}
	public ProductInfoDTO() {
		super();
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

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
	

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public AuthorDTO getAuthorDTO() {
		return authorDTO;
	}
	public void setAuthorDTO(AuthorDTO authorDTO) {
		this.authorDTO = authorDTO;
	}
	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}
	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}
	public PublisherDTO getPublisherDTO() {
		return publisherDTO;
	}
	public void setPublisherDTO(PublisherDTO publisherDTO) {
		this.publisherDTO = publisherDTO;
	}
	public Integer getCateId() {
		return cateId;
	}
	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public Integer getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
}
