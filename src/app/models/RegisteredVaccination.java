package app.models;

import app.relations.BasicRelation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

public class RegisteredVaccination extends Model {
   public static final String name = "registered_vaccinations";

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
