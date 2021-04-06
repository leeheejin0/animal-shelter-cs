package com.leeheejin.pms.handler;

import java.sql.Date;
import java.util.List;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardUpdateHandler extends AbstractBoardHandler {

  public BoardUpdateHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service(int updateNo) {
    try {
      int password = Prompt.inputInt("<수정>\n비밀번호? ");
      if (findByPw(updateNo, password)) {
        System.out.println("-");
        Board b = boardList.get(updateNo - 1);
        b.setName(Prompt.inputString("이름>> "));
        b.setPassword(Prompt.inputInt("비밀번호>> "));
        b.setTitle(Prompt.inputString("제목>> "));
        b.setContent(Prompt.inputString("내용>> "));
        b.setRegisteredDate(new Date(System.currentTimeMillis()));

        System.out.println("==========================================================");
        System.out.printf("<  %s  >                %s | %s |\n", 
            b.getTitle(), b.getName(), b.getRegisteredDate());
        System.out.println(" --------------------------------------------------------");
        System.out.printf("  %s\n", b.getContent());
        System.out.println(" --------------------------------------------------------");
        System.out.printf("                              조회수 : %d | 좋아요 : %d |\n", 
            b.getViewCount(), b.getLike());
        System.out.println("==========================================================");
        System.out.println("- <수정완료>");
        System.out.println();
      } else {
        System.out.println("- 비밀번호가 일치하지 않습니다. ");
        System.out.println();
      }
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
  }
}