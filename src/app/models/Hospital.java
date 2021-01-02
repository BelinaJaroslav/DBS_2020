package app.models;

import app.relations.BasicRelation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Hospital extends Model {
   public static final String name = "hospitals";

   @Override
   protected List<String> resultSetToList(ResultSet resultSet) throws SQLException {
      LinkedList<String> line = new LinkedList<>();
      int id = resultSet.getInt("id");

      line.add(Integer.toString(id));
      line.add(resultSet.getString("name"));
      line.add(resultSet.getString("address"));

      return line;
   }

   @Override
   public String modelName() {
      return name;
   }

   @Override
   public BasicRelation getBasicRelation() {
      return new BasicRelation("hospital_id", "hospital_name", "hospital_address");
   }
}
