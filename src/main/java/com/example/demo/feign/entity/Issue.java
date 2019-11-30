package com.example.demo.feign.entity;

import lombok.Data;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-09-12 23:48
 **/
@Data
public class Issue {
    String title;
    String body;
    List<String> assignees;
    int milestone;
    List<String> labels;
}
