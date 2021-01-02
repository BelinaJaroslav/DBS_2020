package app.options;

import app.exceptions.ExitException;
import app.formatters.Formattable;

public class Exit extends Option {
   @Override
   public Formattable execute(String[] args) {
      throw new ExitException();
   }
}
