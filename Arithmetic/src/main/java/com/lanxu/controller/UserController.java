package com.lanxu.controller;

import com.lanxu.entity.User;
import com.lanxu.service.ServiceImp.UserserviceImp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class UserController {
    @Autowired
    UserserviceImp userserviceImp;
    @Autowired
    User user;

    @RequestMapping(value = {"/regist"}, method = {RequestMethod.POST})
    @ResponseBody
    public void regist(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String userName, @RequestParam("password") String password, @RequestParam("email") String email,@RequestParam("type")int type) throws ServletException, IOException {
        this.user.setEmail(email);
        this.user.setPassword(password);
        this.user.setUserName(userName);
        this.user.setType(type);
        JSONObject jsonObject = new JSONObject();
        if (this.userserviceImp.regist(this.user)) {
            jsonObject.put("status", 1);
            response.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("status", 2);
            response.getWriter().write(jsonObject.toString());
        }

    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();
        System.out.println(username+"  "+password);
        if (this.userserviceImp.login(username, password)) {
            request.getSession().setAttribute("current_user", username);
            jsonObject.put("status", 1);
            response.getWriter().write(jsonObject.toString());
        } else {
            jsonObject.put("status", 2);
            response.getWriter().write(jsonObject.toString());
        }

    }


    }
