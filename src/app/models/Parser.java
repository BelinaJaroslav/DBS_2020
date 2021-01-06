package app.models;

import app.exceptions.IllegalOptionArgumentException;

public class Parser {
   public static Model fromTableName(String tableName) throws IllegalOptionArgumentException {
      switch (tableName) {
         case Patient.name:
            return new Patient();
         case Vaccine.name:
            return new Vaccine();
         case Doctor.name:
            return new Doctor();
         case Hospital.name:
            return new Hospital();
         case HospitalsDoctor.name:
            return new HospitalsDoctor();
         case RegisteredVaccination.name:
            return new RegisteredVaccination();
      }

      throw new IllegalOptionArgumentException("Model `" + tableName + "` is not defined");
   }
}
