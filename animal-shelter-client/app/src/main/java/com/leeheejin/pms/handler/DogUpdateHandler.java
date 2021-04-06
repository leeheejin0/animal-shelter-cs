package com.leeheejin.pms.handler;

import java.util.List;
import com.leeheejin.pms.domain.Dog;
import com.leeheejin.util.Prompt;

public class DogUpdateHandler extends AbstractDogHandler {

  public DogUpdateHandler(List<Dog> dogList) {
    super(dogList);
  }

  @Override
  public void service() {
    System.out.println();
    int updateNo = Prompt.inputInt("<상태수정>\n번호? ");

    if (updateNo <= dogList.size()) {

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
      Dog d = dogList.get(updateNo - 1);
      d.setStatus(stateLabel);
      System.out.println("<수정완료>");
      System.out.println();
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }
  }
}