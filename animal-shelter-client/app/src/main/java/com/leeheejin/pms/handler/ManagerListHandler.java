package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManagerListHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 관리자 목록 +-+-+");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select  from pms_manager order by no asc");
        ResultSet rs = stmt.executeQuery()) {

      System.out.println("+");
      while (rs.next()) {
        System.out.printf("%s, %s, %s\n", 
            rs.getString("id"), 
            rs.getString("name"), 
            rs.getString("email"));
      }
      System.out.println("+");
    }
  }
}






