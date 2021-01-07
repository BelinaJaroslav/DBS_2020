package app.options;

import app.formatters.Formattable;
import app.models.Patient;

import java.sql.SQLException;


public class SelectInFrom extends Option {

    @Override
    public Formattable execute(String[] args) throws SQLException {
        Patient model = new Patient();
        return model.selectInFrom();
    }
}
