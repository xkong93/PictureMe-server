package com.example.app.Daos;

import com.example.app.Db.JDBCUtils;
import com.example.app.Models.Photo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Xuan Kong
 * @Date 2019-11-18.
 */
public class PhotoDao {


  public void createPhoto(Photo photo) {
    Connection conn = null;
    PreparedStatement ps = null;
    String sql = "INSERT INTO photo (url,name,create_date,height,width,focal_length,f_number,iso) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setString(1, photo.getUrl());
      ps.setString(2, photo.getName());
      ps.setDate(3, new java.sql.Date(photo.getCreateDate().getTime()));
      ps.setInt(4, photo.getHeight());
      ps.setInt(5, photo.getWidth());
      ps.setInt(6, photo.getFocalLength());
      ps.setString(7, photo.getfNumber());
      ps.setInt(8, photo.getIso());
      ps.executeUpdate();
    } catch (SQLException se) { // key point
      //Handle errors for JDBC
      se.printStackTrace();
      System.out.println(se.getMessage());
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }
  }


  public static void main(String[] args) {
    PhotoDao photoDao = new PhotoDao();
    Photo photo = new Photo();
    photo.setUrl("https://dbs5200.s3-us-west-1.amazonaws.com/IMG_012.jpg");
    photo.setName("IMG_012.jpg");
    photo.setHeight(800);
    photo.setWidth(600);
    photo.setFocalLength(35);
    photo.setfNumber("f/2.5");
    photo.setIso(6400);
    photoDao.createPhoto(photo);
  }


}
