package app.options;

import app.formatters.Formattable;

import java.sql.SQLException;

public abstract class Option {
   public final static String PACKAGE = "app.options";

   public abstract Formattable execute(String[] args) throws ClassNotFoundException, SQLException;
}
