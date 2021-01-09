package app.options;

import app.formatters.Formattable;
import app.models.Doctor;
import app.relations.BasicRelation;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectInWhere extends Option {
   @Override
   public Formattable execute(String[] args) throws SQLException {
      Doctor model = new Doctor();
      String sql = "SELECT * FROM doctors WHERE salary > (SELECT AVG(salary) FROM doctors)";

      try (
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement()
      ) {
         ResultSet resultSet = statement.executeQuery(sql);
         BasicRelation relation = model.getBasicRelation();

         while (resultSet.next()) {
            relation.addLine(model.resultSetToList(resultSet));
         }

         return relation;
      }
   }
}
