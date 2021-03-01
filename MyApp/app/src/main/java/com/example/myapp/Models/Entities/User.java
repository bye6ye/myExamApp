package com.example.myapp.Models.Entities;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

public class User{

    public String login, password, name, subject;
    public @Nullable List<Exam> exams;

    public User() { }

    public User(String  login, String password, String name, String subject) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.subject = subject;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
