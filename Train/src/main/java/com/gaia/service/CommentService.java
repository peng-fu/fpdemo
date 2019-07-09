package com.gaia.service;

import java.util.List;

import com.gaia.pojo.CommentData;
import com.gaia.pojo.CommentTable;

public interface CommentService {

	List<CommentData> selectAll(String blogid);
	
	int insert(CommentTable record);
	
}
