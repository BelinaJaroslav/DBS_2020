package app.models;

import app.relations.BasicRelation;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Patient extends Model {
   public static final String name = "patients";

   public List<String> resultSetToList(ResultSet resultSet) throws SQLException {
      LinkedList<String> line = new LinkedList<>();
      int id = resultSet.getInt("id");
      BigDecimal birthNumber = resultSet.getBigDecimal("birth_number");

      line.add(Integer.toString(id));
      line.add(resultSet.getString("name"));
      line.add(resultSet.getString("surname"));
      line.add(birthNumber.toString());

      return line;
   }

   @Override
   public String modelName() {
      return name;
   }

   public BasicRelation getBasicRelation() {
      return new BasicRelation("patient_id", "patient_name", "patient_surname", "patient_birth_number");
   }
}
