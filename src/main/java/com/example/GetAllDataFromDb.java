package com.example;

import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllDataFromDb {

    public List<TaskMessageDb> getAlldatafroDb(Map<String, Object> model) {

        DbConnect dbConnect = new DbConnect();
        TaskMessageDb taskMessageDb;
        List<TaskMessageDb> listTaskov = new ArrayList<>();

        try {

            try (Statement statement = dbConnect.dbConnectMethod().createStatement())
            {

                ResultSet resultSet = statement.executeQuery("SELECT * FROM tasksList");
                while(resultSet.next())
                {

                    int id = resultSet.getInt("id");
                    String status = resultSet.getString("status");
                    String task = resultSet.getString("task");
                    taskMessageDb = new TaskMessageDb(id, task, status);
                    listTaskov.add(taskMessageDb);

                }
                System.out.println("Connection to Store DB succesfull!");

            }
        }
        catch (Exception ex)
        {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
     //   model.put("listTaskov", listTaskov);
       // return "index";
        // return ("index");
        return listTaskov;
    }
}
