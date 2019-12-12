package com.example.demo.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        // a real example would do work like a network call here
        return "Hello " + name + "!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String s = new CommandHelloWorld("Bob").execute();
        System.out.println(s);
        Future<String> s1 = new CommandHelloWorld("Bob").queue();
        System.out.println(s1.get());
        Observable<String> s2 = new CommandHelloWorld("Bob").observe();
        System.out.println(s2.subscribe(i-> System.out.println(i)));
    }
}