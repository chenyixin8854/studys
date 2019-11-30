package com.example.demo.web_container;

//import javax.servlet.http.HttpServlet;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-10-27 21:40
 **/
public abstract class MyServlet {
//        extends HttpServlet {

    public void service(MyRequest myRequest, MyResponse myResponse) {
        if(myRequest.getMethod().equalsIgnoreCase("POST")) {
            doPost(myRequest, myResponse);
        }else if(myRequest.getMethod().equalsIgnoreCase("GET")) {
            doGet(myRequest, myResponse);
        }
    }

    public void doGet(MyRequest myRequest, MyResponse myResponse) {

    }

    public void doPost(MyRequest myRequest, MyResponse myResponse) {

    }

}
