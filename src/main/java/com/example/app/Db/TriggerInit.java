package com.example.app.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Xuan Kong
 * @Date 2019-11-25.
 */
public class TriggerInit {
  JDBCUtils jdbcUtils;
  Connection conn;


  public TriggerInit() {
    createIsoTrigger();
    createWidthTrigger();
    createHeightTrigger();
    createFocalLengthTrigger();
    createFNumberTrigger();
  }

  private void createIsoTrigger() {
    PreparedStatement ps = null;
    String createTrigger = "create trigger checkISO" +
            " before insert on photo for each row" +
            " begin" +
            " if NEW.iso < 25 or new.iso > 1024000 then" +
            " SIGNAL sqlstate '45000'" +
            " set message_text = '[table:photo] - iso is not in the range';" +
            " end if;" +
            " end;";
    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createTrigger);
      ps.execute();
      System.out.println("[table:photo] - checkISO trigger is created");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.getInstance().closeConnection(conn, ps, null);
    }
  }

  private void createWidthTrigger() {
    PreparedStatement ps = null;
    String createTrigger = "create trigger checkWidth" +
            " before insert on photo for each row" +
            " begin" +
            " if NEW.width <= 0 then" +
            " SIGNAL sqlstate '45000'" +
            " set message_text = '[table:photo] - width is negative value';" +
            " end if;" +
            " end;";
    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createTrigger);
      ps.execute();
      System.out.println("[table:photo] - checkWidth trigger is created");

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.getInstance().closeConnection(conn, ps, null);
    }
  }


  private void createHeightTrigger() {
    PreparedStatement ps = null;
    String createTrigger = "create trigger checkHeight" +
            " before insert on photo for each row" +
            " begin" +
            " if NEW.height <= 0 then" +
            " SIGNAL sqlstate '45000'" +
            " set message_text = '[table:photo] - height is negative value';" +
            " end if;" +
            " end;";
    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createTrigger);
      ps.execute();
      System.out.println("[table:photo] - checkHeight trigger is created");

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.getInstance().closeConnection(conn, ps, null);
    }
  }

  private void createFocalLengthTrigger() {
    PreparedStatement ps = null;
    String createTrigger = "create trigger checkFocalLength" +
            " before insert on photo for each row" +
            " begin" +
            " if NEW.focal_length <= 0 then" +
            " SIGNAL sqlstate '45000'" +
            " set message_text = '[table:photo] - focal_length is negative value';" +
            " end if;" +
            " end;";
    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createTrigger);
      ps.execute();
      System.out.println("[table:photo] - checkFocalLength trigger is created");

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.getInstance().closeConnection(conn, ps, null);
    }
  }

    private void createFNumberTrigger() {
    PreparedStatement ps = null;
    String createTrigger = "create trigger checkFNumberLength" +
            " before insert on photo for each row" +
            " begin" +
            " if not NEW.focal_length regexp 'f/[0-9].[0-9]' then" +
            " SIGNAL sqlstate '45000'" +
            " set message_text = '[table:photo] - f_number is invalid';" +
            " end if;" +
            " end;";
    try {
      conn = jdbcUtils.getInstance().getConnection();
      ps = conn.prepareStatement(createTrigger);
      ps.execute();
      System.out.println("[table:photo] - checkFNumber trigger is created");

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jdbcUtils.getInstance().closeConnection(conn, ps, null);
    }
  }
}
