package com.gaia.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gaia.pojo.UserTable;

@Mapper
public interface UserTableMapper {
	
    int deleteByPrimaryKey(String userid);
    
    UserTable getUserInfoByEmial(String useremail);
    
    @Select("select count(*) from usertable where useremail = #{useremail}")
    int getDoesItExistByUserEmail(@Param("useremail")String useremail);
    
    @Select("select count(*) from usertable where username = #{username}")
    int getDoesItExistByUserName(@Param("username")String username);
    
    @Select("select count(*) from usertable where userphone = #{userphone}")
    int getDoesItExistByUserPhone(@Param("userphone")long userphone);
    
    UserTable getUserInfoByUserId(String userid);
    
    UserTable getUserInfoByUserPhone(long userphone);
    
    UserTable getUserInfoByUserName(String username);
    
    int insert(UserTable record);
    
    List<UserTable> selectAll();

    int updateByPrimaryKey(UserTable record);
    
    @Update("update usertable set userpassword = #{userpassword} where userid = #{userid}")
    int updatePwdByUserId(@Param("userpassword") String userpassword, @Param("userid") String userid);
    
}