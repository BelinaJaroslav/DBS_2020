package app.options;

import app.exceptions.IllegalOptionArgumentException;
import app.exceptions.NotFoundButNotRequiredArgumentException;
import app.formatters.Formattable;
import app.formatters.PlainMessageFormatter;
import app.models.Patient;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;

public class RegisterPatient extends Option {
   @Override
   public Formattable execute(String[] args) throws ClassNotFoundException, SQLException, IllegalOptionArgumentException {
      String name = parseStringArgument(args, 0, "name", true);
      String surname = parseStringArgument(args, 1, "surname", true);
      Long birth_number = parseLongArgument(args, 2, "birth-number", true);
      Integer vaccine_id = parseIntArgument(args, 3, "vaccine-id", true);
      Integer doctor_id = parseIntArgument(args, 4, "doctor-id", true);
      Date date = parseDateArgument(args, 5, "vaccination-date", true);
      Time time = parseTimeArgument(args, 6, "vaccination-time", true);

      Patient model = new Patient();
      int patient_id = model.createNew(
            name, surname, birth_number, vaccine_id, doctor_id,
            date, time
      );

      return new PlainMessageFormatter(
            String.format("Patient and their registration were created with id %d", patient_id)
      );
   }

   private Long parseLongArgument(String[] args, int index, String name, boolean required) {
      try {
         checkArgumentExistence(args, index, name, required);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      }

      return Long.parseLong(args[index]);
   }

   private Date parseDateArgument(String[] args, int index, String name, boolean required) {
      try {
         checkArgumentExistence(args, index, name, required);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      }

      return Date.valueOf(args[index]);
   }

   private Time parseTimeArgument(String[] args, int index, String name, boolean required) {
      try {
         checkArgumentExistence(args, index, name, required);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      }

      return Time.valueOf(args[index]);
   }
}
