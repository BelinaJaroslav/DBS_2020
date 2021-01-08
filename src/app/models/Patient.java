package app.models;

import app.exceptions.DoctorsRecordNotFoundException;
import app.exceptions.BirthNumberAttributeViolatesUniqueConstraintException;
import app.exceptions.RecordNotFoundException;
import app.exceptions.VaccinesRecordNotFoundException;
import app.relations.BasicRelation;
import db.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Patient extends Model {
   public static final String name = "patients";

   public int createNew(
         String name, String surname, Long birth_number, int vaccine_id, int doctor_id,
         Date date, Time time
   ) throws SQLException {
      Connection connection = ConnectionManager.getConnection();

      try (
            PreparedStatement insertPatient = connection.prepareStatement(
                  String.format("INSERT INTO %s (name, surname, birth_number) VALUES (?, ?, ?)", modelName()),
                  Statement.RETURN_GENERATED_KEYS
            );
            PreparedStatement insertVaccination = connection.prepareStatement(
                  String.format(
                        "INSERT INTO %s (patient_id, vaccine_id, doctor_id, completed, time) VALUES (?, ?, ?, 0, ?)",
                        RegisteredVaccination.name
                  )
            )
      ) {
         connection.setAutoCommit(false);

         if (existsRecordForBirthNumber(birth_number)) {
            throw new BirthNumberAttributeViolatesUniqueConstraintException();
         }

         Vaccine vaccine = new Vaccine();
         if (!vaccine.existsRecordForId(vaccine_id)) {
            throw new VaccinesRecordNotFoundException();
         }

         Doctor doctor = new Doctor();
         if (!doctor.existsRecordForId(doctor_id)) {
            throw new DoctorsRecordNotFoundException();
         }

         insertPatient.setString(1, name);
         insertPatient.setString(2, surname);
         insertPatient.setLong(3, birth_number);
         insertPatient.executeUpdate();

         ResultSet resultSet = insertPatient.getGeneratedKeys();
         resultSet.next();
         int patient_id = resultSet.getInt(1);

         insertVaccination.setInt(1, patient_id);
         insertVaccination.setInt(2, vaccine_id);
         insertVaccination.setInt(3, doctor_id);
         insertVaccination.setString(4, date.toString() + " " + time.toString());
         insertVaccination.executeUpdate();

         commitAndClose(connection);

         return patient_id;
      } catch (Exception e) {
         rollbackAndClose(connection);
         throw e;
      }
   }

   public void deleteById(Integer id) throws SQLException {
      Connection connection = ConnectionManager.getConnection();

      try (
            PreparedStatement delete = connection.prepareStatement(
                  String.format("DELETE FROM %s WHERE id = ?", modelName())
            )
      ) {
         connection.setAutoCommit(false);
         if (!existsRecordForId(id)) {
            throw new RecordNotFoundException();
         }

         delete.setInt(1, id);
         delete.execute();

         commitAndClose(connection);
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

   private boolean existsRecordForBirthNumber(Long birth_number) throws SQLException {
      try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement count = connection.prepareStatement(String.format("SELECT COUNT(*) as records_count FROM %s WHERE birth_number = ?", modelName()))
      ) {
         count.setLong(1, birth_number);
         ResultSet resultSet = count.executeQuery();

         resultSet.next();
         int cnt = resultSet.getInt("records_count");

         return cnt > 0;
      }
   }
}
