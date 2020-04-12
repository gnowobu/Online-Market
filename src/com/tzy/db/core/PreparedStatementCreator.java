package com.tzy.db.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  create PreparedStatement object
 */
public interface PreparedStatementCreator {
    PreparedStatement createPreparedStatement(Connection conn)throws SQLException;
}
