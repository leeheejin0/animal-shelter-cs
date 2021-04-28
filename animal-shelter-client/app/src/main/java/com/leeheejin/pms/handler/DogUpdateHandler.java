package com.leeheejin.pms.handler;

import java.sql.Date;
import com.leeheejin.driver.Statement;
import com.leeheejin.util.Prompt;

public class DogUpdateHandler implements Command {

  Statement stmt;
  ManagerValidator managerValidator;

  public DogUpdateHandler (Statement stmt,ManagerValidator managerValidator) {
    this.stmt = stmt;
    this.managerValidator = managerValidator;
  }

  @Override

  public void service() throws Exception {
    System.out.println("+-+-+ 개 정보 수정 +-+-+");

    int updateNo = Prompt.inputInt("| 번호? ");

    String writer = managerValidator.inputManager("| 관리자 이름? ");
    if (writer.length() == 0) {
      System.out.println("+---------------------------------------+");
      System.out.println("| 정보 수정은 관리자 권한이 필요합니다. |");
      System.out.println("+---------------------------------------+");
      return;
    }

    String[] fields = stmt.executeQuery("dog/select", Integer.toString(updateNo)).next().split(",");
    String photos = Prompt.inputString(String.format("| 사진[%s]? ", fields[1]));
    String breeds = Prompt.inputString(String.format("| 품종[%s]? ", fields[2]));
    String genders = Prompt.inputString(String.format("| 성별[%s]? ", fields[3]));
    int ages = Prompt.inputInt(String.format("| 나이[%s]? ", fields[4]));
    Date dates = Prompt.inputDate(String.format("| 구조일[%s]? ", fields[5]));
    String places = Prompt.inputString(String.format("| 구조장소[%s]? ", fields[6]));
    String status = Prompt.inputString(
        String.format("| 상태[%s]\n| >> 공고중 >> 입양완료 ? ", fields[7]));

    String input = Prompt.inputString(
        "+-----------------------------+\n"
            + "| 정말 변경하시겠습니까[y/N]? |\n"
            + "+-----------------------------+\n+ ");
    if (!input.equalsIgnoreCase("y")) {
      System.out.println("+-----------------------------+");
      System.out.println("| 정보 변경을 취소하였습니다. |");
      System.out.println("+-----------------------------+");
      return;
    }

    stmt.executeUpdate("dog/update", 
        String.format("%s,%s,%s,%d,%s,%s,%s", 
            photos, breeds, genders, ages, dates, places, status));
  }
}
