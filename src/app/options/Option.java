package app.options;

import app.formatters.Formattable;

public abstract class Option {
   public final static String PACKAGE = "app.options";

   public abstract Formattable execute(String[] args);
}
