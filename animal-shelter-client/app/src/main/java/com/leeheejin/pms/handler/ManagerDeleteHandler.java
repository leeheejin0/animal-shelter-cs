package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.leeheejin.util.Prompt;

public class ManagerDeleteHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 관리자 삭제 +-+-+");

    int no = Prompt.inputInt("| 번호? ");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "delete from pms_manager where no=?")) {

      stmt.setInt(1, no);
      if (stmt.executeUpdate() == 0) {
        System.out.println("+--------------------------------+");
        System.out.println("| 해당 번호의 관리자가 없습니다. |");
        System.out.println("+--------------------------------+");
      } else {
        System.out.println("+--------------------------+");
        System.out.println("| 관리자를 삭제하였습니다. |");
        System.out.println("+--------------------------+");
      }
    }
  }
}






