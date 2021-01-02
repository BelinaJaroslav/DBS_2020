package app.models;

import app.relations.BasicRelation;
import config.Config;
import db.ConnectionManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class User extends Model {
   @Override
   public BasicRelation selectAll() throws SQLException {
      BasicRelation relation = new BasicRelation("user_id", "user_name", "user_email");

      try (Connection connection = ConnectionManager.getConnection()) {
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

         while (resultSet.next()) {
            relation.addLine(resultSetToList(resultSet));
         }
      }

      return relation;
   }

   @Override
   public BasicRelation selectAll(Integer id) throws SQLException {
      BasicRelation relation = new BasicRelation("user_id", "user_name", "user_email");

      try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")
      ) {
         preparedStatement.setInt(1, id);
         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
            relation.addLine(resultSetToList(resultSet));
         }
      }

      return relation;
   }

   private List<String> resultSetToList(ResultSet resultSet) throws SQLException {
      LinkedList<String> line = new LinkedList<>();
      int id = resultSet.getInt("id");

      line.add(Integer.toString(id));
      line.add(resultSet.getString("name"));
      line.add(resultSet.getString("email"));

      return line;
   }
}
