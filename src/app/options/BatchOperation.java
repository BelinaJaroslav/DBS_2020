package app.options;

import app.formatters.Formattable;
import app.models.Patient;
import app.relations.BasicRelation;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchOperation extends Option {
   @Override
   public Formattable execute(String[] args) throws SQLException {
      Patient model = new Patient();
      String sql = "SELECT * FROM patients EXCEPT (SELECT p.* FROM patients p INNER JOIN registered_vaccinations rv ON rv.patient_id = p.id WHERE rv.completed = 0)";

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

