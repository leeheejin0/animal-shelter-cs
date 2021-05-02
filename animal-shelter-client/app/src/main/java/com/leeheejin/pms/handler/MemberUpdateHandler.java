package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.leeheejin.pms.domain.Member;
import com.leeheejin.util.Prompt;

public class MemberUpdateHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 회원 변경 +-+-+");

    int no = Prompt.inputInt("| 번호? ");

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select * from pms_member where no = ?");
        PreparedStatement stmt2 = con.prepareStatement(
            "update pms_member set id=?,name=?,email=?,photo=?,password=password(?),tel=? where no=?")) {

      Member mem = new Member();

      // 1) 기존 데이터 조회
      stmt.setInt(1, no);
      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          System.out.println("+------------------------------+");
          System.out.println("| 해당 번호의 회원이 없습니다. |");
          System.out.println("+------------------------------+");
          return;
        }

        mem.setNo(no); 
        mem.setId(rs.getString("id"));
        mem.setName(rs.getString("name"));
        mem.setEmail(rs.getString("email"));
        mem.setPhoto(rs.getString("photo"));
        mem.setTel(rs.getString("tel"));
      }

      // 2) 사용자에게서 변경할 데이터를 입력 받는다.
      mem.setId(Prompt.inputString(String.format("| 아이디(%s)? ", mem.getId())));
      mem.setName(Prompt.inputString(String.format("| 이름(%s)? ", mem.getName())));
      mem.setEmail(Prompt.inputString(String.format("| 이메일(%s)? ", mem.getEmail())));
      mem.setPhoto(Prompt.inputString(String.format("| 사진(%s)? ", mem.getPhoto())));
      mem.setPassword(Prompt.inputString("| 새암호? "));
      mem.setTel(Prompt.inputString(String.format("| 전화(%s)? ", mem.getTel())));

      // 3) DBMS에게 데이터 변경을 요청한다.
      stmt2.setString(1, mem.getId());
      stmt2.setString(2, mem.getName());
      stmt2.setString(3, mem.getEmail());
      stmt2.setString(4, mem.getPhoto());
      stmt2.setString(5, mem.getPassword());
      stmt2.setString(6, mem.getTel());
      stmt2.setInt(7, mem.getNo());
      stmt2.executeUpdate();

      System.out.println("+------------------------+");
      System.out.println("| 회원을 변경하였습니다. |");
      System.out.println("+------------------------+");
    }
  }
}






