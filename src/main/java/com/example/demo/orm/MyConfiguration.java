package com.example.demo.orm;

public class MyConfiguration {

    @SuppressWarnings("rawtypes")
    public MapperXmlMsg getMapperMsg(){
        //实际应该读取xml并封装为一个对象，这里为了理解直接用写好的类代替
        MapperXmlMsg mapperXmlMsg = new MapperXmlMsg();
        //对应namespace
        mapperXmlMsg.setInterFaceName("com.example.demo.orm.BlogMapper");
        //对应id
        mapperXmlMsg.setFunctionName("queryBlog");
        mapperXmlMsg.setSql("select * from t_merchant where id = ");
        return mapperXmlMsg;
    }
}