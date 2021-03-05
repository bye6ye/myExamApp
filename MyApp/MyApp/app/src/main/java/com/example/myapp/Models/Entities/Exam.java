package com.example.myapp.Models.Entities;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Exam {
    User user;
    Date date;
    Boolean isActive;
    String name;
    List<Question> tickets;
}
