package com.tzy.db.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSet Object
 */
public interface RowCallbackHandler {
    void processRow(ResultSet rs) throws SQLException;
}
