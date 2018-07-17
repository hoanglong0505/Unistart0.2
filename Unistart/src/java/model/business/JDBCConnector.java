/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author TNT
 */
public class JDBCConnector {

    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123456";

    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    //============================
    public static String getURL() {
        return "jdbc:sqlserver://localhost:1433;databaseName=unistart2";
    }

    public static final Connection connect() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(getURL(), USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
