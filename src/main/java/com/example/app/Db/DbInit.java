package com.example.app.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Xuan Kong
 * @Date 2019-11-26.
 */
public class DbInit {
  // JDBC driver name and database URL
  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost/";

  //  Database credentials
  static final String USER = "root";//by default
  static final String PASS = "123456kx";//by default

  public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    try {
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      System.out.println("Creating database...");
      stmt = conn.createStatement();

      String sql = "CREATE DATABASE IF NOT EXISTS PictureMe ";
      stmt.executeUpdate(sql);
      System.out.println("Database created successfully...");
    } catch (SQLException se) {
      //Handle errors for JDBC
      se.printStackTrace();
    } catch (Exception e) {
      //Handle errors for Class.forName
      e.printStackTrace();
    } finally {
      //finally block used to close resources
      try {
        if (stmt != null)
          stmt.close();
      } catch (SQLException se2) {
      }// nothing we can do
      try {
        if (conn != null)
          conn.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }//end finally try
    }//end try
    TableInit tableInit = new TableInit();
    TriggerInit triggerInit = new TriggerInit();
    StoredProceduresInit storedProceduresInit = new StoredProceduresInit();
    TestDataInit testDataInit = new TestDataInit();
    testDataInit.insertPhotoTestData();
    testDataInit.insertCategoryTestData();
    testDataInit.insertPhotoCategoryTestData();
    testDataInit.insertTagTestData();
    testDataInit.insertPhotoTagTestData();
  }
}
