package com.example;

import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

public class DbConnect {

    Connection conn;

    public Connection dbConnectMethod() {

        try {

            String url = "jdbc:mysql://localhost:3306/best-todo";
            String username = "root";
            String password = "root";
              Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                 conn = DriverManager.getConnection(url, username, password);

                //    Statement statement = conn.createStatement();
                //     int rows = statement.executeUpdate(dbquery);


                System.out.println("Connection to Store DB succesfull!");
                //  System.out.printf("Updated %d rows", rows);
                //   System.out.println();
                //   System.out.println(dbquery);

            } catch (Exception ex) {
                System.out.println("Connection failed...");
                // System.out.println(dbquery);

                System.out.println(ex);
            }

       // return ("index");
        return conn;

    }
}