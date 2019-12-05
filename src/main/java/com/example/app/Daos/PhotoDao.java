package com.example.app.Daos;

import com.example.app.Db.JDBCUtils;
import com.example.app.Models.Photo;

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
public class PhotoDao {


    /**
   * Created a photo.
   * @param photo
   * @return true if the photo is successfully created.
   */
  public boolean createPhoto(Photo photo) {
    Connection conn = null;
    PreparedStatement ps = null;
    String sql = "INSERT INTO photo (url,name,create_date,height,width,focal_length,f_number,iso) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setString(1, photo.getUrl());
      ps.setString(2, photo.getName());
      ps.setDate(3, new java.sql.Date(new Date().getTime()));
      ps.setInt(4, photo.getHeight());
      ps.setInt(5, photo.getWidth());
      ps.setInt(6, photo.getFocalLength());
      ps.setString(7, photo.getfNumber());
      ps.setInt(8, photo.getIso());
      ps.executeUpdate();

    } catch (SQLException se) { // key point
      //Handle errors for JDBC

      System.out.println(se.getMessage());
      return false;
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }
    return true;
  }

    /**
   * Find the photo with a photo name.
   * @param name
   * @return the photo if found
   */
  public Photo findPhotoByName(String name) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Photo photo = new Photo();
    String sql = "select * from photo where name=?";

    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setString(1, name);
      rs = ps.executeQuery();
      while (rs.next()) {
        photo.setPid(rs.getInt("id"));
        photo.setName(rs.getString("name"));
        photo.setCreateDate(rs.getDate("create_date").toString());
        photo.setHeight(rs.getInt("height"));
        photo.setHeight(rs.getInt("width"));
        photo.setFocalLength(rs.getInt("focal_length"));
        photo.setfNumber(rs.getString("f_number"));
      }

    } catch (SQLException se) {
      se.getMessage();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, rs);
    }
    return photo;
  }

  /**
   * Find all photos stored in database.
   * @return a list of photos
   */
  public List<Photo> findAllPhotos() {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "select * from photo";
    List<Photo> photoList = new ArrayList<>();
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      photoList = new ArrayList<>();
      while (rs.next()) {
        Photo temp = new Photo();
        temp.setPid(rs.getInt("id"));
        temp.setName(rs.getString("name"));
        temp.setUrl();
        temp.setCreateDate(rs.getDate("create_date").toString());
        temp.setHeight(rs.getInt("height"));
        temp.setWidth(rs.getInt("width"));
        temp.setFocalLength(rs.getInt("focal_length"));
        temp.setfNumber(rs.getString("f_number"));
        temp.setIso(rs.getInt("iso"));
        photoList.add(temp);
      }

    } catch (SQLException se) {
      se.getMessage();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, rs);
    }
    return photoList;
  }

    /**
   * Delete a photo.
   * @param name
   * @return true if the photo is successfully deleted,
   * false if the photo is not found.
   */
  public boolean deletePhoto(String name) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Photo photo = new Photo();

    String sql = "delete from photo where name=?";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setString(1, name);
      ps.executeUpdate();

      while (rs.next()) {
        photo.setPid(rs.getInt("id"));
        photo.setName(rs.getString("name"));
        photo.setCreateDate(rs.getDate("create_date").toString());
        photo.setHeight(rs.getInt("height"));
        photo.setHeight(rs.getInt("width"));
        photo.setFocalLength(rs.getInt("focal_length"));
        photo.setfNumber(rs.getString("f_number"));
      }
    } catch (SQLException se) {
      System.out.println(se.getMessage());
      return false;
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }
    return true;

  }

    /**
   * Update the name of a photo.
   * @param newName
   * @param oldName
   * @return true if the photo is successfully updated,
   * false if the photo is not found.
   */
  public boolean updatePhotoName(String newName, String oldName) {//same name
    Connection conn = null;
    PreparedStatement ps = null;
    String sql = "update photo set ";
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
        System.out.println("Photo: " + oldName + " not exists");
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


//  public static void main(String[] args) {
//    PhotoDao photoDao = new PhotoDao();
////    Photo photo = new Photo();
////    photo.setName("IMG_212.jpg");
////    photo.setHeight(800);
////    photo.setWidth(600);
////    photo.setFocalLength(35);
////    photo.setfNumber("f/2.5");
////    photo.setIso(6400);
////    photoDao.createPhoto(photo);
//    List<Photo> photoList = photoDao.findAllPhotos();
//    for (Photo p : photoList) {
//      System.out.println(p.getName());
//    }
//  }


}
