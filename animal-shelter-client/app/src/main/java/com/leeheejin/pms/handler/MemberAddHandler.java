package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.leeheejin.pms.domain.Manager;
import com.leeheejin.util.Prompt;

public class MemberAddHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 회원 등록 +-+-+");

    Manager m = new Manager();

    m.setId(Prompt.inputString("| 아이디? "));
    m.setPassword(Prompt.inputString("| 비밀번호? "));
    m.setName(Prompt.inputString("| 이름? "));
    m.setEmail(Prompt.inputString("| 이메일? "));
    m.setTel(Prompt.inputString("| 전화번호? "));

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "insert into pms_member(id,name,email,tel,password) values(?,?,?,?,password(?))");) {

      stmt.setString(1, m.getId());
      stmt.setString(2, m.getName());
      stmt.setString(3, m.getEmail());
      stmt.setString(4, m.getTel());
      stmt.setString(5, m.getPassword());

      stmt.executeUpdate();
      System.out.println("+------------------------+");
      System.out.println("| 회원을 등록하였습니다. |");
      System.out.println("+------------------------+");
    }
  }
}






