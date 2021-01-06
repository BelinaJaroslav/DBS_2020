package app.options;

import app.exceptions.IllegalOptionArgumentException;
import app.exceptions.RecordNotFoundException;
import app.formatters.Formattable;
import app.formatters.PlainMessageFormatter;
import app.models.Patient;

import java.sql.SQLException;

public class DeletePatient extends Option {
   @Override
   public Formattable execute(String[] args) throws IllegalOptionArgumentException, SQLException {
      Integer id = parseIdArgument(args, 0, true);
      Patient model = new Patient();

      String message = String.format("Patient with id %d and it's registrations were removed", id);
      try {
         model.deleteById(id);
      } catch (RecordNotFoundException e) {
         message = String.format("Patient with id %d was not found", id);
      }

      return new PlainMessageFormatter(message);
   }
}
