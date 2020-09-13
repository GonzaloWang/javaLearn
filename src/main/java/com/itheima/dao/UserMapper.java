package com.itheima.dao;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    @Insert("insert into user values(null, #{username}, #{password}, #{email}, #{phoneNum})")
    int addUser(User user);

    @Delete("delete from user where id = #{id}")
    int deleteUser(Integer id);

    @Update("update user set username = #{username}, password = #{password}, email=#{email},phoneNum=#{phoneNum} where id = #{id}")
    int updateUser(User user);

    @Select("select * from user where id = #{id}")
    User findUserByID(Integer id);
}
