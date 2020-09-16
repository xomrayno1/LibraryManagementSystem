package com.tampro.dto;

public class Paging {
	private int totalPages;
	private int offSet;
	private long totalRows;
	private int recordPerPage;
	private int indexPage;
	
	
	
	
	public Paging(int recordPerPage) {
		
		this.recordPerPage = recordPerPage;
	}
	public int getTotalPages() {
		this.totalPages = (int) Math.ceil(totalRows / (double) recordPerPage);
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getOffSet() {
		if(indexPage > 0) {
			this.offSet = indexPage * recordPerPage - recordPerPage; // index = 1 2 3
		}															//			0 10 20
		return offSet;
	}
	public void setOffSet(int offSet) {
		
		this.offSet = offSet;
	}
	public long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}
	public int getRecordPerPage() {
		return recordPerPage;
	}
	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}
	public int getIndexPage() {
		return indexPage;
	}
	public void setIndexPage(int indexPage) {
		this.indexPage = indexPage;
	}
	
	
	
	
	

}
