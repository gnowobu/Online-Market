package com.tzy.db.core;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelp {
    static Properties info = new Properties();
    static {//use properties file to config database connection
        InputStream in = DBHelp.class.getClassLoader().getResourceAsStream("config.properties");//
        try {
            info.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Database connection

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName(info.getProperty("driver"));

        Connection connection = DriverManager.getConnection(info.getProperty("url"), info);
        return connection;
    }
}
