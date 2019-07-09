package com.gaia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaia.pojo.CommentData;
import com.gaia.pojo.CommentTable;
import com.gaia.service.BlogService;
import com.gaia.service.CommentService;
import com.gaia.util.AjaxResult;
import com.gaia.util.PageBean;
import com.gaia.util.PublicData;
import com.gaia.util.PublicUtil;

@RestController
@RequestMapping("/comment")
public class CommentController {

	private static final Logger m_logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService m_service;
	
	@Autowired
	private BlogService m_blogService;
	
	/**
	 * 根据blogid查询所有评论
	 * @param page
	 * @param size
	 * @param blogid
	 * @return
	 */
	@RequestMapping(value = "/getallcomment")
	@ResponseBody
	public AjaxResult getAllComment(@RequestParam(value = "page", defaultValue = "1") Integer page, 
			@RequestParam(value = "size", defaultValue = "10") Integer size, String blogid) {
		if(!PublicUtil.isEmpty(blogid)) {
			return AjaxResult.fail(301, "blogID为空");
		}
		PageBean<CommentData> blogList = new PageBean<CommentData>();
		try {
			List<CommentData> list = m_service.selectAll(blogid);
			if (list.isEmpty()) {
				m_logger.debug("数据查询为空");
				return AjaxResult.fail(301, "数据查询为空");
			}
			int totalSize = list.size();
			int totalPage = (int) ((totalSize%size==0)?(totalSize/size):(totalSize/size+1));
			page = page < 1 ? 1 : page;
			page = page > totalPage ? totalPage : page;
			int i = (page - 1) * size;
			i = i < 0 ? 0 : i;
			int j = page * size;
			j = j > totalSize ? totalSize : j;
			blogList.setDatas(list.subList(i, j));
			blogList.setIndexPage(page);
			blogList.setPageSize(size);
			blogList.setTotalSize((long) totalSize);
			return AjaxResult.success(200, "查询成功", blogList);
		} catch (Exception e) {
			m_logger.debug("服务异常");
			e.printStackTrace();
			return AjaxResult.fail(500, "服务器异常");
		}
	}
	
	/**
	 * 评论
	 * @param request
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/addcomment", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult insert(HttpServletRequest request, CommentTable record) {
		if(!PublicUtil.isEmpty(record.getBlogid())) {
			m_logger.debug("添加评论-blogID为空");
			return AjaxResult.fail(301, "blogID为空");
		}
		if(!PublicUtil.isEmpty(record.getCommentcontent())) {
			m_logger.debug("添加评论-评论内容为空");
			return AjaxResult.fail(302, "评论内容为空");
		}
		record.setCommentid(PublicUtil.getUUID());
		record.setUserid(PublicUtil.getSessionString(request, PublicData.M_USERID_SESSION));
		record.setCommenttime(PublicUtil.getBJCurrentTimeMillis());
		record.setDeleteflag(0);
		record.setTimestamp(PublicUtil.getBJCurrentTimeMillis());
		int res = m_service.insert(record);
		if(res > 0) {
			int blogres = m_blogService.updateAddCommentQuantity(record.getBlogid());
			if(blogres <= 0) {
				return AjaxResult.success(200, "评论成功，增加评论量失败", null);
			}
			return AjaxResult.success(200, "评论成功", null);
		}
		m_logger.debug("添加评论-评论失败");
		return AjaxResult.fail(303, "评论失败");
	}
	
}
