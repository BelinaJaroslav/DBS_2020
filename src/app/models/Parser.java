package app.models;

import app.exceptions.IllegalOptionArgumentException;

public class Parser {
   public static Model fromTableName(String tableName) throws ClassNotFoundException {
      switch (tableName) {
         case Patient.name:
            return new Patient();
         case Vaccine.name:
            return new Vaccine();
         case Doctor.name:
            return new Doctor();
         case Hospital.name:
            return new Hospital();
      }

      throw new IllegalOptionArgumentException("Model `" + tableName + "` is not defined");
   }
}
