package com.itheima.dao;

import com.itheima.pojo.Account;
import org.apache.ibatis.annotations.Select;
import java.util.List;
public interface AccountMapper {
    @Select("select * from account ")
    List<Account> findAll();
}
