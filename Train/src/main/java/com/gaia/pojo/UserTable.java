package com.gaia.pojo;

import java.io.Serializable;

public class UserTable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userid;

    private String username;

    private Long userphone;

    private String useremail;

    private String userpassword;

    private Integer usersex;

    private String usericon;

    private Integer userjurisdiction;

    private Integer userlock;

    private Long userregisttime;

    private Long timestamp;

    private Integer deleteflag;

    private String userinfojson1;

    private String userinfojson2;

    private String userinfojson3;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Long getUserphone() {
        return userphone;
    }

    public void setUserphone(Long userphone) {
        this.userphone = userphone;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail == null ? null : useremail.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public Integer getUsersex() {
        return usersex;
    }

    public void setUsersex(Integer usersex) {
        this.usersex = usersex;
    }

    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon == null ? null : usericon.trim();
    }

    public Integer getUserjurisdiction() {
        return userjurisdiction;
    }

    public void setUserjurisdiction(Integer userjurisdiction) {
        this.userjurisdiction = userjurisdiction;
    }

    public Integer getUserlock() {
        return userlock;
    }

    public void setUserlock(Integer userlock) {
        this.userlock = userlock;
    }

    public Long getUserregisttime() {
        return userregisttime;
    }

    public void setUserregisttime(Long userregisttime) {
        this.userregisttime = userregisttime;
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

	@Override
	public String toString() {
		return "UserTable [userid=" + userid + ", username=" + username + ", userphone=" + userphone + ", useremail="
				+ useremail + ", userpassword=" + userpassword + ", usersex=" + usersex + ", usericon=" + usericon
				+ ", userjurisdiction=" + userjurisdiction + ", userlock=" + userlock + ", userregisttime="
				+ userregisttime + ", timestamp=" + timestamp + ", deleteflag=" + deleteflag + ", userinfojson1="
				+ userinfojson1 + ", userinfojson2=" + userinfojson2 + ", userinfojson3=" + userinfojson3 + "]";
	}
    
    
    
}