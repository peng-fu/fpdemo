package com.gaia.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaia.dao.CommentTableMapper;
import com.gaia.pojo.CommentData;
import com.gaia.pojo.CommentTable;
import com.gaia.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentTableMapper m_mapper;
	
	public int insert(CommentTable record) {
		return m_mapper.insert(record);
	}
	
	public List<CommentData> selectAll(String blogid){
		return m_mapper.selectAll(blogid);
	}
	
}
