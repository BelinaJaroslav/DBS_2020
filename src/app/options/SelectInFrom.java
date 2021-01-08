package app.options;

import app.formatters.Formattable;
import app.models.Patient;
import app.relations.BasicRelation;
import db.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SelectInFrom extends Option {

    @Override
    public Formattable execute(String[] args) throws SQLException {
        Patient model = new Patient();
        String sql = "SELECT * FROM (SELECT * FROM patients WHERE patients.birth_number > 9000000000) AS p WHERE p.name = 'Lucie'";
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
