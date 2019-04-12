package com.lanxu.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.lanxu.service.UserService;
import com.lanxu.service.ServiceImp.*;
import com.lanxu.dao.UserDao;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@WebServlet(name = "ErrorBook")
public class ErrorBook{
    @Autowired
    UserserviceImp userserviceImp;
    @Autowired
    UserDao userDao;

    public void addQuestion(String name, String question){
        userserviceImp.addQuestion(name,question);
    }

    @RequestMapping(value = "/printQuestion")
    @ResponseBody
    public void printQuestion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String name = (String) request.getSession().getAttribute("current_user");
        List<String> question = userserviceImp.printQuestion(name);
        System.out.println("错题"+question.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("question",question);
        response.getWriter().write(jsonObject.toString());
    }
}

