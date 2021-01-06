package app.relations;

import app.formatters.Formattable;

import java.util.LinkedList;
import java.util.List;

public class BasicRelation implements Formattable {
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
      String formatUnit = formatUnit();
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

   private String formatUnit() {
      Integer size = sizeOfLargestRecord() + 3;

      return "%-" + size.toString() + "s|";
   }

   private Integer sizeOfLargestRecord() {
      Integer max = 0;

      for (String columnName : this.columnNames) {
         if (max < columnName.length()) {
            max = columnName.length();
         }
      }

      for (List<String> line : this.lines) {
         for (String item : line) {
            if (max < item.length()) {
               max = item.length();
            }
         }
      }

      return max;
   }
}
