package com.gaia.service;

import java.util.List;

import com.gaia.pojo.BlogData;
import com.gaia.pojo.BlogTable;

public interface BlogService {

	List<BlogTable> countCategory();
	
	List<BlogData> getBlogData(String blogcategory);
	
	List<BlogData> getBlogDataByUserId(String blogcategory, String userid);

	int insert(BlogTable blog);
	
	BlogData selectByPrimaryKey(String blogid);
	
	int updateAddCommentQuantity(String blogid);
	
	int updateAddViewQuantity(String blogid);
	
	int updateByPrimaryKey(BlogTable blog);
	
	int updateDeleteFlag(String blogid, String userid);

}
