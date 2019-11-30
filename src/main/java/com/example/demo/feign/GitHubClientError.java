package com.example.demo.feign;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-09-12 23:51
 **/
public class GitHubClientError extends RuntimeException {
    private String message; // parsed from json

    @Override
    public String getMessage() {
        return message;
    }
}
