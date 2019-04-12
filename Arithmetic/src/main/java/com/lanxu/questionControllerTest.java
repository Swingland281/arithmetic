package com.lanxu;

import com.lanxu.controller.Lib;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class questionControllerTest {

    @org.junit.Test
    public void question() {
        int min=1;//数值区间的最小值
        int max=100;//数值区间的最大值
        int n=100;//生成的式子数量
        int k=8;//生成的式子运算符最大值
        String flag1="true";//式子是否有括号
        //String flag2=true;//生成的式子是否是整式
        Lib lib = new Lib();

        List result = new ArrayList();//存放式子
        List judge = new ArrayList();//存放判断
        //循环生成算式

        if(flag1.equals("false")){
            for(int i=0;i<n;i++){
                for(;;){
                    result=lib.generate1(min,max,k,false);//生成无括号式子
                    judge=lib.cacumulate(result);
                    if(judge.get(0).equals("t")){
                        for(Object obj:result){
                            System.out.print(obj);
                        }
                        System.out.println("="+judge.get(1));
                        break;
                    }
                }
            }
        }else{
            for(int i=0;i<n;i++){
                for(;;){
                    result=lib.generate2(min,max,k);//生成无括号式子
                    judge=lib.cacumulate(result);
                    if(judge.get(0).equals("t")){
                        for(Object obj:result){
                            System.out.print(obj);
                        }
                        System.out.println("="+judge.get(1));
                        break;
                    }
                }
            }
        }


    }
}