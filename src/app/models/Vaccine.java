package app.models;

import app.exceptions.RecordNotFoundException;
import app.exceptions.RecordWasNotChangedException;
import app.formatters.Formattable;
import app.relations.BasicRelation;
import db.ConnectionManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Vaccine extends Model {
   public static final String name = "vaccines";

   @Override
   protected List<String> resultSetToList(ResultSet resultSet) throws SQLException {
      LinkedList<String> line = new LinkedList<>();
      int id = resultSet.getInt("id");
      int price = resultSet.getInt("price");

      line.add(Integer.toString(id));
      line.add(resultSet.getString("name"));
      line.add(resultSet.getString("manufacturer"));
      line.add(Integer.toString(price));

      return line;
   }

   @Override
   protected String modelName() {
      return name;
   }

   @Override
   protected BasicRelation getBasicRelation() {
      return new BasicRelation("vaccine_id", "vaccine_name", "vaccine_manufacturer", "vaccine_price");
   }
}
