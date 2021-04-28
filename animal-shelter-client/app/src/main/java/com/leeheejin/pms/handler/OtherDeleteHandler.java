package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.leeheejin.util.Prompt;

public class OtherDeleteHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 기타동물 삭제 +-+-+");

    int id = Prompt.inputInt("| 번호? ");

    String input = Prompt.inputString("| 정말 삭제하시겠습니까?(y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("+---------------------------------+");
      System.out.println("| 기타동물 삭제를 취소하였습니다. |");
      System.out.println("+---------------------------------+");
      return;
    }

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "delete from pms_animal_othr where id=?")) {

      stmt.setInt(1, id);
      if (stmt.executeUpdate() == 0) {
        System.out.println("+----------------------------------+");
        System.out.println("| 해당 번호의 기타동물이 없습니다. |");
        System.out.println("+----------------------------------+");
      } else {
        System.out.println("+----------------------------+");
        System.out.println("| 기타동물을 삭제하였습니다. |");
        System.out.println("+----------------------------+");
      }
    }
  }
}






