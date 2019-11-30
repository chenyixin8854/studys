package com.example.demo.jaxb.bean;


import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-09-12 23:19
 **/
@XmlRootElement
public class Student {
    private String id;
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student() {
    }
}