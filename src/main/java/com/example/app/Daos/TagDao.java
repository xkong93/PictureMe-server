package com.example.app.Daos;

import com.example.app.Db.JDBCUtils;
import com.example.app.Models.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Xuan Kong
 * @Date 2019-11-18.
 */
public class TagDao {


  public void createTag(Tag tag) {

    Connection conn = null;

    PreparedStatement ps = null;
    String sql = "insert into tag(create_date,name) values (?,?)";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setDate(1, new java.sql.Date(tag.getCreateDate().getTime()));
      ps.setString(2, tag.getName());
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }

  }
}
