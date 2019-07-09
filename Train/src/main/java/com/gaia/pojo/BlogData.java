package com.gaia.pojo;

public class BlogData{
	
	private String blogid;
	
	private String blogcategory;
	 
	private Long releasetime;

	private Integer viewquantity;

	private Integer commentquantity;
	
	private String blogtitle;

    private String blogsynopsis;

    private String blogcontent;

	private String username;

	public String getBlogid() {
		return blogid;
	}

	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}

	public String getBlogcategory() {
		return blogcategory;
	}

	public void setBlogcategory(String blogcategory) {
		this.blogcategory = blogcategory;
	}

	public Long getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(Long releasetime) {
		this.releasetime = releasetime;
	}

	public Integer getViewquantity() {
		return viewquantity;
	}

	public void setViewquantity(Integer viewquantity) {
		this.viewquantity = viewquantity;
	}

	public Integer getCommentquantity() {
		return commentquantity;
	}

	public void setCommentquantity(Integer commentquantity) {
		this.commentquantity = commentquantity;
	}

	public String getBlogtitle() {
		return blogtitle;
	}

	public void setBlogtitle(String blogtitle) {
		this.blogtitle = blogtitle;
	}

	public String getBlogsynopsis() {
		return blogsynopsis;
	}

	public void setBlogsynopsis(String blogsynopsis) {
		this.blogsynopsis = blogsynopsis;
	}

	public String getBlogcontent() {
		return blogcontent;
	}

	public void setBlogcontent(String blogcontent) {
		this.blogcontent = blogcontent;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
