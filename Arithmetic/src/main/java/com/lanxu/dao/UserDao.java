//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lanxu.dao;

import com.lanxu.entity.User;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@WebServlet(name = "UserDao")
public interface UserDao {
    void addUser(User var1);

    User findByName(String var1);

    void addQuestion(@Param("name") String name,@Param("question") String question);

    List printQuestion(@Param("name")String name);

    String selectFromA(@Param("id")int id);

    String selectFromB(@Param("tel")String tel);
}
