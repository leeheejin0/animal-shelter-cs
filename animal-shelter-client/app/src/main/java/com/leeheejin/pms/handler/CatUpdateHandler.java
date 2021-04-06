package com.leeheejin.pms.handler;

import java.sql.Date;
import com.leeheejin.driver.Statement;
import com.leeheejin.util.Prompt;

public class CatUpdateHandler implements Command {

  Statement stmt;
  ManagerValidator managerValidator;

  public CatUpdateHandler (Statement stmt,ManagerValidator managerValidator) {
    this.stmt = stmt;
    this.managerValidator = managerValidator;
  }

  @Override

  public void service() throws Exception {
    System.out.println("+-+-+ 고양이 정보 수정 +-+-+");

    int updateNo = Prompt.inputInt("| 번호? ");

    String writer = managerValidator.inputManager("| 관리자 이름? ");
    if (writer.length() == 0) {
      System.out.println("+---------------------------------------+");
      System.out.println("| 정보 수정은 관리자 권한이 필요합니다. |");
      System.out.println("+---------------------------------------+");
      return;
    }

    String[] fields = stmt.executeQuery("cat/update", Integer.toString(updateNo)).next().split(",");

    String photos = Prompt.inputString(String.format("| 사진?[%d] ", fields));
    String photos = Prompt.inputString(String.format("", fields));
    String breeds = Prompt.inputString(String.format("", fields));
    String genders = Prompt.inputString(String.format("", fields));
    int ages = Prompt.inputInt(String.format("", fields));
    Date dates = Prompt.inputDate(String.format("", fields));
    String places = Prompt.inputString(String.format("", fields));
    String status = Prompt.inputString(String.format("", fields));
    //    int updateNo = Prompt.inputInt("<상태수정>\n번호? ");
    //
    //    if (updateNo <= catList.size()) {
    //
    //      print(updateNo - 1);
    //      String updateStatus = Prompt.inputString("1: 공고중 | 2: 입양완료\n>> ");
    //      String stateLabel = null;
    //      switch (updateStatus) {
    //        case "1":
    //          stateLabel = "공고중";
    //          break;
    //        case "2":
    //          stateLabel = "입양완료";
    //          break;
    //        default:
    //          stateLabel = "신규";
    //          break;
    //      }
    //      Cat c = catList.get(updateNo - 1);
    //      c.setStatus(stateLabel);
    //      System.out.println("<수정완료>");
    //      System.out.println();
    //      return;
    //    } else {
    //      System.out.println("- 잘못 입력하셨습니다. ");
    //      System.out.println();
    //    }
  }
}
