package app;

import app.options.Parser;
import app.formatters.Formattable;

import java.util.Scanner;

public class Main {
   private static Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {
      System.out.println(welcomeText());

      while(true) {
         System.out.print("> ");

         try {
            String line = scanner.nextLine();
            Formattable data = Parser.parseLine(line);

            if (data != null) {
               System.out.println(data.format());
            }
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
   }

   public static String welcomeText() {
      return "# # # # # # # # # # # # #\n" +
            "# VACCINATION  REGISTRY #\n" +
            "# # # # # # # # # # # # #\n\n";
   }

   //public static void main(String[] args) throws Exception {
//
   //   String databaseURL = "jdbc:sqlserver://" + Config.serverName + ":" + Config.port;
//
   //   //create connection
   //   try (Connection connection = DriverManager.getConnection(databaseURL, Config.username, Config.password)) {
   //      //create statement
   //      Statement stmnt = connection.createStatement();
   //      //execute query
   //      ResultSet rs = stmnt.executeQuery("SELECT * FROM users");
   //      //process result
   //      while (rs.next()) {
   //         int id = rs.getInt("id");
   //         String name = rs.getString("name");
   //         String email = rs.getString("email");
   //         System.out.format("%d %-10s %-10s", id, name, email);
   //      }
   //   }
   //}
}
