package com.metrix.orm.test;

import com.metrix.orm.entity.User;
import com.metrix.orm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtilTest {
    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select * from tb_user where id = ?");
            ps.setInt(1,1);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
