package com.example.app.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Xuan Kong
 * @Date 2019-11-26.
 */
public class StoredProceduresInit {

  JDBCUtils jdbcUtils;
  Connection conn;

  public StoredProceduresInit() {
    dropAllStoredProcedures();
    initCreateCategory();
    initGetCategoryByName();
  }

  public void dropAllStoredProcedures() {
    conn = null;
    PreparedStatement ps = null;
    String dropStoredProcedure1 = "DROP PROCEDURE IF EXISTS create_category;";
    String dropStoredProcedure2 = "DROP PROCEDURE IF EXISTS get_category_by_name;";
    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(dropStoredProcedure1);
      ps.execute();

      ps = conn.prepareStatement(dropStoredProcedure2);
      ps.execute();

      System.out.println("dropped all procedures");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }

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
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }
  }

  public void initGetCategoryByName() {
    conn = null;
    PreparedStatement ps = null;
    String createStoredProcedure =
            "create procedure get_category_by_name (in c_name varchar(64))" +
                    " begin " +
                    " select * from category where name = c_name; " +
                    " end ";
    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createStoredProcedure);
      ps.execute();
      System.out.println("get_category_by_name stored procedure created");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }
  }

//  public void initGetAllPhotosFromCategoryByName() {
//    conn = null;
//    PreparedStatement ps = null;
//    String createStoredProcedure =
//            "create procedure get_category_by_name (in c_name varchar(64))" +
//                    " begin " +
//                    "  select * from photo p inner join photo_category ps on ps.pid = p.id " +
//                    "inner join category c on c.id = ps.cid where c.name = c_name; ";
//                    "  end ";
//    try {
//      conn = jdbcUtils.getInstance().getConnection();
//      ps = conn.prepareStatement(createStoredProcedure);
//      ps.execute();
//      System.out.println("get_category_by_name stored procedure created");
//    } catch (Exception e) {
//      e.printStackTrace();
//    } finally {
//      JDBCUtils.getInstance().closeConnection(conn, ps, null);
//    }
//  }
}
