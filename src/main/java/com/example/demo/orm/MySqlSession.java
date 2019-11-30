package com.example.demo.orm;



import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlSession {
    private MyConfiguration myConfiguration = new MyConfiguration();

    public <T> T select(String sql, String param){
        ConfigXmlMsg configXmlMsg = ConfigXmlMsg.getConfigXmlMsg();
        try {
            Class.forName(configXmlMsg.getDriverClassName());
            Connection con = DriverManager.getConnection(configXmlMsg.getUrl()
                    ,configXmlMsg.getUsername()
                    ,configXmlMsg.getPassword());
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(new MyConfiguration().getMapperMsg().getSql()+param);
            System.out.println("执行的语句是："+new MyConfiguration().getMapperMsg().getSql()+param);
            Blog blog = new Blog();
            while (rs.next()){
                blog.setId(rs.getString(1));
                System.out.println("id结果："+blog.getId());
                blog.setName(rs.getString(2));
                System.out.println("name结果："+blog.getName());
            }
            return (T)blog;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //<T> T表示返回的类型由调用者决定
    //这里实际上交给MyMapperProxy先处理
    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clas){
        //动态代理调用
        return (T) Proxy.newProxyInstance(clas.getClassLoader(),new Class[]{clas}, new MyMapperProxy(myConfiguration,this));
    }
}