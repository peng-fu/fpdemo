package com.gaia.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.pojo.UserTable;
import com.gaia.service.UserService;
import com.gaia.util.AjaxResult;
import com.gaia.util.PublicData;
import com.gaia.util.PublicUtil;


/**
 * 用户相关 
 */

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger m_logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService m_service;
	
	/**
	 * 忘记密码
	 * @param request
	 * @param userphone
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/forgetpwd", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult forgetPwd(HttpServletRequest request, UserTable user) {
		if(user.getUserphone() == null) {
			m_logger.debug("忘记密码-手机号码为空");
			return AjaxResult.fail(301, "手机号码为空");
		}
		if(!PublicUtil.isEmpty(user.getUserpassword())) {
			m_logger.debug("忘记密码-密码为空");
			return AjaxResult.fail(302, "密码为空");
		}
		UserTable userinfo = m_service.getUserInfoByUserPhone(user.getUserphone());
		if(userinfo == null) {
			m_logger.debug("忘记密码-用户不存在---userphone" + user.getUserphone());
			return AjaxResult.fail(304, "用户不存在");
		}
		if(userinfo.getUserpassword().equals(user.getUserpassword())) {
			m_logger.debug("忘记密码-密码一致---userphone" + user.getUserphone());
			return AjaxResult.fail(305, "新密码不能和旧密码一致");
		}
		int res = m_service.updatePwdByUserId(user.getUserpassword(), userinfo.getUserid());
		if(res > 0) {
			m_logger.debug("忘记密码-密码修改成功---userphone" + user.getUserphone());
			return AjaxResult.success(200, "密码修改成功", null);
		}
		m_logger.debug("忘记密码-密码修改失败---userphone:" + user.getUserphone());
		return AjaxResult.fail(306, "密码修改失败");
	}
	
	/**
	 * 获取用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getuserinfo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getUserInfo(HttpServletRequest request) {
		String userid = PublicUtil.getSessionString(request, PublicData.M_USERID_SESSION);
		UserTable user = m_service.getUserInfoByUserId(userid);
		if(user == null){
			m_logger.debug("查询用户信息-查询为空---userid:" + userid);
			return AjaxResult.fail(301, "查询为空");
		}
		user.setUserid(null);
		user.setUserpassword(null);
		user.setUserjurisdiction(null);
		user.setUserlock(null);
		user.setUserregisttime(null);
		user.setUserregisttime(null);
		user.setDeleteflag(null);
		return AjaxResult.success(200, "查询成功", user);
	}
	
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult login(HttpServletRequest request, UserTable user) {
		if(!PublicUtil.isEmpty(user.getUsername())) {
			m_logger.debug("登录-用户名为空---登录信息:" + user.toString());
			return AjaxResult.fail(301, "用户名为空");
		}
		if(!PublicUtil.isEmpty(user.getUserpassword())) {
			m_logger.debug("登录-用户密码为空---登录信息:" + user.toString());
			return AjaxResult.fail(302, "用户密码为空");
		}
		UserTable userinfo = m_service.getUserInfoByUserName(user.getUsername());
		if(userinfo == null) {
			m_logger.debug("登录-用户不存在---username:" + user.getUsername());
			return AjaxResult.fail(303, "用户不存在");
		}
		if(user.getUserpassword().equals(userinfo.getUserpassword())){
			m_logger.debug("登录-登录成功---username:" + user.getUsername());
			PublicUtil.setSessionString(request, userinfo.getUserid(), PublicData.M_USERID_SESSION);
			return AjaxResult.success(200, "登录成功", null);
		}else {
			m_logger.debug("登录-密码错误---username:" + user.getUsername());
			return AjaxResult.fail(304, "密码错误");
		}
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 */
	@RequestMapping(value = "/modifypwd", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult modifyPwd(HttpServletRequest request, String oldpassword, String newpassword) {
		String userid = PublicUtil.getSessionString(request, PublicData.M_USERID_SESSION);
		if(!PublicUtil.isEmpty(oldpassword)) {
			m_logger.debug("修改密码-旧密码为空---userid:" + userid);
			return AjaxResult.fail(301, "旧密码为空");
		}
		if(!PublicUtil.isEmpty(newpassword)) {
			m_logger.debug("修改密码-新密码为空---userid:" + userid);
			return AjaxResult.fail(302, "新密码为空");
		}
		UserTable userinfo = m_service.getUserInfoByUserId(userid);
		if(!oldpassword.equals(userinfo.getUserpassword())) {
			m_logger.debug("修改密码-旧密码不正确---userid:" + userid + "---旧密码:" + oldpassword);
			return AjaxResult.fail(303, "旧密码不正确");
		}
		if(oldpassword.equals(newpassword)) {
			m_logger.debug("修改密码-旧密码和新密码一致---userid:" + userid + "---旧密码:" + oldpassword + "---新密码:" + newpassword);
			return AjaxResult.fail(304, "旧密码和新密码不能一致");
		}
		int res = m_service.updatePwdByUserId(newpassword, userinfo.getUserid());
		if(res > 0) {
			m_logger.debug("修改密码-密码修改成功---userid:" + userid);
			return AjaxResult.success(200, "密码修改成功", null);
		}
		m_logger.debug("修改密码-密码修改失败---userid:" + userid);
		return AjaxResult.fail(305, "密码修改失败");
	}
	
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult register(HttpServletRequest request, UserTable user) {
		if(!PublicUtil.isEmpty(user.getUsername())) {
			m_logger.debug("注册-用户名为空---注册信息:" + user.toString());
			return AjaxResult.fail(301, "用户名为空");
		}
		if(m_service.getDoesItExistByUserName(user.getUsername()) >= 1) {
			m_logger.debug("注册-用户名已存在---注册信息:" + user.toString());
			return AjaxResult.fail(302, "用户名已存在");
		}
		if(!PublicUtil.isEmpty(user.getUseremail())) {
			m_logger.debug("注册-用户邮箱为空---注册信息:" + user.toString());
			return AjaxResult.fail(303, "用户邮箱为空");
		}
		if(m_service.getDoesItExistByUserEmail(user.getUseremail()) >= 1) {
			m_logger.debug("注册-邮箱已存在---注册信息:" + user.toString());
			return AjaxResult.fail(303, "邮箱已存在");
		}
		if(user.getUserphone() == null) {
			m_logger.debug("注册-用户手机号为空---注册信息:" + user.toString());
			return AjaxResult.fail(304, "用户手机号为空");
		}
		if(m_service.getDoesItExistByUserPhone(user.getUserphone()) >= 1) {
			m_logger.debug("注册-手机号已存在---注册信息:" + user.toString());
			return AjaxResult.fail(305, "手机号已存在");
		}
		if(!PublicUtil.isEmpty(user.getUserpassword())) {
			m_logger.debug("注册-用户密码为空---注册信息:" + user.toString());
			return AjaxResult.fail(306, "用户密码为空");
		}
		int res = m_service.register(user);
		if(res > 0) {
			m_logger.debug("注册-注册成功---注册信息:" + user.toString());
			PublicUtil.setSessionString(request, user.getUserid(), PublicData.M_USERID_SESSION);
			return AjaxResult.success(200, "注册成功", res);
		}
		m_logger.debug("注册-注册失败---注册信息:" + user.toString());
		return AjaxResult.fail(307, "注册失败");
	}
		
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/signoutlogin", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult signOutLogin(HttpServletRequest request) {
		request.getSession().removeAttribute(PublicData.M_USERID_SESSION);
		return AjaxResult.success(200, "退出成功", null);
	}
	
}
