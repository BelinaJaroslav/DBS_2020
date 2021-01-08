package app.options;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import app.exceptions.IllegalOptionArgumentException;
import app.exceptions.RecordNotFoundException;
import app.exceptions.RecordWasNotChangedException;
import app.formatters.Formattable;
import app.formatters.PlainMessageFormatter;
import app.models.RegisteredVaccination;

public class ChangeVaccinationTime extends Option {
   @Override
   public Formattable execute(String[] args) throws ClassNotFoundException, SQLException, IllegalOptionArgumentException {
      int id = parseIntArgument(args, 0, "vaccine-id", true);
      Date date = parseDateArgument(args, 1, "vaccination-date", true);
      Time time = parseTimeArgument(args, 2, "vaccination-time", true);
      RegisteredVaccination model = new RegisteredVaccination();
      
      String message;
      try {
         model.setTime(id, date, time);
         message = String.format("Succesfully changed time of the vaccination with id %d.", id);
      } catch (RecordNotFoundException e) {
         message = String.format("Registered vaccination with id %d was not found", id);
      } catch (RecordWasNotChangedException e) {
         message = String.format("Registered vaccination with id %d was not changed.\nIt was already registered for this time.", id);
      }
      
      return new PlainMessageFormatter(message);
   }
}
