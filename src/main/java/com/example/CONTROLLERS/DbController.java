package com.example.CONTROLLERS;

import com.example.DbConnect;
import com.example.TaskMessageDb;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Controller
//@RestController  // поменял @Controller на @RestController поскольку он возвращает JSON
public class DbController {
    /*public static void dbConnect() {

    }*/


    // Выводим на экран список тасков
    @GetMapping("baza")
    public String taskMessage(Map<String, MysqlxDatatypes.Object> model) {
       // model.put("spisokTaskov", spisokTaskov);
              //  String di = "INSERT INTO tasksList(status, task) VALUES('x', 'task5')";
        try {

            String url = "jdbc:mysql://localhost:3306/best-todo";


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

            String url = "jdbc:mysql://localhost:3306/best-todo";

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
   /* @PostMapping("/getDataFromDb")
    public String getFromDbmethod(@RequestParam String dbGetData, Map<String, Object> model) {

        try {
                    String url = "jdbc:mysql://localhost:3306/best-todo";

                    String username = "root";
                    String password = "root";
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection(url, username, password))
                    {
                        Statement statement = conn.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM tasksList");
                    while(resultSet.next())
                    {
                        int id = resultSet.getInt("id");
                        String status = resultSet.getString("status");
                        String task = resultSet.getString("task");
                       System.out.printf("%s.  %s %s \n", id, status, task);
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
    }*/
  //  тут мы будем ПОЛУЧАТЬ даные из базы методом ПОСТ
    //  используя класс подключения к базе
    //и отдельный класс GetAllDataFromDb для вывода всей базы на экран
    @PostMapping("/getDataFromDb")
    public String getFromDbmethod(@RequestParam String dbGetData, Map<String, Object> model) {

        DbConnect dbConnect = new DbConnect();
        TaskMessageDb taskMessageDb;
        List<TaskMessageDb> listTaskov = new ArrayList<>();

        try {
           /* String url = "jdbc:mysql://localhost:3306/best-todo";

            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();*/
            try (Statement statement = dbConnect.dbConnectMethod().createStatement())
            {
               // Statement statement = dbConnect.dbConnectMethod().createStatement();
               // Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(dbGetData);
                while(resultSet.next())
                {

                    int id = resultSet.getInt("id");
                    String status = resultSet.getString("status");
                    String task = resultSet.getString("task");
                    taskMessageDb = new TaskMessageDb(id, task, status);
                    listTaskov.add(taskMessageDb);
                   // System.out.printf("%s.  %s %s \n", id, status, task);
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
        model.put("listTaskov", listTaskov);
        return "index";
       // return ("index");
        //return listTaskov;
    }

}
