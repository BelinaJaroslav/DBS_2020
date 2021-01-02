package app.relations;

import app.formatters.Formattable;

import java.util.LinkedList;
import java.util.List;

public class BasicRelation implements Formattable {
   private static final String formatUnit = "%-20s |";
   private List<List<String>> lines;
   private String[] columnNames;

   public BasicRelation(String... columnNames) {
      this.lines = new LinkedList<>();
      this.columnNames = columnNames;
   }

   public void addLine(List<String> line) {
      this.lines.add(line);
   }

   @Override
   public String format() {
      StringBuilder builder = new StringBuilder();

      if (this.lines.isEmpty()) {
         builder.append((new EmptyRelation()).format());

         return builder.toString();
      }

      builder.append(String.format("%5s  |", ""));
      for (String columnName : this.columnNames) {
         builder.append(String.format(formatUnit, columnName));
      }
      builder.append('\n');

      int lineNumber = 1;
      for (List<String> line : this.lines) {
         builder.append(String.format("%5s. |", Integer.toString(lineNumber)));
         for (String item : line) {
            builder.append(String.format(formatUnit, item));
         }
         builder.append('\n');
         lineNumber++;
      }

      return builder.toString();
   }
}
