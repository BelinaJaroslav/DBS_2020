package app.models;

public class Parser {
   public static Model fromTableName(String tableName) throws ClassNotFoundException {
      switch (tableName) {
         case "users":
            return new User();
      }

      throw new ClassNotFoundException();
   }
}
