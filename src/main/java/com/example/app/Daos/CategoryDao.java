package com.example.app.Daos;

import com.example.app.Db.JDBCUtils;
import com.example.app.Models.Category;
import com.example.app.Models.Photo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xuan Kong
 * @Date 2019-11-17.
 */
public class CategoryDao {

  //CRUD
//  public void createCategory(Category category) {
//    Connection conn = null;
//    PreparedStatement ps = null;
//    String sql = "insert into category(create_date,name) values (?,?)";
//    try {
//      conn = JDBCUtils.getInstance().getConnection();
//      ps = conn.prepareStatement(sql);
//      ps.setDate(1, new java.sql.Date(category.getCreateDate().getTime()));
//      ps.setString(2, category.getName());
//      ps.executeUpdate();
//    } catch (Exception e) {
//      e.printStackTrace();
//    } finally {
//      JDBCUtils.getInstance().closeConnection(conn, ps, null);
//    }
//
//  }

  public boolean createCategory(Category category) {
    Connection conn = null;
    PreparedStatement ps = null;
    String sql = "call create_category(?,?)";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setDate(1, new java.sql.Date(new Date().getTime()));
      ps.setString(2, category.getName());
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

  public List<Category> findAllCategories() {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "select * from category";
    List<Category> categoryList = new ArrayList<>();
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      categoryList = new ArrayList<>();
      while (rs.next()) {
        Category category = new Category();
        category.setCid(rs.getInt("id"));
        category.setCreateDate(rs.getDate("create_date").toString());
        category.setName(rs.getString("name"));
        categoryList.add(category);
      }

    } catch (SQLException se) {
      se.getMessage();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, rs);
    }
    return categoryList;
  }

  public List<Photo> findAllPhotosFromCategoryByName(String name) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Photo> photoList = null;
    String sql2 = "select * from photo p inner join photo_category ps on ps.pid = p.id " +
            "inner join category c on c.id = ps.cid where c.name = ?";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql2);
      ps.setString(1, name);
      rs = ps.executeQuery();
      photoList = new ArrayList<>();

      while (rs.next()) {
        Photo temp = new Photo();
        temp.setPid(rs.getInt("id"));
        temp.setName(rs.getString("name"));
        temp.setCreateDate(rs.getDate("create_date").toString());
        temp.setHeight(rs.getInt("height"));
        temp.setHeight(rs.getInt("width"));
        temp.setFocalLength(rs.getInt("focal_length"));
        temp.setfNumber(rs.getString("f_number"));
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

  public Category findCategoryByName(String name) {
    String newName = name.toLowerCase();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "call get_category_by_name(?)";
    Category category = new Category();
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setString(1, newName);
      rs = ps.executeQuery();
      while (rs.next()) {
        category.setCid(rs.getInt(1));
        category.setCreateDate(rs.getDate(2).toString());
        category.setName(rs.getString(3));

      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, rs);
    }
    return category;

  }

  public boolean updateCategory(String newName, String oldName) {//same name
    Connection conn = null;
    PreparedStatement ps = null;
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
    } catch (SQLException se) {
      System.out.println(se.getMessage());
      return false;
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }

    return true;

  }

  public boolean deleteCategory(String name) {
    Connection conn = null;
    PreparedStatement ps = null;
    String sql = "delete from category where name=?";
    try {
      conn = JDBCUtils.getInstance().getConnection();
      ps = conn.prepareStatement(sql);
      ps.setString(1, name);
      ps.executeUpdate();
    } catch (SQLException se) {
      System.out.println(se.getMessage());
      return false;
    } finally {
      JDBCUtils.getInstance().closeConnection(conn, ps, null);
    }
    return true;
  }


  //test CRUD for CategoryDao
  public static void main(String[] args) {
//    Category category = new Category();
//    Date date = new Date();
//    date.getTime();
//    category.setCreateDate(date);
//    category.setName("fasion");
    CategoryDao categoryDao = new CategoryDao();
//    categoryDao.createCategory(category);
//    categoryDao.deleteCategory("home");
//    categoryDao.updateCategory("newHome","home");
//    List<Photo> photoList = categoryDao.findAllPhotosFromCategoryByName("newHome");
//    for (Photo p : photoList) {
//      System.out.println(p.getIso());
//    }
//    System.out.println(c.getName());
//    System.out.println(c.getCreateDate());
//    System.out.println(category.getCreateDate());
    List<Photo> photoList;
    photoList = categoryDao.findAllPhotosFromCategoryByName("travel");
    for (Photo p : photoList) {
      System.out.println(p.getName() + p.getCreateDate());
    }
//
//    Category c = categoryDao.findCategoryByName("dsd");
//    System.out.println(c.getName() + c.getCreateDate());

  }
}
