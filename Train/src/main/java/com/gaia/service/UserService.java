package com.gaia.service;

import com.gaia.pojo.UserTable;

public interface UserService {
	
	int getDoesItExistByUserEmail(String useremail);
	
	int getDoesItExistByUserName(String username);
	
	int getDoesItExistByUserPhone(long userphone);
	
	UserTable getUserInfoByEmial(String useremail);
	
	UserTable getUserInfoByUserId(String userid);
	
	UserTable getUserInfoByUserPhone(long userphone);
	
	UserTable getUserInfoByUserName(String username);
	
	int register(UserTable record);
	
	int updatePwdByUserId(String newpassword, String userid);
	
}
