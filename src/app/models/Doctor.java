package app.models;

import app.relations.BasicRelation;
import db.ConnectionManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Doctor extends Model {
   public static final String name = "doctors";

   public BasicRelation selectInWhere() throws SQLException {
      String sql = "SELECT * FROM doctors d WHERE d.salary > (SELECT AVG(d.salary) FROM doctors d);";
      try (
              Connection connection = ConnectionManager.getConnection();
              Statement statement = connection.createStatement();
      ) {
         ResultSet resultSet = statement.executeQuery(sql);
         BasicRelation relation = getBasicRelation();
         while (resultSet.next()) {
            relation.addLine(resultSetToList(resultSet));
         }
         return relation;
      }
   }

   @Override
   protected List<String> resultSetToList(ResultSet resultSet) throws SQLException {
      LinkedList<String> line = new LinkedList<>();
      int id = resultSet.getInt("id");
      int salary = resultSet.getInt("salary");

      line.add(Integer.toString(id));
      line.add(resultSet.getString("name"));
      line.add(resultSet.getString("surname"));
      line.add(Integer.toString(salary));

      return line;
   }

   @Override
   protected String modelName() {
      return name;
   }

   @Override
   protected BasicRelation getBasicRelation() {
      return new BasicRelation("doctor_id", "doctor_name", "doctor_surname", "doctor_salary");
   }
}
