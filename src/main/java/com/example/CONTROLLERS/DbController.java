package com.example.CONTROLLERS;


import com.example.TaskMessage;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.*;
//import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;




@Controller
public class DbController {
    /*public static void dbConnect() {

    }*/


    // Выводим на экран список тасков
    @GetMapping("baza")
    public String taskMessage(Map<String, Object> model) {
       // model.put("spisokTaskov", spisokTaskov);
              //  String di = "INSERT INTO tasksList(status, task) VALUES('x', 'task5')";
        try {

            String url = "jdbc:mysql://localhost:8889/best-todo";


            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                Statement statement = conn.createStatement();
             int rows = statement.executeUpdate("INSERT INTO tasksList(status, task) VALUES('x', 'task7')");

                System.out.println("Connection to Store DB succesfull!");
                System.out.printf("Updated %d rows", rows);

            statement.close();

            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }

        return "index";
    }


    //тут мы будем добавлять даные в базу методом ПОСТ
    @PostMapping("/adToDb")
    public String adToDbmethod(@RequestParam String dbquery, Map<String, Object> model) {

        try {

            String url = "jdbc:mysql://localhost:8889/best-todo";


            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                Statement statement = conn.createStatement();
               int rows = statement.executeUpdate(dbquery);


                System.out.println("Connection to Store DB succesfull!");
               System.out.printf("Updated %d rows", rows);
                System.out.println();
                System.out.println(dbquery);

            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
           System.out.println(dbquery);

            System.out.println(ex);
        }



        return ("index");

    }
    //тут мы будем ПОЛУЧАТЬ даные из базы методом ПОСТ
    @PostMapping("/getDataFromDb")
    public String getFromDbmethod(@RequestParam String dbGetData, Map<String, Object> model) {

        try {
                    String url = "jdbc:mysql://localhost:8889/best-todo";

                    String username = "root";
                    String password = "root";
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection(url, username, password))
                    {
                        Statement statement = conn.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM tasksList");
                    while(resultSet.next())
                    {
                        String status = resultSet.getString(1);
                        String task = resultSet.getString(2);
                       System.out.printf("%s.  %s  \n", status, task);
                       // System.out.println(dbGetData);
                    }
                        System.out.println("Connection to Store DB succesfull!");
                     //   System.out.printf("Updated %d rows", rows);
                        System.out.println();
                        System.out.println(dbGetData);
                    }
             }
        catch (Exception ex)
        {
            System.out.println("Connection failed...");


            System.out.println(ex);
        }


        return ("index");

    }


}
