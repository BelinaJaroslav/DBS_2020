package app.relations;

import app.formatters.Formattable;

public class NoRecords implements Formattable {
   @Override
   public String format() {
      return "* no records *";
   }
}
