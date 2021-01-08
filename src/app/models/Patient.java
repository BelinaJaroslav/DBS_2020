package app.models;

import app.exceptions.RecordNotFoundException;
import app.relations.BasicRelation;
import db.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Patient extends Model {
   public static final String name = "patients";

   public void deleteById(Integer id) throws SQLException {
      Connection connection = ConnectionManager.getConnection();

      try (PreparedStatement delete = connection.prepareStatement(String.format("DELETE FROM %s WHERE id = ?", modelName()))) {
         connection.setAutoCommit(false);
         if (!existsRecordForId(id)) {
            throw new RecordNotFoundException();
         }

         delete.setInt(1, id);
         delete.execute();
         connection.commit();
      } catch (Exception e) {
         rollbackAndClose(connection);
         throw e;
      }
   }

   protected List<String> resultSetToList(ResultSet resultSet) throws SQLException {
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
   protected String modelName() {
      return name;
   }

   protected BasicRelation getBasicRelation() {
      return new BasicRelation("patient_id", "patient_name", "patient_surname", "patient_birth_number");
   }
}
