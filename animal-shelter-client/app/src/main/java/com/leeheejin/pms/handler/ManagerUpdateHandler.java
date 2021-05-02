package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.leeheejin.pms.domain.Manager;
import com.leeheejin.util.Prompt;

public class ManagerUpdateHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 관리자 변경 +-+-+");

    int no = Prompt.inputInt("| 번호? ");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select * from pms_manager where no = ?");
        PreparedStatement stmt2 = con.prepareStatement(
            "update pms_manager set id=?,name=?,email=?,photo=?,password=password(?),tel=? where no=?")) {

      Manager m = new Manager();

      // 1) 기존 데이터 조회
      stmt.setInt(1, no);
      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          System.out.println("+--------------------------------+");
          System.out.println("| 해당 번호의 관리자가 없습니다. |");
          System.out.println("+--------------------------------+");
          return;
        }

        m.setNo(no); 
        m.setId(rs.getString("id"));
        m.setName(rs.getString("name"));
        m.setEmail(rs.getString("email"));
        m.setPhoto(rs.getString("photo"));
        m.setTel(rs.getString("tel"));
      }

      // 2) 사용자에게서 변경할 데이터를 입력 받는다.
      m.setId(Prompt.inputString(String.format("| 아이디(%s)? ", m.getId())));
      m.setName(Prompt.inputString(String.format("| 이름(%s)? ", m.getName())));
      m.setEmail(Prompt.inputString(String.format("| 이메일(%s)? ", m.getEmail())));
      m.setPhoto(Prompt.inputString(String.format("| 사진(%s)? ", m.getPhoto())));
      m.setPassword(Prompt.inputString("| 새암호? "));
      m.setTel(Prompt.inputString(String.format("| 전화(%s)? ", m.getTel())));

      // 3) DBMS에게 데이터 변경을 요청한다.
      stmt2.setString(1, m.getId());
      stmt2.setString(2, m.getName());
      stmt2.setString(3, m.getEmail());
      stmt2.setString(4, m.getPhoto());
      stmt2.setString(5, m.getPassword());
      stmt2.setString(6, m.getTel());
      stmt2.setInt(7, m.getNo());
      stmt2.executeUpdate();

      System.out.println("+--------------------------+");
      System.out.println("| 매니저를 변경하였습니다. |");
      System.out.println("+--------------------------+");
    }
  }
}






