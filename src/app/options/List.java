package app.options;

import app.exceptions.IllegalOptionArgumentException;
import app.formatters.Formattable;
import app.models.Model;
import app.models.Parser;

import java.sql.SQLException;

public class List extends Option {
   @Override
   public Formattable execute(String[] args) throws SQLException {
      Integer id = parseIdArgument(args, 1, false);
      String tableName = parseStringArgument(args, 0, "entity-name", true);
      Model model = Parser.fromTableName(tableName);

      if (id == null) {
         return model.selectAll();
      } else {
         return model.selectAll(id);
      }
   }

   private String parseTableName(String[] args) {
      if (args.length == 0) {
         throw new IllegalOptionArgumentException("<entity-name> argument required, but not given");
      }

      return args[0];
   }
}
