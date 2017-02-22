package com.foogaro.dev.tool;

public class SQLClient {

    public static void main(String[] args) {
        try {
            (new DbConnection()).testConnection(Env.getProperty(Consts.JDBC_DRIVER), Env.getProperty(Consts.JDBC_URL), Env.getProperty(Consts.JDBC_UID), Env.getProperty(Consts.JDBC_PWD));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
