package com.example.demo.orm;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyMapperProxy implements InvocationHandler {
    //这里没有使用xml而是使用两个实体类代替
    private  MySqlSession mySqlsession;

    private MyConfiguration myConfiguration;

    public MyMapperProxy(MyConfiguration myConfiguration, MySqlSession mySqlsession) {
        this.myConfiguration=myConfiguration;
        this.mySqlsession=mySqlsession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperXmlMsg mapperXmlMsg = myConfiguration.getMapperMsg();
        //先验证我们的mapper.xml文件中有没有调用的接口类，这里只用了一个mapper举例
        if(!method.getDeclaringClass().getName().equals(mapperXmlMsg.getInterFaceName())){
            return  null;
        }
        Object object = null;
        //xml中是否有对应的方法
        if(method.getName().equals(mapperXmlMsg.getFunctionName())){
            object = mySqlsession.select(mapperXmlMsg.getSql(),String.valueOf(args[0]));
        }

        return object;
    }


}