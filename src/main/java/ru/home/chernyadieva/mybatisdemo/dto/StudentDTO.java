package ru.home.chernyadieva.mybatisdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("age")
    private int age;

    @JsonProperty("university")
    private String university;

    @JsonProperty("faculty")
    private String faculty;
}
