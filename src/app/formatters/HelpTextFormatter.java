package app.formatters;

public class HelpTextFormatter implements Formattable {
   @Override
   public String format() {
      return "Vaccination register options:\n"
            + String.format("%-80s %s\n", "list <entity-name> [id]", "Lists records in selected entity")
            + String.format("%-80s %s\n", "exit", "Exit the program")
            + String.format("%-80s %s\n", "help", "Shows this help text");
   }
}
