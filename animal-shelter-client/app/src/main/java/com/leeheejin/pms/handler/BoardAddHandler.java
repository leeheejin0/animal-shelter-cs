package com.leeheejin.pms.handler;

import java.sql.Date;
import com.leeheejin.driver.Statement;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardAddHandler implements Command {

  Statement stmt;

  public BoardAddHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service(String menuName, String name) {
    System.out.printf("[ 홈 > %s > 게시판 > %s > 글쓰기* ]\n", menuName, name);

    try {
      Board b = new Board();

      stmt.executeUpdate("board/insert", 
          String.format("%s,%s,%s", b.getTitle(), b.getContent(), b.getWriter()));

      b.setPassword(Prompt.inputInt("비밀번호>> "));
      b.setTitle(Prompt.inputString("제목>> "));
      b.setContent(Prompt.inputString("내용>> "));
      b.setRegisteredDate(new Date(System.currentTimeMillis()));

      boardList.add(b);

      System.out.println("- 게시글 등록이 완료되었습니다. ");
      System.out.println();
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
          e.getClass().getName(), e.getMessage());
      e.printStackTrace();
      System.out.println("---------------------");
    }
  }
}