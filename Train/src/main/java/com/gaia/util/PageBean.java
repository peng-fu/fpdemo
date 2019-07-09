package com.gaia.util;

import java.util.List;

/**
 * 
 * @author ilar
 * 分页的工具类
 *
 */
public class PageBean<T> {
	private Integer indexPage;//当前页
	private Integer beginRow;//开始行下标
	private Long    totalSize;//总记录数
	private Integer pageSize;//每页记录数
	private Integer totalPage;//总页数
	private List<T> datas;//保存分页的数据
	private Boolean isFirstPage;//是否是第一页
	private Boolean isLastPage;//是否是最后一页
	
	public PageBean() {
		super();
	}

	public PageBean(Long totalSize, Integer pageSize) {
		super();
		this.totalSize = totalSize;
		this.pageSize = pageSize;
	}

	public Integer getIndexPage() {
		return indexPage;
	}

	public void setIndexPage(Integer indexPage) {
		this.indexPage = indexPage;
	}
	//每页行下标可以计算出来
	public Integer getBeginRow() {
		//(页数-1)*每页显示的条数
		return (indexPage-1)*getPageSize();
	}

	public void setBeginRow(Integer beginRow) {
		this.beginRow = beginRow;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	//总页数可以计算出来
	public Integer getTotalPage() {
		return totalPage=(int) ((totalSize%pageSize==0)?(totalSize/pageSize):(totalSize/pageSize+1));
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public Boolean getIsFirstPage() {
		return indexPage == 1;
	}

	public Boolean getIsLastPage() {
		return indexPage == getTotalPage();
	}

	public void setIsFirstPage(Boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public void setIsLastPage(Boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	@Override
	public String toString() {
		return "PageBean [indexPage=" + indexPage + ", beginRow=" + beginRow + ", totalSize=" + totalSize
				+ ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", datas=" + datas + ", isFirstPage="
				+ isFirstPage + ", isLastPage=" + isLastPage + "]";
	}
		
}
