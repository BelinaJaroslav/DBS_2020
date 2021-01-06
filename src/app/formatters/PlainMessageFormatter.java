package app.formatters;

public class PlainMessageFormatter implements Formattable {
   private String message;

   public PlainMessageFormatter(String message) {
      this.message = message;
   }

   @Override
   public String format() {
      return message;
   }
}
