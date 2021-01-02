package app;

import config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
   public static void main(String[] args) throws Exception {

      String databaseURL = "jdbc:sqlserver://" + Config.serverName + ":" + Config.port;

      //create connection
      try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
         //create statement
         Statement stmnt = connection.createStatement();
         //execute query
         ResultSet rs = stmnt.executeQuery("SELECT * FROM users");
         //process result
         while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            System.out.format("%d %-10s %-10s", id, name, email);
         }
      }
   }
}
