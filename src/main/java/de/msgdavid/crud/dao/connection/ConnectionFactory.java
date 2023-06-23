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
            Class.forName("com.mysql.cj.jdbc.Driver");
            String db_url = "jdbc:mysql://localhost:3306/demo",
                    db_userName = "root",
                    db_password = "password123";
            connectionObject = DriverManager.getConnection(db_url,db_userName,db_password);
            System.out.println("JDBC Connection successful");
        } catch (Exception sqlException) {
            System.out.println("JDBC Connection failed!");
            sqlException.printStackTrace();
        }
        return connectionObject;
    }
}
