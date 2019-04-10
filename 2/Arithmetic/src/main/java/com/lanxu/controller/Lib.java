package com.lanxu.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Lib")
public class Lib {
    Lib2 lib = new Lib2();
    //生成不带括号的式子
    public List generate1(int min,int max,int k,Boolean flag1){
        List list = new ArrayList();
        //随机生成算符的数量
        int k2;
        if(flag1)
            k2 = lib.getRandom(2, k);
        else
            k2 = lib.getRandom(1, k);
        //生成式子
        int m;
        Boolean flag;
        Boolean f;
        int e;
        for(;;){
            list.add(lib.getRandom(min, max));//生成第一个数字
            for(int i=0;i<k2;i++){
                f=false;
                //生成算符
                m=lib.getRandom(1, 4);
                switch(m){
                    case 1:list.add('+');break;
                    case 2:list.add('-');break;
                    case 3:list.add('*');break;
                    case 4:{
                        list.add('÷');
                        f=true;
                        break;
                    }
                }
                //生成数字
                e=lib.getRandom(min,max);
                list.add(e);
                //保证除法运算生成的概率
                if(f){
                    int x;
                    for(;;){//
                        x=e*lib.getRandom(1, 5);
                        if(x<=max)
                            break;
                    }
                    list.set(list.size()-3,x);
                }


            }
            flag=true;
            if(k2>1){
                for(int i=2;i<=k2;i++){
                    if(!list.get(2*i-1).equals(list.get(1))){
                        flag=false;
                        break;
                    }
                }
                if(!flag)
                    break;
                else{
                    list.clear();
                }
            }else
                break;
        }
        //式子生成完毕
        return list;
    }

    public List generate_K(List list1){
        List list = new ArrayList();
        List result = new ArrayList();
        list .addAll(list1);
        //获取list的长度
        int len=list.size();
        if(len<3)
            return result;
        else{
            //在第p个数字之前加上左括号
            int p=lib.getRandom(1, (len-1)/2);
            list.add(2*p-2,'(');
            //在第q个数字后加右括号
            int q=lib.getRandom(p+1, (len+1)/2);
            list.add(2*q,')');
            len=list.size();
            if(p>2&&q<(len-3)/2){
                //System.out.println("middle");
                result.addAll(generate_K(list.subList(0,2*p-3)));
                result.addAll(list.subList(2*p-3, 2*q+2));
                result.addAll(generate_K(list.subList(2*q+2,len)));
                return result;
            }else if(p>2&&q>=(len-3)/2){
                //System.out.println("left");
                result.addAll(generate_K(list.subList(0,2*p-3)));
                result.addAll(list.subList(2*p-3,len));
                return result;
            }else if(p<=2&&q<(len-3)/2){
                //System.out.println("right");
                result.addAll(list.subList(0, 2*q+2));
                result.addAll(generate_K(list.subList(2*q+2,len)));
                return result;
            }
            else
                return list;

        }

    }

    public List generate2(int min,int max,int k){
        List result = new ArrayList();
        //生成不带括号的式子
        result=this.generate1(min,max,k,true);
        //生成带括号的原始式子
        result=generate_K(result);
        //对原始式子进行进一步判断
        int len=result.size();
        Boolean flag=false;
        if(result.get(0).equals('(')&&result.get(len-1).equals(')')){
            for(int i=1;i<=len-2;i++){
                if(result.get(i).equals('(')||result.get(i).equals(')')){
                    flag=true;
                }
            }
            //对原始式子进行精简
            if(!flag){
                result.remove(0);
                result.add(2,'(');
            }
        }

        return result;
    }

    public List cacumulate(List list){
        String flag="t";
        List r=new ArrayList();
        //中序表达式转化为后序表达式
        List<Character> s1 = new ArrayList<Character>();//运算符栈
        List s2 = new ArrayList();//中间结果栈
        //从左到右扫描中缀表达式
        for(Object obj:list){
            if(obj.equals('(')||obj.equals(')')){
                //当是括号的情况
                if(obj.equals('(')){
                    s1.add('(');
                }
                if(obj.equals(')')){
                    while(!s1.get(s1.size()-1).equals('(')){
                        s2.add(s1.get(s1.size()-1));
                        s1.remove(s1.size()-1);
                    }
                    s1.remove(s1.size()-1);
                }
            }else if(obj.equals('+')||obj.equals('-')||obj.equals('*')||obj.equals('÷')){
                //当是运算符的情况
                for(;;){
                    //当s1为空或栈顶元素为"("或优先级比栈顶元素优先级高时
//					int b=lib.getPriority((String)obj);
//					int a=lib.getPriority((String)s1.get(s1.size()-1));
                    if(s1.isEmpty()||s1.get(s1.size()-1).equals('(')){
                        s1.add((Character) obj);
                        break;
                    }else if(lib.getPriority(obj)>lib.getPriority(s1.get(s1.size()-1))){
                        s1.add((Character) obj);
                        break;
                    }else{
                        s2.add(s1.get(s1.size()-1));
                        s1.remove(s1.size()-1);
                    }
                }
            }else{
                //当是数字的情况
                s2.add(obj);
            }

        }
        while(!s1.isEmpty()){
            s2.add(s1.get(s1.size()-1));
            s1.remove(s1.size()-1);
        }


        //计算后序表达式
        List<Integer> s3 = new ArrayList<Integer>();//运算栈
        float result;//存放中间结果

        for(Object obj:s2){
            if(obj.equals('+')){
                result=s3.get(s3.size()-2)+s3.get(s3.size()-1);
                s3.remove(s3.size()-1);
                s3.remove(s3.size()-1);
                s3.add((int)result);
            }else if(obj.equals('-')){
                result=s3.get(s3.size()-2)-s3.get(s3.size()-1);
                if(result<0){
                    flag="f";
                    break;
                }else{
                    s3.remove(s3.size()-1);
                    s3.remove(s3.size()-1);
                    s3.add((int)result);
                }
            }else if(obj.equals('*')){
                result=s3.get(s3.size()-2)*s3.get(s3.size()-1);
                if(result>1000){
                    flag="f";
                    break;
                }else {
                    s3.remove(s3.size() - 1);
                    s3.remove(s3.size() - 1);
                    s3.add((int) result);
                }
            }else if(obj.equals('÷')){
                result=(float)s3.get(s3.size()-2)/(float)s3.get(s3.size()-1);
                if(result!=(int)result){
                    flag="f";
                    break;
                }else{
                    s3.remove(s3.size()-1);
                    s3.remove(s3.size()-1);
                    s3.add((int)result);
                }
            }else{
                s3.add((Integer) obj);
            }
        }

        r.add(flag);
        if(flag.equals("t")){
            r.add(s3.get(0));
        }
        return r;
    }

}
