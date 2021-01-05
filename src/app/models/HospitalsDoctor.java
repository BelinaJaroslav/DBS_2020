package app.models;

import app.relations.BasicRelation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class HospitalsDoctor extends Model {
   public static final String name = "hospitals_doctors";

   @Override
   protected List<String> resultSetToList(ResultSet resultSet) throws SQLException {
      int id = resultSet.getInt("id");
      int hid = resultSet.getInt("hospital_id");
      int did = resultSet.getInt("doctor_id");
      LinkedList<String> list = new LinkedList<>();

      list.add(Integer.toString(id));
      list.add(Integer.toString(hid));
      list.add(Integer.toString(did));

      return list;
   }

   @Override
   protected String modelName() {
      return name;
   }

   @Override
   protected BasicRelation getBasicRelation() {
      return new BasicRelation(
            "hospitals_doctor_id",
            "hospitals_doctor_hospital_id",
            "hospitals_doctor_doctor_id"
      );
   }
}
