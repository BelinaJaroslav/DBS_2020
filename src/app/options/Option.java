package app.options;

import app.exceptions.IllegalOptionArgumentException;
import app.exceptions.NotFoundButNotRequiredArgumentException;
import app.formatters.Formattable;
import app.lib.WordUtils;

import java.sql.SQLException;

public abstract class Option {
   public final static String PACKAGE = "app.options";

   public static Option nameToObject(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
      Class optionClass = Class.forName(classNameFromRaw(name));

      return (Option) optionClass.newInstance();
   }

   public abstract Formattable execute(String[] args) throws ClassNotFoundException, SQLException, IllegalOptionArgumentException;

   private static String classNameFromRaw(String str) {
      return Option.PACKAGE + "." + WordUtils.toCamelCase(str);
   }

   protected Integer parseIdArgument(String[] args, int index, boolean required) throws IllegalOptionArgumentException {
      return parseIntArgument(args, index, "id", required);
   }


   protected Integer parseIntArgument(String[] args, int index, String name, boolean required) {
      try {
         checkArgumentExistence(args, index, name, required);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      }

      return Integer.parseInt(args[index]);
   }

   protected String parseStringArgument(String[] args, int index, String name, boolean required) {
      try {
         checkArgumentExistence(args, index, name, required);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      }

      return args[index];
   }

   protected void checkArgumentExistence(
         String[] args, int index, String name, boolean required
   ) throws IllegalOptionArgumentException, NotFoundButNotRequiredArgumentException {
      if (args.length < index + 1) {
         if (required) {
            throw new IllegalOptionArgumentException(String.format("<%s> argument required, but not given", name));
         } else {
            throw new NotFoundButNotRequiredArgumentException();
         }
      }
   }
}
