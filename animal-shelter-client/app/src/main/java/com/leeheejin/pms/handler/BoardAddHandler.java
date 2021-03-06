package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardAddHandler implements Command {

  MemberValidator memberValidator;

  public BoardAddHandler(MemberValidator memberValidator) {
    this.memberValidator = memberValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 게시글 등록 +-+-+");

    Board b = new Board();

    b.setTitle(Prompt.inputString("| 제목? "));
    b.setContent(Prompt.inputString("| 내용? "));
    b.setWriter(memberValidator.inputMember("| 작성자 이름? "));
    if (b.getWriter() == null) {
      System.out.println("+----------------------------------+");
      System.out.println("| 게시글 등록은 회원만 가능합니다. |");
      System.out.println("+----------------------------------+");
      return;
    }

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt =
            con.prepareStatement("insert into pms_board(title, content, writer) values(?,?,?)");) {

      stmt.setString(1, b.getTitle());
      stmt.setString(2, b.getContent());
      stmt.setInt(3, b.getWriter().getNo());

      stmt.executeUpdate();
      System.out.println("+--------------------------+");
      System.out.println("| 게시글을 등록하였습니다. |");
      System.out.println("+--------------------------+");
    }
  }
}






