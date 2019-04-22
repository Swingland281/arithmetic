//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lanxu.service;

import com.lanxu.entity.User;
import javax.servlet.annotation.WebServlet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@WebServlet(
        name = "UserService"
)
public interface UserService {
    boolean regist(User var1);

    boolean login(String var1, String var2);

    User findByName(String var1);

    void addQuestion(String name,String question);

    List printQuestion(String name);

}
