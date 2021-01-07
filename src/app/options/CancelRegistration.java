package app.options;

import app.exceptions.IllegalOptionArgumentException;
import app.exceptions.RecordDeleteNotAllowed;
import app.exceptions.RecordNotFoundException;
import app.formatters.Formattable;
import app.formatters.PlainMessageFormatter;
import app.models.RegisteredVaccination;

import java.sql.SQLException;

public class CancelRegistration extends Option {
   @Override
   public Formattable execute(String[] args) throws IllegalOptionArgumentException, SQLException {
      Integer id = parseIdArgument(args, 0, true);
      RegisteredVaccination model = new RegisteredVaccination();

      String message = String.format("Registered vaccination with id %d was canceled and deleted from registry", id);
      try {
         model.cancel(id);
      } catch (RecordNotFoundException e) {
         message = String.format("Registered vaccination with id %d was not found", id);
      } catch (RecordDeleteNotAllowed e) {
         message = String.format(
               "Registered vaccination with id %d was not deleted.\nIt's the last registration of that patient.\nDelete the patient instead.",
               id
         );
      }

      return new PlainMessageFormatter(message);
   }
}
