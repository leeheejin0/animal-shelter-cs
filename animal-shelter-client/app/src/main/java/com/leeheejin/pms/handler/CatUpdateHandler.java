package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.util.Prompt;

public class CatUpdateHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 고양이 상태 변경 +-+-+");

    int no = Prompt.inputInt("| 번호? ");

    //    String writer = managerValidator.inputManager("| 관리자 이름? ");
    //    if (writer.length() == 0) {
    //      System.out.println("+---------------------------------------+");
    //      System.out.println("| 정보 수정은 관리자 권한이 필요합니다. |");
    //      System.out.println("+---------------------------------------+");
    //      return;
    //    }

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select * from pms_animal_cat where id = ?");
        PreparedStatement stmt2 = con.prepareStatement(
            "update pms_animal_cat set status=? where id=?")) {

      Cat c = new Cat();

      // 1) 기존 데이터 조회
      stmt.setInt(1, no);
      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          System.out.println("+--------------------------------+");
          System.out.println("| 해당 번호의 고양이가 없습니다. |");
          System.out.println("+--------------------------------+");
          return;
        }

        c.setIds(no); 
        c.setStatus(rs.getString("status"));
      }

      // 2) 사용자에게서 변경할 데이터를 입력 받는다.
      c.setStatus(Prompt.inputString(String.format("| 상태(%s)? ", c.getStatus())));


      String input = Prompt.inputString("| 정말 변경하시겠습니까?(y/N) ");
      if (!input.equalsIgnoreCase("Y")) {
        System.out.println("+-----------------------------+");
        System.out.println("| 상태 변경을 취소하였습니다. |");
        System.out.println("+-----------------------------+");
        return;
      }

      // 3) DBMS에게 데이터 변경을 요청한다.
      stmt2.setString(1, c.getStatus());
      stmt2.executeUpdate();

      System.out.println("+------------------------+");
      System.out.println("| 상태를 변경하였습니다. |");
      System.out.println("+------------------------+");
    }
  }
}






