package app.options;

import app.formatters.Formattable;
import app.relations.BasicRelation;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class GroupBy extends Option {
   @Override
   public Formattable execute(String[] args) throws SQLException {
      String sql = "SELECT h.*, COUNT(hd.id) AS count_of_doctors FROM hospitals h LEFT JOIN hospitals_doctors hd ON h.id = hd.hospital_id GROUP BY h.id, h.name, h.city HAVING COUNT(hd.id) >= 3 ORDER BY COUNT(hd.id) DESC";

      try (
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement()
      ) {
         ResultSet resultSet = statement.executeQuery(sql);
         BasicRelation relation = new BasicRelation("hospital_id", "hospital_name", "hospital_city", "count_of_doctors");

         while (resultSet.next()) {
            relation.addLine(resultSetToList(resultSet));
         }

         return relation;
      }
   }

   protected static List<String> resultSetToList(ResultSet resultSet) throws SQLException {
      LinkedList<String> line = new LinkedList<>();

      line.add(resultSet.getString("id"));
      line.add(resultSet.getString("name"));
      line.add(resultSet.getString("city"));
      line.add(Integer.toString(resultSet.getInt("count_of_doctors")));

      return line;
   }
}