
package com.example.CONTROLLERS;

import com.example.DbConnect;
import com.example.GetAllDataFromDb;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Statement;
import java.util.Map;

//@RestController
@Controller
public class DbUpdateController {

    @PostMapping("/closeTaskInDB")
    public String changeStatusDb(@RequestParam Integer id, Map<String, Object> model) {

        DbConnect dbConnect = new DbConnect();
        String closeStatusQuery = "UPDATE taskslist SET status = 'X' WHERE taskslist.id =" + id;

        try {

           /* String url = "jdbc:mysql://localhost:3306/best-todo";

            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();*/
            try (Statement statement = dbConnect.dbConnectMethod().createStatement()) {

                int resultUpdate = statement.executeUpdate(closeStatusQuery);
              //  Statement statement = conn.createStatement();
              //  int rows = statement.executeUpdate(dbquery);

               /* System.out.println("Connection to Store DB succesfull!");*/
                System.out.printf("Updated %d rows", resultUpdate);
                System.out.println();
              /*  System.out.println(dbquery);*/

            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
          //  System.out.println(dbquery);

            System.out.println(ex);
        }

        GetAllDataFromDb ddd = new GetAllDataFromDb();
        model.put("listTaskov", ddd.getAlldatafroDb(model));
        return ("index");
    }



}

