package app.formatters;

public class HelpTextFormatter implements Formattable {
   @Override
   public String format() {
      return "Vaccination register options:\n"
            + String.format("%-110s %s\n", "list (patients | doctors | vaccines | hospitals | hospitals_doctors | registered_vaccinations) [id]", "Lists records in selected entity")
            + String.format("%-110s %s\n", "delete_patient (patient_id)", "Deletes patient and it's registrations from the registry")
            + String.format("%-110s %s\n", "cancel_registration (patient_id)", "Deletes registered vaccination from registry")
            + String.format("%-110s %s\n", "exit", "Exit the program")
            + String.format("%-110s %s\n", "help", "Shows this help text");
   }
}
