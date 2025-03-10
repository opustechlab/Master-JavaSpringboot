package com.oputechlab.belajarcrud3.model;

public class MyRequest {
    private String name;
    private int age;
    private int id;

    // Constructor
    public MyRequest() {}

    public MyRequest(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

}
