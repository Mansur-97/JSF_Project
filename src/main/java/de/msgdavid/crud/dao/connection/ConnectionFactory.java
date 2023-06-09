package de.msgdavid.crud.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final ConnectionFactory connectionFactory = new ConnectionFactory();
    private ConnectionFactory() {

    }
    public static ConnectionFactory getInstance() {
        return connectionFactory;
    }
    public Connection getConnection() {
        Connection connectionObject = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db_url = "jdbc:mysql://localhost:3306/movies",
                    db_userName = "root",
                    db_password = "password123";
            connectionObject = DriverManager.getConnection(db_url,db_userName,db_password);
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return connectionObject;
    }
}
