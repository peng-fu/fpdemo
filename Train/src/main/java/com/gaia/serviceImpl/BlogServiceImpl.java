package com.gaia.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaia.dao.BlogTableMapper;
import com.gaia.pojo.BlogData;
import com.gaia.pojo.BlogTable;
import com.gaia.service.BlogService;
import com.gaia.util.PublicUtil;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogTableMapper m_mapper;
	
	public List<BlogTable> countCategory(){
		return m_mapper.countCategory();
	}
	
	@Override
	public List<BlogData> getBlogData(String blogcategory) {
		return m_mapper.selectAll(blogcategory);
	}
	
	@Override
	public List<BlogData> getBlogDataByUserId(String blogcategory, String userid) {
		return m_mapper.selectAllByUserId(blogcategory, userid);
	}
	
	public int insert(BlogTable blog) {
		blog.setBlogid(PublicUtil.getUUID());
		blog.setTimestamp(PublicUtil.getBJCurrentTimeMillis());
		blog.setReleasetime(PublicUtil.getBJCurrentTimeMillis());
		blog.setBlogtype(0);
		blog.setCommentquantity(0);
		blog.setViewquantity(0);
		blog.setDeleteflag(0);
		return m_mapper.insert(blog);
	}
	
	public BlogData selectByPrimaryKey(String blogid) {
		return m_mapper.selectByPrimaryKey(blogid);
	}
	
	public int updateAddCommentQuantity(String blogid) {
		return m_mapper.updateAddCommentQuantity(blogid);
	}
	
	public int updateAddViewQuantity(String blogid) {
		return m_mapper.updateAddViewQuantity(blogid);
	}
	
	public int updateByPrimaryKey(BlogTable blog) {
		blog.setTimestamp(PublicUtil.getBJCurrentTimeMillis());
		return m_mapper.updateByPrimaryKey(blog);
	}
	
	public int updateDeleteFlag(String blogid, String userid) {
		return m_mapper.updateDeleteFlag(blogid, userid);
	}

}
