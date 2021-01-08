package app.models;

import app.exceptions.RecordDeleteNotAllowed;
import app.exceptions.RecordNotFoundException;
import app.exceptions.RecordWasNotChangedException;
import app.relations.BasicRelation;
import db.ConnectionManager;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RegisteredVaccination extends Model {
   public static final String name = "registered_vaccinations";

   public void setIncomplete(int id) throws SQLException {
      updateCompletedAttribute(id, false);
   }

   public void setCompleted(int id) throws SQLException {
      updateCompletedAttribute(id, true);
   }

   public void cancel(Integer id) throws SQLException {
      Connection connection = ConnectionManager.getConnection();
      String sql = String.format(
            "SELECT COUNT(r.id) AS registrations_count FROM %s p JOIN %s r ON p.id = r.patient_id WHERE p.id = (SELECT patient_id FROM %s WHERE id = ?) GROUP BY p.id",
            Patient.name,
            RegisteredVaccination.name,
            RegisteredVaccination.name
      );

      try (
            PreparedStatement registrations = connection.prepareStatement(sql);
            PreparedStatement delete = connection.prepareStatement(String.format("DELETE FROM %s WHERE id = ?", modelName()))
      ) {
         connection.setAutoCommit(false);
         if (!existsRecordForId(id)) {
            throw new RecordNotFoundException();
         }

         registrations.setInt(1, id);
         ResultSet resultSet = registrations.executeQuery();
         resultSet.next();
         int patientRegistrationsCnt = resultSet.getInt("registrations_count");
         if (patientRegistrationsCnt < 2) {
            throw new RecordDeleteNotAllowed();
         }

         delete.setInt(1, id);
         delete.execute();

         commitAndClose(connection);
      } catch (Exception e) {
         rollbackAndClose(connection);
         throw e;
      }
   }

   public void setTime(int id, Date date, Time time) throws SQLException {
	  Connection connection = ConnectionManager.getConnection();
	  String sqlUpdate = "UPDATE %s SET time = ? WHERE id = ?";
	  String sqlCurrent = "SELECT time FROM %s WHERE id = ?";
	  sqlUpdate = String.format(sqlUpdate, modelName());
	  sqlCurrent = String.format(sqlCurrent, modelName());
	  
	  try (
            PreparedStatement update = connection.prepareStatement(sqlUpdate);
            PreparedStatement current = connection.prepareStatement(sqlCurrent);
      ) {
         connection.setAutoCommit(false);

         if (!existsRecordForId(id)) {
            throw new RecordNotFoundException();
         }
         current.setInt(1, id);
         ResultSet resultSet = current.executeQuery();
         resultSet.next();
         if (date.equals(resultSet.getDate("time")) && time.equals(resultSet.getTime("time"))) {
            throw new RecordWasNotChangedException();
         }

   	     update.setString(1, date.toString() + " " + time.toString());
   	     update.setInt(2, id);
         update.executeUpdate();

         commitAndClose(connection);
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
      Date date = resultSet.getDate("time");
      Time time = resultSet.getTime("time");
      LinkedList<String> list = new LinkedList<>();

      list.add(Integer.toString(id));
      list.add(Integer.toString(pid));
      list.add(Integer.toString(vid));
      list.add(Integer.toString(did));
      list.add(Boolean.toString(complete));
      list.add(date.toString() + " " + time.toString());

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

   private void updateCompletedAttribute(int id, boolean state) throws SQLException {
      Connection connection = ConnectionManager.getConnection();
      try (
            PreparedStatement currentState = connection.prepareStatement(String.format("SELECT completed FROM %s WHERE id = ?", modelName()));
            PreparedStatement update = connection.prepareStatement(String.format("UPDATE %s SET completed = ? WHERE id = ?", modelName()))
      ) {
         connection.setAutoCommit(false);
         if (!existsRecordForId(id)) {
            throw new RecordNotFoundException();
         }

         currentState.setInt(1, id);
         ResultSet resultSet = currentState.executeQuery();
         resultSet.next();
         if (state == resultSet.getBoolean("completed")) {
            throw new RecordWasNotChangedException();
         }

         update.setBoolean(1, state);
         update.setInt(2, id);
         update.executeUpdate();

         commitAndClose(connection);
      } catch (Exception e) {
         rollbackAndClose(connection);
         throw e;
      }
   }
}
