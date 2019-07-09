package com.gaia.util;

import java.time.Instant;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 公共工具类
 */
public class PublicUtil {

	
	/**
	 * 生成时间戳
	 * @return
	 */
	public static long getBJCurrentTimeMillis() {
		int rawOffset = TimeZone.getDefault().getRawOffset();
		long timeStamp = System.currentTimeMillis() - rawOffset;
		Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(0));
		timeStamp = now.toEpochMilli();
		return timeStamp;
	}
	
	/**
	 * 獲取session中的信息
	 * @param request
	 * @param response
	 * @param sessionName
	 * @return
	 */
	public static String getSessionString(HttpServletRequest request, String key) {
		Object obj = request.getSession().getAttribute(key);
		if(obj != null) {
			return obj.toString();
		}else {
			return null;
		}
	}
	
	/**
	 * 获取UUID,用于数据库插入时手动生成一个ID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 判断字符串
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str == null || str.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 存儲session信息
	 * @param request
	 * @param response
	 * @param value
	 */
	public static void setSessionString(HttpServletRequest request, String value, String key) {
		HttpSession session = request.getSession(false);
		if (session == null){
			session = request.getSession();
		}
		session.setAttribute(key, value);
		session.setAttribute("SESSIONID", session.getId());
		session.setMaxInactiveInterval(10800);
	}
	
	
}
