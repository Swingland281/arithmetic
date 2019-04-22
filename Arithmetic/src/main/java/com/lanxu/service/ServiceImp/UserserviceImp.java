//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lanxu.service.ServiceImp;

import com.lanxu.dao.UserDao;
import com.lanxu.entity.User;
import com.lanxu.service.UserService;
import javax.servlet.annotation.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.List;

@Service
@WebServlet(name = "UserServiceImp")
public class UserserviceImp implements UserService {
    @Autowired
    UserDao userDao;

    public boolean regist(User user) {
        boolean flag;
        if (this.userDao.findByName(user.getUserName()) == null) {
            this.userDao.addUser(user);
            flag = true;
            return true;
        } else {
            flag = false;
            return false;
        }
    }

    public boolean login(String name, String password) {
        boolean flag;
        if (password.equals(this.userDao.findByName(name).getPassword())) {
            flag = true;
            return true;
        } else {
            flag = false;
            return false;
        }
    }

    public User findByName(String username) {
        return this.userDao.findByName(username);
    }

    public void addQuestion(String name,String question){
         this.userDao.addQuestion(name,question);
    }

    public List printQuestion(String name){
        return this.userDao.printQuestion(name);
    }

}
