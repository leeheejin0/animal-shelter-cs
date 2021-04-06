package com.leeheejin.pms.handler;

import java.util.HashMap;
import com.leeheejin.driver.Statement;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardDetailHandler implements Command {

  Statement stmt;

  public BoardDetailHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {

    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("1", new BoardUpdateHandler(stmt));
    commandMap.put("2", new BoardRemoveHandler(stmt));

    int detailNo = Prompt.inputInt("<상세보기>\n게시글 번호? ");
    if (detailNo <= boardList.size()) {
      Board b = boardList.get(detailNo - 1);
      b.setViewCount(b.getViewCount() + 1);
      System.out.println("==========================================================");
      System.out.printf("<  %s  >                %s | %s |\n", 
          b.getTitle(), b.getName(), b.getRegisteredDate());
      System.out.println(" --------------------------------------------------------");
      System.out.printf("  %s\n", b.getContent());
      System.out.println(" --------------------------------------------------------");
      System.out.printf("                              조회수 : %d | 좋아요 : %d |\n", 
          b.getViewCount(), b.getLike());
      System.out.println("==========================================================");
      String command = Prompt.inputString("1: 수정 | 2: 삭제 | 3: 뒤로가기\n>> ");
      switch (command) {
        default:
          Command commandHandler = commandMap.get(command);
          if (commandHandler == null) {
            System.out.println("실행할 수 없는 명령입니다.");
          } else {
            commandHandler.service(detailNo);
          }
          break;
      }
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }
  }
}