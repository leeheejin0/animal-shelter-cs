package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.leeheejin.util.Prompt;

public class BoardSearchHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 게시물 검색 +-+-+");
    String keyword = Prompt.inputString("| 검색어? ");

    if (keyword.length() == 0) {
      System.out.println("+----------------------+");
      System.out.println("| 검색어를 입력하세요. |");
      System.out.println("+----------------------+");
      return;
    }

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select no,title,writer,rdt,vw_cnt"
                + " from pms_board"
                + " where title like concat('%',?,'%')"
                + " or content like concat('%',?,'%')"
                + " or writer like concat('%',?,'%')"
                + " order by no desc")) {

      stmt.setString(1, keyword);
      stmt.setString(2, keyword);
      stmt.setString(3, keyword);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          System.out.println("+--------------------------------------+");
          System.out.println("| 검색어에 해당하는 게시글이 없습니다. |");
          System.out.println("+--------------------------------------+");
          return;
        }

        System.out.println("+");
        do {
          System.out.printf("%d, %s, %s, %s, %d\n", 
              rs.getInt("no"), 
              rs.getString("title"), 
              rs.getString("writer"),
              rs.getDate("rdt"),
              rs.getInt("vw_cnt"));
        } while (rs.next());
        System.out.println("+");
      }
    }
  }
}






