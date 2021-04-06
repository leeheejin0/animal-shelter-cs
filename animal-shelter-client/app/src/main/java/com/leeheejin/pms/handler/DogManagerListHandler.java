package com.leeheejin.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.leeheejin.pms.domain.Dog;
import com.leeheejin.util.Prompt;

public class DogManagerListHandler extends AbstractDogHandler {

  HashMap<String, Command> commandMap = new HashMap<>();

  public DogManagerListHandler(List<Dog> dogList) {
    super(dogList);
  }

  @Override
  public void service() {
    loop:
      while (true) {
        commandMap.put("1", new DogUpdateHandler(dogList));
        commandMap.put("2", new DogRemoveHandler(dogList));

        System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 개구조목록* ]");
        print();
        String command = Prompt.inputString("1: 상태수정 | 2: 삭제 | 3: 뒤로가기 \n>> ");
        switch (command) {
          case "3":
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