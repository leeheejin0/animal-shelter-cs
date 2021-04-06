package com.leeheejin.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.leeheejin.pms.domain.Other;
import com.leeheejin.util.Prompt;

public class OtherManagerListHandler extends AbstractOtherHandler {

  HashMap<String, Command> commandMap = new HashMap<>();

  public OtherManagerListHandler(List<Other> otherList) {
    super(otherList);
  }

  @Override
  public void service() {
    loop:
      while (true) {
        commandMap.put("1", new OtherUpdateHandler(otherList));
        commandMap.put("1", new OtherRemoveHandler(otherList));

        System.out.println("[ 홈 > 메뉴 > 구조동물목록 > 기타동물구조목록* ]");
        print();
        int command = Prompt.inputInt("1: 상태수정 | 2: 삭제 | 3: 뒤로가기 \n>> ");
        switch (command) {
          case 3:
            break loop;
          default:
            Command commandHandler = commandMap.get(command);
            if (commandHandler == null) {
              System.out.println("실행할 수 없는 명령입니다.");
              System.out.println();
            } else {
              commandHandler.service();
            }
            break;
        }
      }
  }
}
