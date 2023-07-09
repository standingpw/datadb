package com.metrix.reflect;

import com.metrix.orm.entity.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class TestReflect {
    public static void main(String[] args) throws Exception {
//        getClassFile();
//        getAndSetFields();
//        getAndSetMothed();
        getAndSetConstructor();
    }

    public static void getAndSetConstructor() {
        Class<User> userClass = User.class;
        try {
            //通过参数列表，来判有参构造和无参构造
//            Constructor constructor = userClass.getDeclaredConstructor();
            Constructor constructor = userClass.getDeclaredConstructor(int.class, String.class, String.class, String.class, Date.class, double.class, char.class);

//            int id, String username, String password, String phone, Date birthday, double money, char gender
            User user = (User) constructor.newInstance(1, "zhangsan", "123", "1230003300", new Date(), 1000.0, '1');
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //    反射
    public static void getAndSetMothed() throws NoSuchMethodException {
        Class<User> userClass = User.class;
        Method[] declaredMethods = userClass.getDeclaredMethods();
        Method method1 = userClass.getDeclaredMethod("method1", int.class, int.class);
        int parameterCount = method1.getParameterCount();
        System.out.println(parameterCount);

        Class<?>[] parameterTypes = method1.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            System.out.println(parameterType);
        }
        try {
            Object invoke = method1.invoke(new User(), 1, 2);
            System.out.println("返回值：" + invoke);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


//        for (Method declaredMethod : declaredMethods) {
//            System.out.println(declaredMethod);
//        }
    }

    public static void getAndSetFields() throws Exception {
        Class<User> userClass = User.class;

        Field id1 = userClass.getDeclaredField("id");
        System.out.println(id1);

//        Field id = userClass.getField("id");
//        System.out.println(id);
        Field[] declaredFields = userClass.getDeclaredFields();
        for (Field f : declaredFields) {
            System.out.println(f);
        }
//
        String name = id1.getName();
        System.out.println(name);
        System.out.println(id1.getModifiers());
//        或者对象的属性值
        User user = new User();
        id1.setAccessible(true);//通过设置访问对象的能力，实现访问对象
        int value = id1.getInt(user);
        System.out.println(value);
//        设置属性值
        id1.set(user, 11);
        System.out.println(user);


    }

    /**
     * 演示反射，获取字节码文件
     */
    public static void getClassFile() throws Exception {
        //方式一
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        Class<?> clazzUser = Class.forName("com.metrix.orm.entity.User");
        //方式二
        Class<User> userClass = User.class;
        //方式三
        User user = new User();
        Class<? extends User> userClass1 = user.getClass();


    }


}
