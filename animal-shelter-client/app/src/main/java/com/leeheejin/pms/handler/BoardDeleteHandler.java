package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.leeheejin.util.Prompt;

public class BoardDeleteHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 게시글 삭제 +-+-+");

    int no = Prompt.inputInt("| 번호? ");

    String input = Prompt.inputString("| 정말 삭제하시겠습니까?(y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("+-------------------------------+");
      System.out.println("| 게시글 삭제를 취소하였습니다. |");
      System.out.println("+-------------------------------+");
      return;
    }

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "delete from pms_board where no=?")) {

      stmt.setInt(1, no);
      if (stmt.executeUpdate() == 0) {
        System.out.println("+--------------------------------+");
        System.out.println("| 해당 번호의 게시글이 없습니다. |");
        System.out.println("+--------------------------------+");
      } else {
        System.out.println("+--------------------------+");
        System.out.println("| 게시글을 삭제하였습니다. |");
        System.out.println("+--------------------------+");
      }
    }
  }
}






