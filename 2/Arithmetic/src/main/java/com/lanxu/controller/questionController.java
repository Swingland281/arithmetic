package com.lanxu.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@WebServlet(name = "questionController")
public class questionController {
    @Autowired
    ErrorBook errorBook;

    @RequestMapping(value = "/question",method = RequestMethod.POST)
    @ResponseBody
    public void question(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam("max")String max, @RequestParam("min")String min,@RequestParam("k")String k,
                       @RequestParam("n")String n,@RequestParam("flag1")String flag1,@RequestParam("flag2")String flag2
    ) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        Lib lib = new Lib();
        JSONObject jsonObject= new JSONObject();
        List answer = new ArrayList();
        List result = new ArrayList();//存放式子
        List judge = new ArrayList();//存放判断
        //循环生成算式
        List<String> json =new ArrayList<String>();
        if(flag1.equals("false")){
            for(int i=0;i<Integer.parseInt(n);i++){
                for(;;){
                    result=lib.generate1(Integer.parseInt(min),Integer.parseInt(max),Integer.parseInt(k),false);//生成无括号式子
                    judge=lib.cacumulate(result);
                    if(judge.get(0).equals("t")){
                        String s="";
                        for(Object obj:result){
                            s+=obj.toString();
                        }
                        json.add(s);
                        answer.add(judge.get(1));
                        for(Object obj:result){
                            System.out.print(obj);
                        }
                        System.out.println("="+judge.get(1));
                        break;
                    }
                }
            }
        }else{
            for(int i=0;i<Integer.parseInt(n);i++){
                for(;;){
                    result=lib.generate2(Integer.parseInt(min),Integer.parseInt(max),Integer.parseInt(k));//生成有括号式子
                    judge=lib.cacumulate(result);
                    if(judge.get(0).equals("t")){
                        String s="";
                        for(Object obj:result){
                            s+=obj.toString();
                        }
                        json.add(s);
                        answer.add(judge.get(1));
                        for(Object obj:result){
                            System.out.print(obj);
                        }
                        System.out.println("="+judge.get(1));
                        break;
                    }
                }
            }
        }

        JSONArray jsonArray =JSONArray.fromObject(json.toArray());
        jsonObject.put("question",jsonArray);
        String[] question=json.toArray(new String[jsonArray.size()]);
        request.getSession().setAttribute("answer",answer.toArray());
        request.getSession().setAttribute("question",json);
//        List<String> ss = (ArrayList<String>)request.getSession().getAttribute("question");
//        System.out.println("fdfdfd"+ss.size());
//        request.getSession().setAttribute("question",question);
//        String[] ss=(String[])request.getSession().getAttribute("question");
//        System.out.println("fdfdf"+ss.length);
        response.getWriter().write(jsonObject.toString());
    }

    @RequestMapping(value = "/judge",method = RequestMethod.POST)
    @ResponseBody
    public void judge(HttpServletRequest request, HttpServletResponse response,@RequestParam("list")String list) throws ServletException, IOException{
        JSONArray jsonArray = JSONArray.fromObject(list);
        List<String> question = (ArrayList<String>)request.getSession().getAttribute("question");
        List ans = (ArrayList)request.getSession().getAttribute("answer");
        for(int i=0;i<jsonArray.size();i++){
            if(jsonArray.get(i).equals(ans.get(i))){
                jsonArray.set(i,"r");
            }else{
                jsonArray.set(i,"f");
                if(!ans.get(i).equals("-1")){
                  errorBook.addQuestion((String)request.getSession().getAttribute("current_user"), question);
                }
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",jsonArray.toString());
        response.getWriter().write(jsonObject.toString());
    }
}
