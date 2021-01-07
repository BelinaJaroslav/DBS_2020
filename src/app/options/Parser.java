package app.options;

import app.exceptions.IllegalOptionArgumentException;
import app.formatters.Formattable;

import java.sql.SQLException;
import java.util.Arrays;

public class Parser {
   public static Formattable parseLine(String line) {
      String[] args = line.split(" +");
      if (args.length == 0 || args[0].isEmpty()) {
         return null;
      }
      String rawOptionName = args[0];
      args = Arrays.copyOfRange(args, 1, args.length);

      try {
         Option option = Option.nameToObject(rawOptionName);

         return option.execute(args);
      } catch (IllegalOptionArgumentException e) {
         throw new RuntimeException("Arguments for option `" + rawOptionName + "` are invalid: " + e.getMessage() + "\nTry <help>");
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
         throw new RuntimeException("Option `" + rawOptionName + "` is invalid. Try <help>");
      } catch (IllegalArgumentException e) {
         throw new RuntimeException("Invalid parameters for option `" + rawOptionName + "`. Try <help>");
      } catch (SQLException e) {
         throw new RuntimeException(e.getMessage());
      }
   }
}
