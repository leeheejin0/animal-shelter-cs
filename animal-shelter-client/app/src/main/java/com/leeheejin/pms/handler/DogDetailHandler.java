package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.leeheejin.util.Prompt;

public class DogDetailHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 개 상세보기 +-+-+");

    int id = Prompt.inputInt("| 번호? ");

    try (Connection con = DriverManager.getConnection( //
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement( //
            "select * from pms_animal_dog where id = ?")) {

      stmt.setInt(1, id);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          System.out.println("+----------------------------+");
          System.out.println("| 해당 번호의 개가 없습니다. |");
          System.out.println("+----------------------------+");
          return;
        }
        System.out.println("+");
        System.out.printf("| 사진: %s\n", rs.getString("photo"));
        System.out.printf("| 품종: %s\n", rs.getString("breed"));
        System.out.printf("| 성별: %s\n", rs.getString("gender"));
        System.out.printf("| 나이: %s\n", rs.getString("age"));
        System.out.printf("| 구조일: %s\n", rs.getDate("date"));
        System.out.printf("| 구조장소: %s\n", rs.getString("place"));
        System.out.printf("| 상태: %s\n", rs.getString("status"));
        System.out.println("+");
      }
    }
  }
}






