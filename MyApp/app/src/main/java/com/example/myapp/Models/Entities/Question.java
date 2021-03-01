package com.example.myapp.Models.Entities;

import java.util.List;

public class Question {
    Integer id;
    String name;
    private List<Answer> answers;
    private int point;
}
