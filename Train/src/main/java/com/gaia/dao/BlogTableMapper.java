package com.gaia.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gaia.pojo.BlogData;
import com.gaia.pojo.BlogTable;

@Mapper
public interface BlogTableMapper {
	
	@Select("select BlogCategory from blogtable group by BlogCategory")
	List<BlogTable> countCategory();
	
    int deleteByPrimaryKey(String blogid);

    int insert(BlogTable record);
    
    List<BlogData> selectAll(@Param("blogcategory")String blogcategory);
    
    List<BlogData> selectAllByUserId(@Param("blogcategory")String blogcategory, @Param("userid")String userid);

    BlogData selectByPrimaryKey(@Param("blogid")String blogid);
    
    @Update("update blogtable set commentquantity = commentquantity + 1 where blogid = #{blogid}")
    int updateAddCommentQuantity(@Param("blogid")String blogid);
    
    @Update("update blogtable set viewQuantity = viewQuantity + 1 where blogid = #{blogid}")
    int updateAddViewQuantity(@Param("blogid")String blogid);
    
    int updateByPrimaryKey(BlogTable record);
    
    @Update("update blogtable set deleteflag = 1 where blogid = #{blogid} and userid = #{userid}")
    int updateDeleteFlag(@Param("blogid")String blogid, @Param("userid")String userid);
}