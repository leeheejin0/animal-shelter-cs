package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OtherListHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 기타동물 구조목록 +-+-+");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select id,species,photo,breed,age,status from pms_animal_othr order by id asc");
        ResultSet rs = stmt.executeQuery()) {
      System.out.println("+");
      while (rs.next()) {
        System.out.printf("%d, %s, %s, %s, %d, %s\n", 
            rs.getInt("id"), 
            rs.getString("species"),
            rs.getString("photo"), 
            rs.getString("breed"),
            rs.getInt("age"),
            rs.getString("status"));
      }
      System.out.println("+");
    }
  }
}






