package com.gaia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.pojo.BlogData;
import com.gaia.pojo.BlogTable;
import com.gaia.service.BlogService;
import com.gaia.util.AjaxResult;
import com.gaia.util.BlogUtil;
import com.gaia.util.PageBean;
import com.gaia.util.PublicData;
import com.gaia.util.PublicUtil;


/**
 * 博客
 */

@RestController
@RequestMapping("/blog")
public class BlogController {

	private static final Logger m_logger = LoggerFactory.getLogger(BlogController.class);
	
	@Autowired
	private BlogService m_service;
	
	/**
	 * 分页查询所有博客数据
	 * @param page	第page页
	 * @param size	每页的数量
	 * @return
	 */
	@RequestMapping("/getblogdata")
	@ResponseBody
	public AjaxResult getBlogData(@RequestParam(value = "page", defaultValue = "1") Integer page, 
			@RequestParam(value = "size", defaultValue = "10") Integer size, String blogcategory){
		List<BlogData> list = m_service.getBlogData(blogcategory);
		if (list.isEmpty()) {
			m_logger.debug("数据查询为空");
			return AjaxResult.fail(301, "数据查询为空");
		}
		PageBean<BlogData> blogList = BlogUtil.paging(page, size, list);
		if(blogList == null) {
			m_logger.debug("服务异常");
			return AjaxResult.fail(301, "服务异常");
		}
		return AjaxResult.success(200, "查询成功", blogList);
	}
	
	/**
	 * 查询自己发表的
	 * @param request
	 * @param page
	 * @param size
	 * @param blogcategory
	 * @return
	 */
	@RequestMapping("/getmyblogdata")
	@ResponseBody
	public AjaxResult getBlogDataByUserId(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") Integer page, 
			@RequestParam(value = "size", defaultValue = "10") Integer size, String blogcategory){
		List<BlogData> list = m_service.getBlogDataByUserId(blogcategory, PublicUtil.getSessionString(request, PublicData.M_USERID_SESSION));
		if (list.isEmpty()) {
			m_logger.debug("数据查询为空");
			return AjaxResult.fail(301, "数据查询为空");
		}
		PageBean<BlogData> blogList = BlogUtil.paging(page, size, list);
		if(blogList == null) {
			m_logger.debug("服务异常");
			return AjaxResult.fail(301, "服务异常");
		}
		return AjaxResult.success(200, "查询成功", blogList);
	}
	
	/**
	 * 查询详情
	 * @param blog
	 * @return
	 */
	@RequestMapping("/getblogdetails")
	@ResponseBody
	public AjaxResult getBlogDetails(BlogTable blog) {
		if(!PublicUtil.isEmpty(blog.getBlogid())) {
			return AjaxResult.fail(301, "ID为空");
		}
		BlogData blogDetails = m_service.selectByPrimaryKey(blog.getBlogid());
		if(blogDetails == null) {
			return AjaxResult.fail(302, "查询为空");
		}
		return AjaxResult.success(200, "查询成功", blogDetails);
	}
	
	/**
	 * 查询分类
	 * @return
	 */
	@RequestMapping("/getblogtype")
	@ResponseBody
	public AjaxResult getBlogType() {
		List<BlogTable> list = m_service.countCategory();
		if(list == null || list.size() == 0) {
			return AjaxResult.fail(301, "查询为空");
		}
		List<String> type = new ArrayList<String>();
		for (BlogTable blogTable : list) {
			type.add(blogTable.getBlogcategory());
		}
		return AjaxResult.success(200, "查询成功", type);
	}
	
	/**
	 * 添加
	 * @param request
	 * @param response
	 * @param blog
	 * @return
	 */
	@RequestMapping("/addblog")
	@ResponseBody
	public AjaxResult insert(HttpServletRequest request, HttpServletResponse response, BlogTable blog) {
		if(!PublicUtil.isEmpty(blog.getBlogtitle())) {
			m_logger.debug("标题为空");
			return AjaxResult.fail(301, "标题为空");
		}
		if(!PublicUtil.isEmpty(blog.getBlogcategory())) {
			m_logger.debug("类型为空");
			return AjaxResult.fail(302, "类型为空");
		}
		if(!PublicUtil.isEmpty(blog.getBlogsynopsis())) {
			m_logger.debug("简介为空");
			return AjaxResult.fail(303, "简介为空");
		}
		if(!PublicUtil.isEmpty(blog.getBlogcontent())) {
			m_logger.debug("内容为空");
			return AjaxResult.fail(304, "内容为空");
		}
		blog.setUserid(PublicUtil.getSessionString(request, PublicData.M_USERID_SESSION));
		int res = m_service.insert(blog);
		if(res > 0) {
			return AjaxResult.success(200, "添加成功", null);
		}
		return AjaxResult.fail(305, "添加失败");
	}
	
	/**
	 * 增加访问量
	 * @param blogid
	 * @return
	 */
	@RequestMapping("/addviewquantity")
	@ResponseBody
	public AjaxResult updateAddViewQuantity(String blogid) {
		if(!PublicUtil.isEmpty(blogid)) {
			return AjaxResult.fail(301, "ID为空");
		}
		int res = m_service.updateAddViewQuantity(blogid);
		if(res > 0) {
			return AjaxResult.success(200, "增加成功", null);
		}
		return AjaxResult.fail(302, "增加失败");
	}
	
	/**
	 * 修改
	 * @param request
	 * @param blog
	 * @return
	 */
	@RequestMapping("/updateblog")
	@ResponseBody
	public AjaxResult updateByPrimaryKey(HttpServletRequest request, BlogTable blog) {
		if(!PublicUtil.isEmpty(blog.getBlogid())) {
			m_logger.debug("修改-ID为空");
			return AjaxResult.fail(301, "ID为空");
		}
		if(!PublicUtil.isEmpty(blog.getBlogtitle())) {
			m_logger.debug("修改-标题为空");
			return AjaxResult.fail(302, "标题为空");
		}
		if(!PublicUtil.isEmpty(blog.getBlogcategory())) {
			m_logger.debug("修改-类别为空");
			return AjaxResult.fail(303, "类型为空");
		}
		if(!PublicUtil.isEmpty(blog.getBlogsynopsis())) {
			m_logger.debug("修改-简介为空");
			return AjaxResult.fail(304, "简介为空");
		}
		if(!PublicUtil.isEmpty(blog.getBlogcontent())) {
			m_logger.debug("修改-内容为空");
			return AjaxResult.fail(305, "内容为空");
		}
		blog.setUserid(PublicUtil.getSessionString(request, PublicData.M_USERID_SESSION));
		int res = m_service.updateByPrimaryKey(blog);
		if(res > 0) {
			return AjaxResult.success(200, "修改成功", null);
		}
		m_logger.debug("修改-修改失败");
		return AjaxResult.fail(306, "修改失败");
	}
	
	/**
	 * 删除
	 * @param request
	 * @param blog
	 * @return
	 */
	@RequestMapping("/updatedeleteflag")
	@ResponseBody
	public AjaxResult updateDeleteFlag(HttpServletRequest request, BlogTable blog) {
		if(!PublicUtil.isEmpty(blog.getBlogid())) {
			m_logger.debug("删除-ID为空");
			return AjaxResult.fail(301, "ID为空");
		}
		String userid = PublicUtil.getSessionString(request, PublicData.M_USERID_SESSION);
		int res = m_service.updateDeleteFlag(blog.getBlogid(), userid);
		if(res > 0) {
			m_logger.debug("删除-删除成功---userid:" + userid + "---blogid:" + blog.getBlogid());
			return AjaxResult.success(200, "删除成功", null);
		}
		m_logger.debug("删除-删除失败---userid:" + userid + "---blogid:" + blog.getBlogid());
		return AjaxResult.fail(302, "删除失败");
	}
	
}
