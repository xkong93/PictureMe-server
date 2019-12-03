package com.example.app.Db;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    // mysql params
    private static String jdbcDriver = null;
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static boolean isCfg = false;
    private static JDBCUtils jdbcUtils = null;

    public static synchronized JDBCUtils getInstance() {
        if (jdbcUtils == null) {
            if (!isCfg) {
                initJDBCParameter();
            }
            return jdbcUtils = new JDBCUtils();
        }
        return jdbcUtils;
    }

    /**
     * read mysql properties
     */
    private static void initJDBCParameter() {

        if (jdbcDriver == null || url == null || user == null || password == null) {
            jdbcDriver = "com.mysql.cj.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/PictureMe";
            user = "root";
            password = "123456kx";
            isCfg = true;
        }
    }

    /**
     * get connection
     *
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws SQLException {
//        System.out.println(jdbcDriver);
//        System.out.println(url);
//        Class.forName(jdbcDriver) ??
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * close connection
     *
     * @param con  //
     * @param stmt //
     * @param rs   //
     */
    public void closeConnection(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
