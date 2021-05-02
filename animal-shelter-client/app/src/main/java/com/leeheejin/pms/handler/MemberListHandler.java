package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberListHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 회원 목록 +-+-+");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select no,id,name,email from pms_member order by no asc");
        ResultSet rs = stmt.executeQuery()) {

      System.out.println("+");
      while (rs.next()) {
        System.out.printf("%d, %s, %s, %s\n", 
            rs.getInt("no"),
            rs.getString("id"), 
            rs.getString("name"), 
            rs.getString("email"));
      }
      System.out.println("+");
    }
  }
}






