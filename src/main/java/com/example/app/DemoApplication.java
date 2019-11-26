package com.example.app;

import com.example.app.Db.JDBCUtils;
import com.example.app.Db.TableInit;
import com.example.app.Db.TableInit2;
import com.example.app.Db.TriggerInit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.Statement;



@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) throws Exception {
   SpringApplication.run(DemoApplication.class, args);

  }

}
