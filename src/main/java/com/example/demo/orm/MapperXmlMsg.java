package com.example.demo.orm;

import lombok.Data;

@Data
public class MapperXmlMsg {
    //真实情况应该使用DOM4j解析后封装
    private String InterFaceName;

    private String FunctionName;

    private String Sql;
}