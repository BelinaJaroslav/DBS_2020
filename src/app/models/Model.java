package app.models;

import app.relations.BasicRelation;
import db.ConnectionManager;
import jdk.nashorn.internal.runtime.ECMAException;

import java.sql.*;
import java.util.List;

public abstract class Model {
   public BasicRelation selectAll() throws SQLException {
      BasicRelation relation = getBasicRelation();

      try (Connection connection = ConnectionManager.getConnection()) {
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", modelName()));

         while (resultSet.next()) {
            relation.addLine(resultSetToList(resultSet));
         }
      }

      return relation;
   }

   public BasicRelation selectAll(Integer id) throws SQLException {
      BasicRelation relation = getBasicRelation();

      try (
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE id = ?", modelName()))
      ) {
         preparedStatement.setInt(1, id);
         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
            relation.addLine(resultSetToList(resultSet));
         }
      }

      return relation;
   }

   public boolean deleteById(Integer id) throws SQLException {
      Connection connection = ConnectionManager.getConnection();

      try (

            PreparedStatement count = connection.prepareStatement(String.format("SELECT COUNT(*) AS records_count FROM %s WHERE id = ?", modelName()));
            PreparedStatement delete = connection.prepareStatement(String.format("DELETE FROM %s WHERE id = ?", modelName()))
      ) {
         connection.setAutoCommit(false);
         count.setInt(1, id);
         ResultSet resultSet = count.executeQuery();

         resultSet.next();
         int cnt = resultSet.getInt("records_count");

         if (cnt == 0) {
            return false;
         }

         delete.setInt(1, id);
         delete.execute();
         connection.commit();
      } catch (Exception e) {
         rollbackAndClose(connection);
         throw e;
      }

      return true;
   }

   private void rollbackAndClose(Connection connection) {
      try {
         connection.rollback();
         connection.close();
      } catch (SQLException _e) {
         // noop
      }
   }

   protected abstract List<String> resultSetToList(ResultSet resultSet) throws SQLException;

   protected abstract String modelName();

   protected abstract BasicRelation getBasicRelation();
}
