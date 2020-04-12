package com.tzy.db.core;

import java.sql.*;

public class JdbcTemplate {

    /**
     * Query
     * @param pscreator create Statement
     * @param callbackHandler create ResultSet
     * @throws DataAccessException
     */
    public void query(PreparedStatementCreator pscreator,
                      RowCallbackHandler callbackHandler) throws DataAccessException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBHelp.getConnection();
            preparedStatement = pscreator.createPreparedStatement(connection);
            resultSet = preparedStatement.executeQuery();

            //traverse ResultSet
            while (resultSet.next()) {
                callbackHandler.processRow(resultSet);
            }

        } catch (SQLException e) {
            throw new DataAccessException("JdbcTemplate_SQLException", e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("JdbcTemplate_ClassNotFoundException", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DataAccessException("JdbcTemplate_DatabaseConnection failed to release", e);
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DataAccessException("JdbcTemplate_Statement failed to release", e);
                }
            }

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DataAccessException("JdbcTemplate_ResultSet failed to release", e);
                }
            }
        }

    }

    /**
     * Update
     * @param pscreator
     * @throws DataAccessException
     */
    public void update(PreparedStatementCreator pscreator)
            throws DataAccessException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBHelp.getConnection();
            preparedStatement = pscreator.createPreparedStatement(connection);
            int count = preparedStatement.executeUpdate();
            System.out.println(count + "row(s) affected");

        } catch (SQLException e) {
            throw new DataAccessException("JdbcTemplate_SQLException", e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("JdbcTemplate_ClassNotFoundException", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DataAccessException("JdbcTemplate_DatabaseConnection failed to release", e);
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DataAccessException("JdbcTemplate_Statement failed to release", e);
                }
            }
        }
    }
}
