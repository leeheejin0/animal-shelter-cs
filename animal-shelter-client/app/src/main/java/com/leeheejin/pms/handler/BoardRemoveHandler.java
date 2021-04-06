package com.leeheejin.pms.handler;

import com.leeheejin.driver.Statement;
import com.leeheejin.util.Prompt;

public class BoardRemoveHandler implements Command {

  Statement stmt;

  public BoardRemoveHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service(int removeNo) {
    int password = Prompt.inputInt("<삭제>\n비밀번호? ");
    if (findByPw(removeNo, password)) {
      String command = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (command.equalsIgnoreCase("n") || command.isEmpty()) {
        System.out.println("- 목록으로 돌아갑니다. ");
        System.out.println();
      } else if (command.equalsIgnoreCase("y")) {
        boardList.remove(boardList.get(removeNo - 1));
        System.out.println("- <삭제완료>");
        System.out.println();
      }
    } else {
      System.out.println("- 비밀번호가 일치하지 않습니다. ");
      System.out.println();
    }
  }
}