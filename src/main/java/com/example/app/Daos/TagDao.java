package com.example.app.Daos;

import com.example.app.Db.JDBCUtils;
import com.example.app.Models.Tag;
import com.example.app.Models.Photo;
import com.example.app.Models.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xuan Kong, Yuting Chen
 * @Date 2019-11-18.
 */
public class TagDao {


 public boolean createTag(Tag tag) {

    Connection conn = null;
    PreparedStatement ps = null;
    String sql = "call create_tag(?,?)";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setDate(1, new java.sql.Date(new Date().getTime()));
      ps.setString(2, tag.getName());
      ps.execute();
    } catch (SQLException se) { // key point
      //Handle errors for JDBC
      System.out.println(se.getMessage());
      return false;
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }
    return true;

  }

  public List<Tag> findAllTags() {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "select * from tag";
    List<Tag> tagList = new ArrayList<>();
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      tagList = new ArrayList<>();
      while (rs.next()) {
        Tag tag = new Tag();
        tag.setTid(rs.getInt("id"));
        tag.setCreateDate(rs.getDate("create_date").toString());
        tag.setName(rs.getString("name"));
        tagList.add(tag);
      }

    } catch (SQLException se) {
      se.getMessage();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, rs);
    }
    return tagList;
  }

  public List<Photo> findAllPhotosFromTagByName(String name) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Photo> photoList = null;
    String sql2 = "select * from photo p inner join photo_tag ps on ps.pid = p.id " +
            "inner join tag c on c.id = ps.tid where c.name = ?";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql2);
      ps.setString(1, name);
      rs = ps.executeQuery();
      photoList = new ArrayList<>();
//      System.out.println(rs.isBeforeFirst());

      while (rs.next()) {
        Photo temp = new Photo();
        temp.setPid(rs.getInt("id"));
        temp.setName(rs.getString("name"));
        temp.setCreateDate(rs.getDate("create_date").toString());
        temp.setHeight(rs.getInt("height"));
        temp.setWidth(rs.getInt("width"));
        temp.setFocalLength(rs.getInt("focal_length"));
        temp.setfNumber(rs.getString("f_number"));
        temp.setIso(rs.getInt("iso"));
        photoList.add(temp);
      }
    } catch (SQLException se) { // key point
      //Handle errors for JDBC
      se.printStackTrace();
      System.out.println(se.getErrorCode());
    } catch (Exception e) {
      //Handle errors for Class.forName
      e.printStackTrace();
    } finally {
      //how to deal with two query for one connection??
      JDBCUtils.getInstance().closeConnection(conn, ps, rs);


    }
    return photoList;

  }

  public Tag findTagByName(String name) {
    String newName = name.toLowerCase();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "call get_tag_by_name(?)";
    Tag tag = new Tag();
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setString(1, newName);
      rs = ps.executeQuery();
      while (rs.next()) {
        tag.setTid(rs.getInt(1));
        tag.setCreateDate(rs.getDate(2).toString());
        tag.setName(rs.getString(3));

      }
    } catch (SQLException se) {
      System.out.println(se.getMessage());
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, rs);
    }
    return tag;

  }

  public boolean updateTag(String newName, String oldName) {//same name
    Connection conn = null;
    PreparedStatement ps = null;
    String sql = "update tag set ";
    StringBuilder sb = new StringBuilder(sql);
    sb.append("name=?");
    sb.append("where name=?");
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sb.toString());
      ps.setString(1, newName);
      ps.setString(2, oldName);
      Integer res = ps.executeUpdate();
      if (res == 0) {
        System.out.println("Tag: " + oldName + " not exists");
        return false;
      }
    } catch (SQLException se) {
      System.out.println(se.getMessage());
      return false;
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }

    return true;

  }

  public boolean deleteTag(String name) {
    Connection conn = null;
    PreparedStatement ps = null;
    String sql = "delete from tag where name=?";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setString(1, name);
      Integer res = ps.executeUpdate();
      if (res == 0) {
        System.out.println("Tag: " + name + " not exists");
        return false;
      }

    } catch (SQLException se) {
      System.out.println(se.getMessage());
      return false;
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }
    return true;
  }

  public boolean addPhotoToTag(String pName, String cName) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    String sql1 = "select id from photo where name=?";
    String sql2 = "select id from tag where name=?";
    String sql3 = "insert into photo_tag (pid,tid) values (?,?)";
    try {

        //get photo id
        conn = JDBCUtils.getInstance().getConnection();
        ps = conn.prepareStatement(sql1);
        ps.setString(1,pName);
        rs1 = ps.executeQuery();
        rs1.next();//move cursor
        Integer pid = rs1.getInt(1);


        //get Tag id
        ps = conn.prepareStatement(sql2);
        ps.setString(1,cName);
        rs2 = ps.executeQuery();
        rs2.next();
        Integer tid = rs2.getInt("id");

        //insert pid tid into photo_cate table
        ps = conn.prepareStatement(sql3);
        ps.setInt(1,pid);
        ps.setInt(2,tid);
        ps.executeUpdate();

    }catch (SQLException se) {
      if (se.getMessage().contains("empty")) {
        System.out.println("Either photo or tag is not found in DBS");
      }
      return false;
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, rs1);
      JDBCUtils.getInstance().closeConnection(conn, ps, rs2);
    }

    return true;
  }
}
