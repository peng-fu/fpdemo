package com.gaia.dao;

import com.gaia.pojo.CommentData;
import com.gaia.pojo.CommentTable;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentTableMapper {
    int deleteByPrimaryKey(String commentid);

    int insert(CommentTable record);

    CommentTable selectByPrimaryKey(String commentid);

    List<CommentData> selectAll(@Param("blogid")String blogid);

    int updateByPrimaryKey(CommentTable record);
}