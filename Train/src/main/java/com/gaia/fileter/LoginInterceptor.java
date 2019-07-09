package com.gaia.fileter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaia.util.AjaxResult;
import com.gaia.util.PublicData;
import com.gaia.util.PublicUtil;


@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        //获取session
        Object userid = PublicUtil.getSessionString(request, PublicData.M_USERID_SESSION);
        //判断session中是否有用户数据，如果有，则返回true，继续向下执行
        ObjectMapper mapper = new ObjectMapper();
		if(userid == null) {
			AjaxResult fail = AjaxResult.fail(302, "用户未登录");
			String writeValueAsString = mapper.writeValueAsString(fail);
			response.getWriter().write(writeValueAsString);
			response.setContentType("application/json;charset=UTF-8");
			return false;
		} 
        return true;
	}

}
