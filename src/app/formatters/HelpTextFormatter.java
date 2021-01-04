package app.formatters;

public class HelpTextFormatter implements Formattable {
   @Override
   public String format() {
      return "Vaccination register options:\n"
            + String.format("%-80s %s\n", "list (patients | doctors | vaccines | hospitals | hospitals_doctors) [id]", "Lists records in selected entity")
            + String.format("%-80s %s\n", "exit", "Exit the program")
            + String.format("%-80s %s\n", "help", "Shows this help text");
   }
}
