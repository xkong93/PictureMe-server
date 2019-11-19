package com.example.app.Daos;

import com.example.app.Db.JDBCUtils;
import com.example.app.Models.Category;
import com.example.app.Models.Photo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.stream.Location;

/**
 * @author Xuan Kong
 * @Date 2019-11-17.
 */
public class CategoryDao {

  public void createCategory(Category category) {
    Connection conn = null;
    PreparedStatement ps = null;
    String sql = "insert into category(create_date,name) values (?,?)";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setDate(1, new java.sql.Date(category.getCreateDate().getTime()));
      ps.setString(2, category.getName());
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }

  }


  //to do
  public Category findCategoryByName(String name) {
    Connection conn = null;
    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    Category c = null;

    String sql = "select * from category where name=?";

    String sql2 = "select * from photo p inner join photo_category ps on ps.pid = p.id " +
            "inner join category c on c.id = ps.cid where c.name = ?";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps1 = conn.prepareStatement(sql);
      ps1.setString(1, name);
      rs = ps1.executeQuery();
      if (rs.next()) {
        c = new Category();
        c.setName(rs.getString("name"));
        c.setCreateDate(rs.getDate("create_date"));
      }

      conn = JDBCUtils.getInstance().getConnection();
      ps2 = conn.prepareStatement(sql2);
      ps2.setString(1, name);
      rs2 = ps2.executeQuery();
      List<Photo> photoList = new ArrayList<>();


      while(rs2.next()) {
          Photo temp = new Photo();
          temp.setIso(rs2.getInt("iso"));
          photoList.add(temp);
      }
      c.setPhotoList(photoList);


    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps1, rs);
      //how to deal with two quest for one connection??
      JDBCUtils.getInstance().closeConnection(conn, ps2, rs2);


    }
    return c;

  }

  public void updateCategory(String newName, String oldName) {//same name
    Connection conn = null;
    PreparedStatement ps = null;
    if (newName.equals(oldName)) {// if two names are identical, return.
      return;
    }
    String sql = "update category set ";
    StringBuilder sb = new StringBuilder(sql);
    sb.append("name=?");
    sb.append("where name=?");
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sb.toString());
      ps.setString(1, newName);
      ps.setString(2, oldName);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }

  }


  public void deleteCategory(String name) {
    Connection conn = null;
    PreparedStatement ps = null;
    String sql = "delete from category where name=?";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setString(1, name);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }
  }


  //test CRUD for CategoryDao
  public static void main(String[] args) {
    Category category = new Category();
    Date date = new Date();
    date.getTime();
    category.setCreateDate(date);
    category.setName("home");
    CategoryDao categoryDao = new CategoryDao();
//    categoryDao.createCategory(category);
//    categoryDao.deleteCategory("home");
//    categoryDao.updateCategory("newHome","home");
      Category c = categoryDao.findCategoryByName("newHome");
      List<Photo> photos = c.getPhotoList();
      for(Photo p : photos) {
        System.out.println(p.getIso());
      }
    System.out.println(c.getName());
//    System.out.println(c.getName());
//    System.out.println(c.getCreateDate());
//    System.out.println(category.getCreateDate());
  }
}
