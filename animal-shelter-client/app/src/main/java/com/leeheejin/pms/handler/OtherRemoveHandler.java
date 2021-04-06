package com.leeheejin.pms.handler;

import java.util.List;
import com.leeheejin.pms.domain.Other;
import com.leeheejin.util.Prompt;

public class OtherRemoveHandler extends AbstractOtherHandler {

  public OtherRemoveHandler(List<Other> otherList) {
    super(otherList);
  }

  @Override
  public void service() {
    int removeNo = Prompt.inputInt("<삭제>\n번호? ");
    if (removeNo <= otherList.size()) {
      print(removeNo - 1);
      String command = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (command.equalsIgnoreCase("n") || command.isEmpty()) {
        System.out.println("- 목록으로 돌아갑니다. ");
        System.out.println();
      } else if (command.equalsIgnoreCase("y")) {
        otherList.remove(otherList.get(removeNo - 1));
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
}
