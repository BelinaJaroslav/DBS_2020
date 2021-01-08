package app.formatters;

public class HelpTextFormatter implements Formattable {
   @Override
   public String format() {
      return "Vaccination register options:\n"
            + String.format("%-130s %s\n", "register_patient (patient-name) (patient-surname) (birth-number) (vaccine-id) (doctor-id) (vaccination-date) (vaccination-time)", "Creates new patient with provided arguments and sets their vaccination on given time. eg. `register_patient hello world  200000000 10 101 2020-01-01 10:00:00`")
            + String.format("%-130s %s\n", "list (patients | doctors | vaccines | hospitals | hospitals_doctors | registered_vaccinations) [id]", "Lists records in selected entity")
            + String.format("%-130s %s\n", "delete_patient (patient_id)", "Deletes patient and it's registrations from the registry")
            + String.format("%-130s %s\n", "cancel_registration (registered_vaccination_id)", "Deletes registered vaccination from registry")
            + String.format("%-130s %s\n", "set_vaccination_completed (registered_vaccination_id)", "Set registered vaccination as completed")
            + String.format("%-130s %s\n", "set_vaccination_incomplete (registered_vaccination_id)", "Set registered vaccination as incomplete")
            + String.format("%-130s %s\n", "change_vaccination_time (registered_vaccination_id) (date) (time)", "Change registered vaccination's date and time")
            + String.format("%-130s %s\n", "select_in_select", "Count of registered vaccinations from every patient")
            + String.format("%-130s %s\n", "select_in_where", "List doctors with higher salary then the average is")
            + String.format("%-130s %s\n", "select_in_from", "List patients with birth number greater then 9000000000 and name 'Lucie'")
            + String.format("%-130s %s\n", "group_by", "Performs a query demonstrating the use of group by")
            + String.format("%-130s %s\n", "batch_operation", "Performs a query demonstrating the use of group by")
            + String.format("%-130s %s\n", "left_join", "Performs a query demonstrating the use of group by")
            + String.format("%-130s %s\n", "exit", "Exit the program")
            + String.format("%-130s %s\n", "help", "Shows this help text");
   }
}
