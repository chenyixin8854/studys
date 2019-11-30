package com.example.demo.orm;

import lombok.Data;

@Data
public class ConfigXmlMsg {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public static ConfigXmlMsg getConfigXmlMsg(){
        ConfigXmlMsg configXmlMsg = new ConfigXmlMsg();
        configXmlMsg.setDriverClassName("com.mysql.cj.jdbc.Driver");
        configXmlMsg.setUrl("jdbc:mysql://cdb-hhkbssqo.bj.tencentcdb.com:10138/fate?useSSL=false&nullNamePatternMatchesAll=true&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useLegacyDatetimeCode=false");
        configXmlMsg.setPassword("fate");
        configXmlMsg.setUsername("P@ssw0rd");

        return configXmlMsg;
    }
}