package ru.home.chernyadieva.mybatisdemo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String university;
    private String faculty;
    private LocalDateTime timeCreate;
}
