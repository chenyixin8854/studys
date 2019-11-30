package com.example.demo.design.combine.filter;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-06-21 01:04
 **/
public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}
