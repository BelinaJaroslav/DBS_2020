package app.models;

import app.exceptions.RecordDeleteNotAllowed;
import app.exceptions.RecordNotFoundException;
import app.relations.BasicRelation;
import db.ConnectionManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RegisteredVaccination extends Model {
   public static final String name = "registered_vaccinations";

   public void cancel(Integer id) throws SQLException {
      Connection connection = ConnectionManager.getConnection();
      String sql = String.format(
            "SELECT COUNT(r.id) AS registrations_count FROM %s p JOIN %s r ON p.id = r.patient_id WHERE p.id = (SELECT patient_id FROM %s WHERE id = ?) GROUP BY p.id",
            Patient.name,
            RegisteredVaccination.name,
            RegisteredVaccination.name
      );

      try (
            PreparedStatement count = connection.prepareStatement(String.format("SELECT COUNT(*) AS records_count FROM %s WHERE id = ?", modelName()));
            PreparedStatement registrations = connection.prepareStatement(sql);
            PreparedStatement delete = connection.prepareStatement(String.format("DELETE FROM %s WHERE id = ?", modelName()))
      ) {
         connection.setAutoCommit(false);
         count.setInt(1, id);
         ResultSet resultSet = count.executeQuery();

         resultSet.next();
         int cnt = resultSet.getInt("records_count");
         if (cnt == 0) {
            throw new RecordNotFoundException();
         }

         registrations.setInt(1, id);
         resultSet = registrations.executeQuery();
         resultSet.next();
         int patientRegistrationsCnt =  resultSet.getInt("registrations_count");
         if (patientRegistrationsCnt < 2) {
            throw new RecordDeleteNotAllowed();
         }

         delete.setInt(1, id);
         delete.execute();
         connection.commit();
      } catch (Exception e) {
         rollbackAndClose(connection);
         throw e;
      }
   }

   @Override
   protected List<String> resultSetToList(ResultSet resultSet) throws SQLException {
      int id = resultSet.getInt("id");
      int pid = resultSet.getInt("patient_id");
      int vid = resultSet.getInt("vaccine_id");
      int did = resultSet.getInt("doctor_id");
      boolean complete = resultSet.getBoolean("completed");
      Time time = resultSet.getTime("time");
      LinkedList<String> list = new LinkedList<>();

      list.add(Integer.toString(id));
      list.add(Integer.toString(pid));
      list.add(Integer.toString(vid));
      list.add(Integer.toString(did));
      list.add(Boolean.toString(complete));
      list.add(time.toString());

      return list;
   }

   @Override
   protected String modelName() {
      return name;
   }

   @Override
   protected BasicRelation getBasicRelation() {
      return new BasicRelation(
            "registered_vaccination_id",
            "registered_vaccination_patient_id",
            "registered_vaccination_vaccine_id",
            "registered_vaccination_doctor_id",
            "registered_vaccination_complete",
            "registered_vaccination_time"
      );
   }
}
