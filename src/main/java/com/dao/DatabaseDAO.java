package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseDAO {
    private Connection conn;

    public DatabaseDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(String tableName, String[] columns, Object[] values) {
        boolean success = false;
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
            for (int i = 0; i < columns.length; i++) {
                sql.append(columns[i]);
                if (i < columns.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(") VALUES (");
            for (int i = 0; i < values.length; i++) {
                sql.append("?");
                if (i < values.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(")");

            PreparedStatement pst = conn.prepareStatement(sql.toString());
            for (int i = 0; i < values.length; i++) {
                pst.setObject(i + 1, values[i]);
            }
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected == 1) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
    public boolean delete(String tableName, String condition) {
        boolean success = false;
        try {
            String sql = "DELETE FROM " + tableName + " WHERE " + condition;
            PreparedStatement pst = conn.prepareStatement(sql);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean update(String tableName, String[] columns, Object[] values, String condition) {
        boolean success = false;
        try {
            StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
            for (int i = 0; i < columns.length; i++) {
                sql.append(columns[i]).append(" = ?");
                if (i < columns.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(" WHERE ").append(condition);
            PreparedStatement pst = conn.prepareStatement(sql.toString());
            for (int i = 0; i < values.length; i++) {
                pst.setObject(i + 1, values[i]);
            }
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public ResultSet select(String tableName, String[] columns, String condition) {
        ResultSet rs = null;
        try {
            StringBuilder sql = new StringBuilder("SELECT ");
            for (int i = 0; i < columns.length; i++) {
                sql.append(columns[i]);
                if (i < columns.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(" FROM ").append(tableName);
            if (condition != null && !condition.isEmpty()) {
                sql.append(" WHERE ").append(condition);
            }
            PreparedStatement pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
