package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.leeheejin.pms.domain.Member;
import com.leeheejin.util.Prompt;

public class MemberValidator {

  public Member inputMember(String promptTitle) throws Exception {

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select count(*) from pms_member where name=?")) {

      while (true) {
        String name = Prompt.inputString(promptTitle);
        if (name.length() == 0) {
          return null;
        } 
        stmt.setString(1, name);

        try (ResultSet rs = stmt.executeQuery()) {
          rs.next();
          if (rs.getInt(1) > 0) {
            Member member = new Member();
            member.setNo(rs.getInt("no"));
            member.setName(rs.getString("name"));
            member.setEmail(rs.getString("email"));
            return member;
          }
          System.out.println("+---------------------------+");
          System.out.println("| 등록되지 않은 회원입니다. |");
          System.out.println("+---------------------------+");
        }
      }
    }
  }
}






