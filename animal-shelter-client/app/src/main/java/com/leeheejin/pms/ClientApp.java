package com.leeheejin.pms;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import com.leeheejin.driver.Statement;
import com.leeheejin.pms.handler.BoardAddHandler;
import com.leeheejin.pms.handler.BoardDetailHandler;
import com.leeheejin.pms.handler.BoardListHandler;
import com.leeheejin.pms.handler.BoardSearchHandler;
import com.leeheejin.pms.handler.BoardUpdateHandler;
import com.leeheejin.pms.handler.CatAddHandler;
import com.leeheejin.pms.handler.CatListHandler;
import com.leeheejin.pms.handler.CatUpdateHandler;
import com.leeheejin.pms.handler.Command;
import com.leeheejin.pms.handler.DogAddHandler;
import com.leeheejin.pms.handler.DogListHandler;
import com.leeheejin.pms.handler.DogUpdateHandler;
import com.leeheejin.pms.handler.ManagerValidator;
import com.leeheejin.pms.handler.MemberValidator;
import com.leeheejin.pms.handler.OtherAddHandler;
import com.leeheejin.pms.handler.OtherListHandler;
import com.leeheejin.pms.handler.OtherUpdateHandler;
import com.leeheejin.util.Prompt;

public class ClientApp {

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
    Statement stmt = new Statement(serverAddress, port);

    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("board/add", new BoardAddHandler(stmt));
    commandMap.put("board/list", new BoardListHandler(stmt));
    commandMap.put("board/detail", new BoardDetailHandler(stmt));
    commandMap.put("board/update", new BoardUpdateHandler(stmt));
    commandMap.put("board/delete", new BoardDeleteHandler(stmt));
    commandMap.put("board/search", new BoardSearchHandler(stmt));

    //    commandMap.put("member/join", new MemberAddHandler(stmt));
    //    commandMap.put("member/signout", new (stmt));
    //    commandMap.put("member/login", new BoardUpdateHandler(stmt));
    //    commandMap.put("member/logout", new BoardDeleteHandler(stmt));

    ManagerValidator managerValidator = new ManagerValidator();
    MemberValidator memeberValidator = new MemberValidator();

    //    commandMap.put("manager/join", new ManagerAddHandler(stmt));
    //    commandMap.put("manager/signout", new (stmt));
    //    commandMap.put("manager/login", new ManagerUpdateHandler(stmt));
    //    commandMap.put("manager/logout", new ManagerDeleteHandler(stmt));

    commandMap.put("cat/add", new CatAddHandler(stmt,managerValidator));
    commandMap.put("cat/list", new CatListHandler(stmt));
    commandMap.put("cat/detail", new CatDetailHandler(stmt));
    commandMap.put("cat/update", new CatUpdateHandler(stmt,managerValidator));
    commandMap.put("cat/delete", new CatDeleteHandler(stmt,managerValidator));

    commandMap.put("dog/add", new DogAddHandler(stmt,managerValidator));
    commandMap.put("dog/list", new DogListHandler(stmt));
    commandMap.put("dog/detail", new DogDetailHandler(stmt));
    commandMap.put("dog/update", new DogUpdateHandler(stmt,managerValidator));
    commandMap.put("dog/delete", new DogDeleteHandler(stmt,managerValidator));

    commandMap.put("other/add", new OtherAddHandler(stmt,managerValidator));
    commandMap.put("other/list", new OtherListHandler(stmt));
    commandMap.put("other/detail", new OtherDetailHandler(stmt));
    commandMap.put("other/update", new OtherUpdateHandler(stmt,managerValidator));
    commandMap.put("other/delete", new OtherDeleteHandler(stmt,managerValidator));

    try {
      loop:
        while (true) {
          try {
            System.out.println("+--------------------+{ 동물 보호소 관리 시스템 }+--------------------+");
            System.out.println("+ + + + + + + + + 사용설명서 : 키워드를 입력하세요! + + + + + + + + + +");
            System.out.println("+---------------+----------------+-----------------+------------------+");
            System.out.println("|  member/join  |  manager/join  |  member/signout |  manager/signout |");
            System.out.println("|  member/login |  manager/login |  member/logout  |  manager/logout  |");
            System.out.println("+---------------+----------------+-----------------+------------------+");
            System.out.println("|  cat/add  |  cat/list  |  cat/detail  |  cat/update  |  cat/delete  |");
            System.out.println("|  dog/add  |  dog/list  |  dog/detail  |  dog/update  |  dog/delete  |");
            System.out.println("| other/add | other/list | other/detail | other/update | other/delete |");
            System.out.println("| board/add | board/list | board/detail | board/update | board/delete |");
            System.out.println("|   exit    | ········································ | board/search |"); 
            System.out.println("+-----------+------------------------------------------+--------------+");
            System.out.println();
            String command = Prompt.inputString(":: ");

            switch (command) {

              case "exit":
                System.out.println("+-----------------+");
                System.out.println("|   종료합니다.   |");
                System.out.println("+-----------------+");
                break loop;
              default:
                System.out.println("+----------------------+");
                System.out.println("| 잘못 입력하셨습니다. |");
                System.out.println("+----------------------+");
                System.out.println();
                break;
            }
          } catch (Exception e) {
            System.out.println("-------------------------------------");
            System.out.println(" 잘못된 입력입니다. ");
            System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
                e.getClass().getName(), e.getMessage());
            System.out.println("-------------------------------------");
          }
        }
    } catch (Exception e) {
      System.out.println("+----------------------------------+");
      System.out.println("| 서버와 통신 하는 중에 오류 발생! |");
      System.out.println("+----------------------------------+");
    }

    Prompt.close();
    stmt.close();
  }
}
