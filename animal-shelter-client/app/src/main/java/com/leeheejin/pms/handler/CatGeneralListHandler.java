package com.leeheejin.pms.handler;

import java.util.List;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.util.Prompt;

public class CatGeneralListHandler extends AbstractCatHandler{

  public CatGeneralListHandler(List<Cat> catList) {
    super(catList);
  }

  @Override
  public void service() {

    System.out.println("[ 홈 > 메뉴 > 구조동물목록 > 고양이구조목록* ]");
    print();
    String command = Prompt.inputString("1: 뒤로가기 \n>> ");
    switch (command) {
      case "1":
        return;
      default:
        System.out.println("실행할 수 없는 명령입니다.");
        System.out.println();
        break;
    }
  }

}
