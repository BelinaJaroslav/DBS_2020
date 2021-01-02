package app.options;

import app.relations.RelationInterface;

import java.util.Arrays;

public class Parser {
   public static RelationInterface parseLine(String line) {
      String[] args = line.split(" +");
      if (args.length == 0 || args[0].isEmpty()) {
         return null;
      }
      String rawOptionName = args[0];
      String name = optionNameFromArg(args[0]);
      args = Arrays.copyOfRange(args, 1, args.length);

      try {
         Class optionClass = Class.forName(name);
         Option option = (Option) optionClass.newInstance();

         return option.execute(args);
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
         throw new RuntimeException("Option `" + rawOptionName + "` is invalid. Try <help>");
      } catch (IllegalArgumentException e) {
         throw new RuntimeException("Invalid parameters for option `" + rawOptionName + "`. Try <help>");
      }
   }

   private static String optionNameFromArg(String arg) {
      char firstLetter = Character.toUpperCase(arg.charAt(0));

      return Option.PACKAGE + "." + firstLetter + arg.substring(1);
   }
}
