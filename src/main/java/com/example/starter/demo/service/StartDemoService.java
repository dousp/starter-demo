package com.example.starter.demo.service;

public class StartDemoService {

    private Boolean enabled;
    private String name;
    private Integer age;
    private String hometown;

    public StartDemoService(Boolean enabled,String name, Integer age, String hometown) {
        this.enabled = enabled;
        this.name = name;
        this.age = age;
        this.hometown = hometown;
    }

    public String sayHello(String name) {
        return "Hello, " + name;
    }

    public String helloWorld() {
        return String.format("[name=%s, age=%d, hometown=%s]", this.name, this.age, this.hometown);
    }
}
