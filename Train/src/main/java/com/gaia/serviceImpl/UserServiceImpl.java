package com.gaia.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaia.dao.UserTableMapper;
import com.gaia.pojo.UserTable;
import com.gaia.service.UserService;
import com.gaia.util.PublicUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserTableMapper m_mapper;
	
	public int getDoesItExistByUserEmail(String useremail) {
		return m_mapper.getDoesItExistByUserEmail(useremail);
	}
	
	public int getDoesItExistByUserName(String username) {
		return m_mapper.getDoesItExistByUserName(username);
	}
	
	public int getDoesItExistByUserPhone(long userphone) {
		return m_mapper.getDoesItExistByUserPhone(userphone);
	}
	
	public UserTable getUserInfoByEmial(String useremial) {
		return m_mapper.getUserInfoByEmial(useremial);
	}
	
	public UserTable getUserInfoByUserId(String userid) {
		return m_mapper.getUserInfoByUserId(userid);
	}
	
	public UserTable getUserInfoByUserPhone(long userphone) {
		return m_mapper.getUserInfoByUserPhone(userphone);
	}
	
	public UserTable getUserInfoByUserName(String username) {
		return m_mapper.getUserInfoByUserName(username);
	}
	
	public int register(UserTable record) {
		record.setUserid(PublicUtil.getUUID());
		record.setUserregisttime(PublicUtil.getBJCurrentTimeMillis());
		record.setTimestamp(PublicUtil.getBJCurrentTimeMillis());
		record.setUserjurisdiction(0);
		record.setUserlock(0);
		record.setDeleteflag(0);
		return m_mapper.insert(record);
	}
	
	public int updatePwdByUserId(String newpassword, String userid) {
		return m_mapper.updatePwdByUserId(newpassword, userid);
	}

}
