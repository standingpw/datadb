package com.metrix.orm.util;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtil {
    //
    private static final Properties properties = new Properties();
    private static Connection connection = null;
    private static InputStream inputStream = null;
    private static DruidDataSource ds;
    static {
        inputStream = DBUtil.class.getResourceAsStream("/db.properties");
        try {
            properties.load(inputStream);
            ds =(DruidDataSource)DruidDataSourceFactory.createDataSource(properties);

//            Class.forName(properties.getProperty("driverClass"));
            ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        try {
            connection = ds.getConnection();
//            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void CloseAll(Connection connection, Statement statement, ResultSet resultSet) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (resultSet != null) {
            try {
                statement.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }


    }

    public static void CloseAll(Connection connection, Statement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * 封装一个查询方法，
     * sql:预处理的sql，占位为？
     * select * from tb_user where id = ? and username=? and password = ?
     * 1 admin admin
     * 参数3：赋值
     * 参数2 :结果要封装的类
     */
    public static <T> T selectOne(String sql,Class<T> t  ,Object... args){
        Connection connection = getConnection();//获得连接
        T target = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0;args!=null && i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            ResultSet rs = preparedStatement.executeQuery();
//            Class<User> userClass = User.class;
            while (rs.next()){
                target = t.newInstance();
                Field[] fields = t.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    Object value = rs.getObject(field.getName());
                    //给对象的改字段赋值
                    field.setAccessible(true);//破解私有
                    field.set(target,value);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
     return target;
    };

    /**
     * 查询全部
     * @param sql
     * @param t
     * @param args
     * @param <T>
     * @return
     */
    public static <T> List<T>  selectAll(String sql,Class<T> t,Object... args){
        ArrayList<T> ts = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0;  args!=null && i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            ResultSet rs = ps.executeQuery();//执行查询语句

            while (rs.next()){
                T t1 = t.newInstance();
                Field[] fields = t.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    fields[i].set(t1,rs.getObject(fields[i].getName()));
                }
                ts.add(t1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ts;
    }

    /**
     * 更新数据
     * @param sql
     * @param args
     * @return
     */
    public static boolean update(String sql,Object... args){
        int num = 0;
        Connection connection = getConnection();
        PreparedStatement ps = null;
        try {
             ps = connection.prepareStatement(sql);
            for (int i = 0; args.length!= 0 && i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
             num = ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                ps.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return num>0? true:false;
    }
}