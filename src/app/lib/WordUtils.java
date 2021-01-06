package app.lib;

public class WordUtils {
   public static String toCamelCase(String s) {
      String[] parts = s.split("_");
      StringBuilder camelCaseString = new StringBuilder();

      for (String part : parts) {
         camelCaseString.append(toProperCase(part));
      }

      return camelCaseString.toString();
   }

   private static String toProperCase(String s) {
      return s.substring(0, 1).toUpperCase() +
            s.substring(1).toLowerCase();
   }
}
