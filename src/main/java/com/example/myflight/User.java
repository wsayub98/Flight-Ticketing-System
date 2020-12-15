package com.example.myflight;

public class User {

    public String email, password, name, gender;
    public Integer age;

    public User() {

    }

    public User(String email, String password, String name, String gender, int age){
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

}
