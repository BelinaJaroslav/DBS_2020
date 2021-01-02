package app.options;

import app.formatters.Formattable;
import app.formatters.HelpTextFormatter;

public class Help extends Option {
   @Override
   public Formattable execute(String[] args) {
      return new HelpTextFormatter();
   }
}
