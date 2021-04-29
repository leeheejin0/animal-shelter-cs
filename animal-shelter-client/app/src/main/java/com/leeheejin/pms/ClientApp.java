package com.leeheejin.pms;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import com.leeheejin.pms.handler.BoardAddHandler;
import com.leeheejin.pms.handler.BoardDeleteHandler;
import com.leeheejin.pms.handler.BoardDetailHandler;
import com.leeheejin.pms.handler.BoardListHandler;
import com.leeheejin.pms.handler.BoardSearchHandler;
import com.leeheejin.pms.handler.BoardUpdateHandler;
import com.leeheejin.pms.handler.CatAddHandler;
import com.leeheejin.pms.handler.CatDeleteHandler;
import com.leeheejin.pms.handler.CatDetailHandler;
import com.leeheejin.pms.handler.CatListHandler;
import com.leeheejin.pms.handler.CatUpdateHandler;
import com.leeheejin.pms.handler.Command;
import com.leeheejin.pms.handler.DogAddHandler;
import com.leeheejin.pms.handler.DogDeleteHandler;
import com.leeheejin.pms.handler.DogDetailHandler;
import com.leeheejin.pms.handler.DogListHandler;
import com.leeheejin.pms.handler.DogUpdateHandler;
import com.leeheejin.pms.handler.ManagerAddHandler;
import com.leeheejin.pms.handler.ManagerDeleteHandler;
import com.leeheejin.pms.handler.ManagerDetailHandler;
import com.leeheejin.pms.handler.ManagerListHandler;
import com.leeheejin.pms.handler.ManagerUpdateHandler;
import com.leeheejin.pms.handler.ManagerValidator;
import com.leeheejin.pms.handler.MemberAddHandler;
import com.leeheejin.pms.handler.MemberDeleteHandler;
import com.leeheejin.pms.handler.MemberDetailHandler;
import com.leeheejin.pms.handler.MemberListHandler;
import com.leeheejin.pms.handler.MemberUpdateHandler;
import com.leeheejin.pms.handler.MemberValidator;
import com.leeheejin.pms.handler.OtherAddHandler;
import com.leeheejin.pms.handler.OtherDeleteHandler;
import com.leeheejin.pms.handler.OtherDetailHandler;
import com.leeheejin.pms.handler.OtherListHandler;
import com.leeheejin.pms.handler.OtherUpdateHandler;
import com.leeheejin.util.Prompt;

public class ClientApp {

  // 사용자가 입력한 명령을 저장할 컬렉션 객체 준비
  ArrayDeque<String> commandStack = new ArrayDeque<>();
  LinkedList<String> commandQueue = new LinkedList<>();

  String serverAddress;
  int port;

  public static void main(String[] args) {
    ClientApp app = new ClientApp("localhost", 8888);

    try {
      app.execute();

    } catch (Exception e) {
      System.out.println("클라이언트 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }

  public ClientApp(String serverAddress, int port) {
    this.serverAddress = serverAddress;
    this.port = port;
  }

  public void execute() throws Exception {

    // 사용자 명령을 처리하는 객체를 맵에 보관한다.
    HashMap<String,Command> commandMap = new HashMap<>();

    MemberValidator memberValidator = new MemberValidator();

    commandMap.put("board/add", new BoardAddHandler(memberValidator));
    commandMap.put("board/list", new BoardListHandler());
    commandMap.put("board/detail", new BoardDetailHandler());
    commandMap.put("board/update", new BoardUpdateHandler());
    commandMap.put("board/delete", new BoardDeleteHandler());
    commandMap.put("board/search", new BoardSearchHandler());

    commandMap.put("member/join", new MemberAddHandler());
    commandMap.put("member/signout", new MemberDeleteHandler());
    commandMap.put("member/update", new MemberUpdateHandler());
    commandMap.put("member/list", new MemberListHandler());
    commandMap.put("member/detail", new MemberDetailHandler());
    //    commandMap.put("manager/login", new ManagerLoginHandler());
    //    commandMap.put("manager/logout", new ManagerLogoutHandler());

    commandMap.put("manager/join", new ManagerAddHandler());
    commandMap.put("manager/signout", new ManagerDeleteHandler());
    commandMap.put("member/update", new ManagerUpdateHandler());
    commandMap.put("member/list", new ManagerListHandler());
    commandMap.put("member/detail", new ManagerDetailHandler());
    //    commandMap.put("manager/login", new ManagerLoginHandler());
    //    commandMap.put("manager/logout", new ManagerLogoutHandler());

    ManagerValidator managerValidator = new ManagerValidator();

    commandMap.put("cat/add", new CatAddHandler(managerValidator));
    commandMap.put("cat/list", new CatListHandler());
    commandMap.put("cat/detail", new CatDetailHandler());
    commandMap.put("cat/update", new CatUpdateHandler());
    commandMap.put("cat/delete", new CatDeleteHandler());

    commandMap.put("dog/add", new DogAddHandler(managerValidator));
    commandMap.put("dog/list", new DogListHandler());
    commandMap.put("dog/detail", new DogDetailHandler());
    commandMap.put("dog/update", new DogUpdateHandler());
    commandMap.put("dog/delete", new DogDeleteHandler());

    commandMap.put("other/add", new OtherAddHandler(managerValidator));
    commandMap.put("other/list", new OtherListHandler());
    commandMap.put("other/detail", new OtherDetailHandler());
    commandMap.put("other/update", new OtherUpdateHandler());
    commandMap.put("other/delete", new OtherDeleteHandler());

    try {

      while (true) {

        System.out.println("+-----------------------+{ 동물 보호소 관리 시스템 }+-----------------------+");
        System.out.println("+ + + + + + + + + +  사용설명서 : 키워드를 입력하세요!  + + + + + + + + + + +");
        System.out.println("+------------------+------------------+------------------+------------------+");
        System.out.println("|   /member/join   |  /manager/join   |  /member/update  | /manager/update  |");
        System.out.println("|   /member/list   |  /manager/list   |  /member/detail  | /manager/detail  |");
        //System.out.println("|   /member/login  |  /manager/login  |  /member/logout  | /manager/logout  |");
        System.out.println("|  /member/signout | ··································· | /manager/signout |");
        System.out.println("+------------------+------------------+------------------+------------------+");
        System.out.println("|  /cat/add   |  /cat/list  |  /cat/detail  |  /cat/update  |  /cat/delete  |");
        System.out.println("|  /dog/add   |  /dog/list  |  /dog/detail  |  /dog/update  |  /dog/delete  |");
        System.out.println("| /other/add  | /other/list | /other/detail | /other/update | /other/delete |");
        System.out.println("| /board/add  | /board/list | /board/detail | /board/update | /board/delete |");
        System.out.println("| exit | quit | ··········································· | /board/search |"); 
        System.out.println("+-------------+---------------------------------------------+---------------+");
        System.out.println();
        String command = Prompt.inputString(":: ");

        if (command.length() == 0) {
          continue;
        }

        // 사용자가 입력한 명령을 보관해둔다.
        commandStack.push(command);
        commandQueue.offer(command);

        try {
          switch (command) {
            case "history":
              printCommandHistory(commandStack.iterator());
              break;
            case "history2": 
              printCommandHistory(commandQueue.iterator());
              break;
            case "quit":
            case "exit":
              System.out.println("+-----------------+");
              System.out.println("|   종료합니다.   |");
              System.out.println("+-----------------+");
              return;
            default:
              Command commandHandler = commandMap.get(command);
              if (commandHandler == null) {
                System.out.println("+----------------------+");
                System.out.println("| 잘못 입력하셨습니다. |");
                System.out.println("+----------------------+");
              } else {
                commandHandler.service();
              }
          }
        } catch (Exception e) {
          System.out.println("-------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
              e.getClass().getName(), e.getMessage());
          System.out.println("-------------------------------------");
        }
        System.out.println(); // 이전 명령의 실행을 구분하기 위해 빈 줄 출력
      }

    } catch (Exception e) {
      System.out.println("+----------------------------------+");
      System.out.println("| 서버와 통신 하는 중에 오류 발생! |");
      System.out.println("+----------------------------------+");
    }

    Prompt.close();
  }

  private void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if ((++count % 5) == 0) {
        String input = Prompt.inputString(": ");
        if (input.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
}

