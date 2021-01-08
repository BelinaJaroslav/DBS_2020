package app.options;

import app.exceptions.IllegalOptionArgumentException;
import app.exceptions.RecordNotFoundException;
import app.exceptions.RecordWasNotChangedException;
import app.formatters.Formattable;
import app.formatters.PlainMessageFormatter;
import app.models.RegisteredVaccination;

import java.sql.SQLException;

public class SetVaccinationIncomplete extends Option{
   @Override
   public Formattable execute(String[] args) throws ClassNotFoundException, SQLException, IllegalOptionArgumentException {
      Integer id = parseIdArgument(args, 0, true);
      RegisteredVaccination model = new RegisteredVaccination();

      String message = String.format("Registered vaccination with id %d was set as incomplete", id);
      try {
         model.setIncomplete(id);
      } catch (RecordNotFoundException e) {
         message = String.format("Registered vaccination with id %d was not found", id);
      } catch (RecordWasNotChangedException e) {
         message = String.format("Registered vaccination with id %d was not changed.\nIt had been not completed yet.", id);
      }

      return new PlainMessageFormatter(message);
   }
}
