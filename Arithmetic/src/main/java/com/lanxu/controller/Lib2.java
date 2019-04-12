package com.lanxu.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Lib2")
public class Lib2 {
    //生成随机数
    public int getRandom(int min,int max){
        return (int)(min+Math.random()*(max-min+1));
    }

    //判断算符优先级
    public int getPriority(Object obj){
        int priority = 0;
        if(obj.equals("+")||obj.equals("-"))
            priority = 1;
        if(obj.equals("*")||obj.equals("÷"))
            priority = 2;
        return priority;
    }
}
