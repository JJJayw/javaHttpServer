package org.jay.util;

import org.jay.core.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class JDBCUtil {
    public static Connection getConnection() {
        // 1.数据库连接的4个基本要素：
        Map<String, String> dataSource = Configuration.getDataSource();

        String url = dataSource.get("url");
        String user = dataSource.get("username");
        String password = dataSource.get("password");

        //2.获取连接
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
