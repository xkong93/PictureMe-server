package com.example.app.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Xuan Kong
 * @Date 2019-11-26.
 */
public class StoredProcedures {

  JDBCUtils jdbcUtils;
  Connection conn;

  public StoredProcedures() {
    initCreateCategory();
  }

  public void initCreateCategory() {

    conn = null;
    PreparedStatement ps = null;

    String createStoredProcedure =
            " CREATE PROCEDURE create_category(in c_createDate date, in c_name varchar(64)) " +
            " begin " +
            " insert into category (create_date, name) values (c_createDate,c_name); " +
            " end ";
    try {
     conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createStoredProcedure);
      ps.execute();
      System.out.println("create_category stored procedure created");
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);

    }
  }
}
