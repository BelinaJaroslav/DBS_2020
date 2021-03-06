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

public class SelectInSelect extends Option {
   @Override
   public Formattable execute(String[] args) throws SQLException {
      String innerSelect = "SELECT COUNT(registered_vaccinations.id) FROM registered_vaccinations WHERE p.id = registered_vaccinations.patient_id";
      String outerSelect = "SELECT p.name, p.surname, (%s) AS registration_count FROM patients p INNER JOIN registered_vaccinations rv ON rv.patient_id = p.id GROUP BY p.id, p.name, p.surname ORDER BY COUNT(rv.id) DESC";
      String query = String.format(outerSelect, innerSelect);

      try (
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement()
      ) {
         ResultSet resultSet = statement.executeQuery(query);
         BasicRelation relation = new BasicRelation("patient_name", "patient_surname", "registration_count");

         while (resultSet.next()) {
            relation.addLine(resultSetToList(resultSet));
         }

         return relation;
      }
   }

   protected static List<String> resultSetToList(ResultSet resultSet) throws SQLException {
      LinkedList<String> line = new LinkedList<>();

      line.add(resultSet.getString("name"));
      line.add(resultSet.getString("surname"));
      line.add(Integer.toString(resultSet.getInt("registration_count")));

      return line;
   }
}