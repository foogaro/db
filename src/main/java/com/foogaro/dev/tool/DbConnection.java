package com.foogaro.dev.tool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by foogaro
 * To use within Java SE environment.
 * @see SQLClient class in test.
 */
public class DbConnection {

    private static final Logger logger = Logger.getLogger("com.foogaro.dev.tool");

    public Connection openConnection(String driver, String url, String uid, String pwd) {
        logger.info("============== [DbConnection] opening new DB Connection.");
        try {
            Class.forName(driver);
            System.out.println("============== [DbConnection] Driver \"" + driver + "\" loaded.");
            System.out.println("============== [DbConnection] connection parameters [url] [uid]: " + url + " - " + uid);
            try(Connection connection = DriverManager.getConnection(url, uid, pwd)) {
                System.out.println("============== [DbConnection] Connection: " + connection);
                return connection;
            } catch (SQLException e) {
                throw new IllegalStateException("Error while getting connection.");
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Error while loading driver.");
        }
    }

    public void testConnection(String driver, String url, String uid, String pwd) {
        DatabaseMetaData metadata = null;
        try {
            metadata = openConnection(driver, url, uid, pwd).getMetaData();
            System.out.println("============== [DbConnection] DB name            : " + metadata.getDatabaseProductName());
            System.out.println("============== [DbConnection] DB version         : " + metadata.getDatabaseProductVersion());
            System.out.println("============== [DbConnection] jdbc Driver        : " + metadata.getDriverName());
            System.out.println("============== [DbConnection] jdbc Driver version: " + metadata.getDriverVersion());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Error while testing the connection.");
        }
    }

}
