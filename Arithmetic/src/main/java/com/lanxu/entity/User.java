package com.lanxu.entity;

import javax.servlet.annotation.WebServlet;
import org.springframework.stereotype.Component;

@Component
@WebServlet(name = "User")
public class User {
    private String name;
    private String password;
    private String email;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getUserName() {
        return this.name;
    }

    public void setUserName(String userName) {
        this.name = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
