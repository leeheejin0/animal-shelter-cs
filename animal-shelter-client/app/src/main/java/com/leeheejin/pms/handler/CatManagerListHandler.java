package com.leeheejin.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.util.Prompt;

public class CatManagerListHandler extends AbstractCatHandler{

  HashMap<String, Command> commandMap = new HashMap<>();

  public CatManagerListHandler(List<Cat> catList) {
    super(catList);
  }

  @Override
  public void service() {
    loop: 
      while (true) {
        commandMap.put("1", new CatUpdateHandler(catList));
        commandMap.put("2", new CatRemoveHandler(catList));

        System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 고양이구조목록* ]");
        print();
        String command = Prompt.inputString("1: 상태수정 | 2: 삭제 | 3: 뒤로가기 \n>> ");
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
        //    System.out.println();
      }
  }
}
