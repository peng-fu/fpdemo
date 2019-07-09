package com.gaia.pojo;

public class BlogTable {
    private String blogid;

    private String userid;

    private Integer blogtype;

    private String blogcategory;

    private String commentid;

    private Long releasetime;

    private Integer viewquantity;

    private Integer commentquantity;

    private Integer whethercommenttype;

    private Long timestamp;

    private Integer deleteflag;

    private String userinfojson1;

    private String userinfojson2;

    private String userinfojson3;

    private String blogtitle;

    private String blogsynopsis;

    private String blogcontent;

    public String getBlogid() {
        return blogid;
    }

    public void setBlogid(String blogid) {
        this.blogid = blogid == null ? null : blogid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getBlogtype() {
        return blogtype;
    }

    public void setBlogtype(Integer blogtype) {
        this.blogtype = blogtype;
    }

    public String getBlogcategory() {
        return blogcategory;
    }

    public void setBlogcategory(String blogcategory) {
        this.blogcategory = blogcategory == null ? null : blogcategory.trim();
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid == null ? null : commentid.trim();
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

    public Integer getWhethercommenttype() {
        return whethercommenttype;
    }

    public void setWhethercommenttype(Integer whethercommenttype) {
        this.whethercommenttype = whethercommenttype;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    public String getUserinfojson1() {
        return userinfojson1;
    }

    public void setUserinfojson1(String userinfojson1) {
        this.userinfojson1 = userinfojson1 == null ? null : userinfojson1.trim();
    }

    public String getUserinfojson2() {
        return userinfojson2;
    }

    public void setUserinfojson2(String userinfojson2) {
        this.userinfojson2 = userinfojson2 == null ? null : userinfojson2.trim();
    }

    public String getUserinfojson3() {
        return userinfojson3;
    }

    public void setUserinfojson3(String userinfojson3) {
        this.userinfojson3 = userinfojson3 == null ? null : userinfojson3.trim();
    }

    public String getBlogtitle() {
        return blogtitle;
    }

    public void setBlogtitle(String blogtitle) {
        this.blogtitle = blogtitle == null ? null : blogtitle.trim();
    }

    public String getBlogsynopsis() {
        return blogsynopsis;
    }

    public void setBlogsynopsis(String blogsynopsis) {
        this.blogsynopsis = blogsynopsis == null ? null : blogsynopsis.trim();
    }

    public String getBlogcontent() {
        return blogcontent;
    }

    public void setBlogcontent(String blogcontent) {
        this.blogcontent = blogcontent == null ? null : blogcontent.trim();
    }
}