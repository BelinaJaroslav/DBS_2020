package app.options;

import app.exceptions.IllegalOptionArgumentException;
import app.exceptions.NotFoundButNotRequiredArgumentException;
import app.formatters.Formattable;
import lib.WordUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

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

   protected Integer parseIntArgument(String[] args, int index, String name, boolean required) throws IllegalOptionArgumentException {
      try {
         checkArgumentExistence(args, index, name, required);

         return Integer.parseInt(args[index]);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      } catch (NumberFormatException e) {
         throw new IllegalOptionArgumentException(
               String.format("unable to convert %s passed as <%s> argument to a number", args[index], name)
         );
      }
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

   protected Long parseLongArgument(String[] args, int index, String name, boolean required) {
      try {
         checkArgumentExistence(args, index, name, required);

         return Long.parseLong(args[index]);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      } catch (NumberFormatException e) {
         throw new IllegalOptionArgumentException(
               String.format("unable to convert %s passed as <%s> argument to a number", args[index], name)
         );
      }
   }

   protected Date parseDateArgument(String[] args, int index, String name, boolean required) {
      try {
         checkArgumentExistence(args, index, name, required);

         return Date.valueOf(args[index]);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      } catch (IllegalOptionArgumentException e) {
         throw e;
      } catch (IllegalArgumentException e) {
         throw new IllegalOptionArgumentException(
               String.format("unable to convert %s passed as <%s> argument to a date", args[index], name)
         );
      } catch (Exception e) {
         System.out.println(e.getMessage());
         throw e;
      }
   }

   protected Time parseTimeArgument(String[] args, int index, String name, boolean required) {
      try {
         checkArgumentExistence(args, index, name, required);

         return Time.valueOf(args[index]);
      } catch (NotFoundButNotRequiredArgumentException e) {
         return null;
      } catch (IllegalOptionArgumentException e) {
         throw e;
      } catch (IllegalArgumentException e) {
         throw new IllegalOptionArgumentException(
               String.format("unable to convert %s passed as <%s> argument to a time", args[index], name)
         );
      }
   }
}
