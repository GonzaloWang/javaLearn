package com.itheima.dao;

import com.itheima.pojo.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {

    @Select("select * from account")
    List<Account> findAll();

    @Insert("insert into account values(null, #{name}, #{money})")
    Integer addAccount(Account account);

    @Delete("delete from account where id = #{id}")
    Integer deleteAccount(Integer id);

    @Update("update account set name = #{name}, money = #{money} where id = #{id}")
    Integer updateAccount(Account account);

    @Select("select * from account where id = #{id}")
    Account findAccountByID(Integer id);
}
