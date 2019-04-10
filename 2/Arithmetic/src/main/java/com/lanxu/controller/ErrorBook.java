package com.lanxu.controller;

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

@Controller
@WebServlet(name = "ErrorBook")
public class ErrorBook{
    @Autowired
    UserserviceImp userserviceImp;
    @Autowired
    UserDao userDao;

    public void addQuestion(String name, List list){
        String question="";
        for(Object obj:list){
            question+=obj.toString();
        }

    }
}
