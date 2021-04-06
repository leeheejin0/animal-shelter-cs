package com.leeheejin.pms.handler;

import java.util.HashMap;
import com.leeheejin.driver.Statement;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardListHandler implements Command {

  Statement stmt;

  public BoardListHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service(String menuName, String name) {

    HashMap<String, Command> commandMap = new HashMap<>();
    loop:
      while (true) {
        commandMap.put("1", new BoardDetailHandler(boardList));
        commandMap.put("2", new BoardSearchHandler(boardList));

        System.out.printf("[ 홈 > %s > 게시판 > %s > 글목록* ]\n", menuName, name);
        Board[] list = boardList.toArray(new Board[boardList.size()]);
        for (Board b : list) {
          System.out.printf("(%d) %s |%s| %s |%d|%d|\n", 
              b.getNo(), b.getTitle(), b.getRegisteredDate(), b.getName(), 
              b.getViewCount(), b.getLike());
        }

        String command = Prompt.inputString("1: 상세보기 | 2: 검색 | 3: 뒤로가기\n>>");
        switch (command) {
          case "3":
            break loop;
          default:
            Command commandHandler = commandMap.get(command);
            if (commandHandler == null) {
              System.out.println("실행할 수 없는 명령입니다.");
            } else {
              commandHandler.service();
            }
            break;
        }
      }  
  }
}