package app.models;

import app.relations.BasicRelation;

import java.sql.SQLException;

public abstract class Model {
   public abstract BasicRelation selectAll() throws SQLException;

   public abstract BasicRelation selectAll(Integer id) throws SQLException;
}
