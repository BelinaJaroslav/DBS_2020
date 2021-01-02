package db;

import config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
   private static final String databaseURL = "jdbc:sqlserver://" + Config.serverName + ":" + Config.port;

   public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(databaseURL, Config.username, Config.password);
   }
}
