package de.msgdavid.crud.database.operation;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection connectionObject;

    public static Connection getConnection() {
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
