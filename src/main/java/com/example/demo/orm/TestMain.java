package com.example.demo.orm;

public class TestMain {

    public static void main(String[] args) throws Exception{
        MySqlSession mySqlSession = new MySqlSession();
        BlogMapper blogMapper =  mySqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.queryBlog(1);
        System.out.println(blog);
    }
}