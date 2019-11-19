package com.example.app.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Xuan Kong
 * @Date 2019-11-14.
 */
public class TableInit {

  JDBCUtils jdbcUtils;
  Connection conn;

  public TableInit() {
    initTagTable();
    initCategoryTable();
    initPhotoTable();
    initPhotoCategoryTable();
    initPhotoTagTable();
  }

  private void initPhotoTable() {
    PreparedStatement ps = null;
    String createTable_Photo =
      "create table if not exists photo("
        + "  id int not null auto_increment,"
        + "  url varchar(64) not null,"
        + "  name varchar(64) not null unique,"
        + "  create_date date,"
        + "  height int,"//unit: pixels
        + "  width int,"
        + "  focal_length varchar(64),"// unit: mm
        + "  f_number varchar(64)," //like f/4.5
        + "  iso int not null,"
        + "  exposure_time varchar(64),"
        + "  latitude double(16,10)," //37.213056
        + "  longitude double(16,10)," //-112.945889
        + "  location varchar (64),"
        + "  manufacturer varchar(64),"
        + "  model varchar(64),"
        + "  primary key (id)"
        + ")";
    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createTable_Photo);
      ps.execute();
      System.out.println("created photo table");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.getInstance().closeConnection(conn, ps, null);
    }
  }

  private void initCategoryTable() {
    PreparedStatement ps = null;
    String createTable_Category =
      "create table if not exists category("
        + " id int not null auto_increment,"
        + " create_date date not null,"
        + " name varchar(64) not null unique,"
        + " primary key (id)"
        + ")";

    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createTable_Category);
      ps.execute();
      System.out.println("created category table");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.getInstance().closeConnection(conn, ps, null);
    }
  }

  private void initTagTable() {
    PreparedStatement ps = null;
    String createTable_Tag =
      "create table if not exists tag("
        + " id int not null auto_increment,"
        + " create_date date not null,"
        + " name varchar(64) not null unique ,"
        + " primary key (id)"
        + ")";

    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createTable_Tag);
      ps.execute();
      System.out.println("created tag table");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.getInstance().closeConnection(conn, ps, null);
    }
  }

  private void initPhotoCategoryTable() {
    PreparedStatement ps = null;
    String createTable_Photo_Category =
      "create table if not exists photo_category("
        + "pid int not null,"
        + "cid int not null,"
        + "foreign key (pid) references photo (id) on delete cascade ,"
        + "foreign key (cid) references category (id)  on delete cascade,"
        + "primary key (cid,pid) "
        + ")";

    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createTable_Photo_Category);
      ps.execute();
      System.out.println("created Photo_Category table");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.getInstance().closeConnection(conn, ps, null);
    }
  }

  private void initPhotoTagTable() {
    PreparedStatement ps = null;
    String createTable_Photo_Tag =
      "create table if not exists photo_tag("
        + "pid int not null,"
        + "tid int not null,"
        + "foreign key (pid) references photo (id) on delete cascade,"
        + "foreign key (tid) references tag (id) on delete cascade,"
        + "primary key (pid,tid) "
        + ")";

    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createTable_Photo_Tag);
      ps.execute();
      System.out.println("created Photo_Tag table");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.getInstance().closeConnection(conn, ps, null);
    }
  }


}
