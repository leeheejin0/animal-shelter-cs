package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.leeheejin.util.Prompt;

public class ManagerValidator {

  public String inputMember(String promptTitle) throws Exception {

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select count(*) from pms_manager where name=?")) {

      while (true) {
        String name = Prompt.inputString(promptTitle);
        if (name.length() == 0) {
          return null;
        } 
        stmt.setString(1, name);

        try (ResultSet rs = stmt.executeQuery()) {
          rs.next();
          if (rs.getInt(1) > 0) {
            return name;
          }
          System.out.println("+-----------------------------+");
          System.out.println("| 등록되지 않은 관리자입니다. |");
          System.out.println("+-----------------------------+");
        }
      }
    }
  }
}






