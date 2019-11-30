package com.example.demo.groovy;


import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyTest {

    public static void main(String[] args) {
        // *) groovy 代码
        String script = "println 'hello'; 'name = ' + name;";

        // *) 传入参数
        Binding binding = new Binding();
        binding.setVariable("name", "lilei");

        // *) 执行脚本代码
        GroovyShell shell = new GroovyShell(binding);
        Object res = shell.evaluate(script);
        //运行完，记得将内部的缓存清理
        shell.getClassLoader().clearCache();
        System.out.println(res);
    }

}