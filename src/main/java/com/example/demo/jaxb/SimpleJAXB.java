package com.example.demo.jaxb;

import com.example.demo.jaxb.bean.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * @program: demo
 * @description:
 * @author: chenyixin
 * @create: 2019-09-12 23:19
 **/
public class SimpleJAXB {

    public static void javaToxml(Student stu) throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean -> 这里使用Student
        JAXBContext context = JAXBContext.newInstance(Student.class);
        // 创建 Marshaller 实例
        Marshaller marshaller = context.createMarshaller();
        // 设置转换参数 -> 这里举例是告诉序列化器是否格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // 构建输出环境 -> 这里使用标准输出，输出到控制台Console
        PrintStream out = System.out;
        // 将所需对象序列化 -> 该方法没有返回值
        marshaller.marshal(stu, out);
    }

    public static void xmlTojava() throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean -> 这里使用Student
        JAXBContext context = JAXBContext.newInstance(Student.class);
        // 创建 UnMarshaller 实例
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 加载需要转换的XML数据 -> 这里使用InputStream，还可以使用File，Reader等
//        InputStream stream = Student.class.getClassLoader().getResourceAsStream("student1.xml");
        File file = new File("src/main/resources/student1.xml");
        InputStream stream = new FileInputStream(file);
        // 将XML数据序列化 -> 该方法的返回值为Object基类，需要强转类型
        Student stu = (Student) unmarshaller.unmarshal(stream);
        // 将结果打印到控制台
        System.out.println(stu);
    }

    public static void main(String[] args) throws Exception {
//        Student student=new Student();
//        student.setAge(1);
//        student.setName("hello");
//        student.setId("2");
//        javaToxml(student);

        xmlTojava();
    }
}
