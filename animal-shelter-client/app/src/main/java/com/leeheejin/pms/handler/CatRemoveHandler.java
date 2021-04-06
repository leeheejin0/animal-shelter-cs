package com.leeheejin.pms.handler;

import com.leeheejin.driver.Statement;
import com.leeheejin.util.Prompt;

public class CatRemoveHandler implements Command {

  Statement stmt;

  public CatRemoveHandler (Statement stmt) {
    this.stmt = stmt; 
  }

  @Override
  public void service() {
    int removeNo = Prompt.inputInt("<삭제>\n번호? ");
    if (removeNo <= catList.size()) {
      print(removeNo - 1);
      String command = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (command.equalsIgnoreCase("n") || command.isEmpty()) {
        System.out.println("- 목록으로 돌아갑니다. ");
        System.out.println();
      } else if (command.equalsIgnoreCase("y")) {
        catList.remove(catList.get(removeNo - 1));
        System.out.println("- <삭제완료>");
        System.out.println();
      } else {
        System.out.println("- 잘못 입력하셨습니다. ");
        System.out.println();
      }
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }
  }

  //  private void backToList(String message) {
  //    System.out.println(message);
  //    System.out.println();
  //    managerList();
  //  }
}
