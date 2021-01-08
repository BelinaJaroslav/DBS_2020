package app.options;

import app.formatters.Formattable;
import app.models.Doctor;

import java.sql.SQLException;


public class SelectInWhere extends Option {

    @Override
    public Formattable execute(String[] args) throws SQLException {
        Doctor model = new Doctor();
        return model.selectInWhere();
    }
}
