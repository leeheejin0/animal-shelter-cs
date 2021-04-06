package com.leeheejin.pms.handler;

import java.util.List;
import com.leeheejin.pms.domain.Other;
import com.leeheejin.util.Prompt;

public class OtherUpdateHandler extends AbstractOtherHandler {

  public OtherUpdateHandler(List<Other> otherList) {
    super(otherList);
  }

  @Override
  public void service() {
    System.out.println();
    int updateNo = Prompt.inputInt("<상태수정>\n번호? ");

    if (updateNo <= otherList.size()) {

      print(updateNo - 1);
      int updateStatus = Prompt.inputInt("1: 공고중 | 2: 입양완료\n>> ");
      String stateLabel = null;
      switch (updateStatus) {
        case 1:
          stateLabel = "공고중";
          break;
        case 2:
          stateLabel = "입양완료";
          break;
        default:
          stateLabel = "신규";
          break;
      }

      Other o = otherList.get(updateNo - 1);
      o.setStatus(stateLabel);
      System.out.println("<수정완료>");
      System.out.println();
      print(updateNo);
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }
  }
}
