package br.com.tempjunior.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;


    private ConnectionFactory(Connection connection) {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = innitConnection();
        } else if (connection.isClosed()) {
            connection = innitConnection();
            return connection;
        }
        return connection;
    }

    private static Connection innitConnection() {
        try {
            return
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/db_produtos_crud", "root", "joselito157");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}