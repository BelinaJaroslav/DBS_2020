package app.relations;

import app.formatters.Formattable;

public class EmptyRelation implements Formattable {
   @Override
   public String format() {
      return "* no records *";
   }
}
