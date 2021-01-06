package app;

import app.exceptions.ExitException;
import app.options.Parser;
import app.formatters.Formattable;

import java.util.Scanner;

public class Main {
   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {
      System.out.println(welcomeText());

      while (true) {
         System.out.print("> ");

         try {
            String line = scanner.nextLine();
            Formattable data = Parser.parseLine(line);

            if (data != null) {
               System.out.println(data.format());
            }
         } catch (ExitException e) {
            System.out.println("quiting...");
            break;
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
   }

   public static String welcomeText() {
      return "# # # # # # # # # # # # #\n" +
            "# VACCINATION  REGISTRY #\n" +
            "# # # # # # # # # # # # #\n\n";
   }
}
