package app.options;

import app.exceptions.*;
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
      String message;
      try {
         int patient_id = model.createNew(
               name, surname, birth_number, vaccine_id, doctor_id,
               date, time
         );
         message = String.format("Patient and their registration were created with id %d", patient_id);
      } catch (DoctorsRecordNotFoundException e) {
         message = String.format("Doctor with id %d does not exists", doctor_id);
      } catch (VaccinesRecordNotFoundException e) {
         message = String.format("Vaccine with id %d does not exists", vaccine_id);
      } catch (BirthNumberAttributeViolatesUniqueConstraintException e) {
         message = String.format("Patient with birth-number equal to %d already exists", birth_number);
      }

      return new PlainMessageFormatter(message);
   }

   private Long parseLongArgument(String[] args, int index, String name, boolean required) {
      try {
         checkArgumentExistence(args, index, name, required);

         return Long.parseLong(args[index]);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      } catch (NumberFormatException e) {
         throw new IllegalOptionArgumentException(
               String.format("unable to convert %s passed as <%s> argument to a number", args[index], name)
         );
      }
   }

   private Date parseDateArgument(String[] args, int index, String name, boolean required) {
      try {
         checkArgumentExistence(args, index, name, required);

         return Date.valueOf(args[index]);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      } catch (IllegalArgumentException e) {
         throw new IllegalOptionArgumentException(
               String.format("unable to convert %s passed as <%s> argument to a date", args[index], name)
         );
      }
   }

   private Time parseTimeArgument(String[] args, int index, String name, boolean required) {
      try {
         checkArgumentExistence(args, index, name, required);

         return Time.valueOf(args[index]);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      } catch (IllegalArgumentException e) {
         throw new IllegalOptionArgumentException(
               String.format("unable to convert %s passed as <%s> argument to a time", args[index], name)
         );
      }
   }
}
