package com.metrix.orm.test;

import com.metrix.orm.entity.User;
import com.metrix.orm.util.DBUtil;

import java.util.List;

public class TestDBUtils {
    public static void main(String[] args) {
        User user = DBUtil.selectOne("select * from tb_user where id= ? and username = ?", User.class, 1, "admin");
        System.out.println(user);
//        List<User> users = DBUtil.selectAll("select * from tb_user ", User.class);
//        System.out.println(users);
//        boolean update = DBUtil.update("insert into tb_user(id,username,password) values(?,?,?)",  100, "metrix", "metrix");
//        System.out.println(update);
    }
}
