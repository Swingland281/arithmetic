package com.lanxu;

import com.lanxu.controller.ErrorBook;
import com.lanxu.dao.UserDao;
import com.lanxu.entity.User;
import com.lanxu.service.ServiceImp.UserserviceImp;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ErrorBookTest {

    @Test
    public void test() {
       UserserviceImp userserviceImp = new UserserviceImp();
       userserviceImp.printQuestion("aaa");
    }
}