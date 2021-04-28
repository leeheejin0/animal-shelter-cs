package com.leeheejin.pms.handler;

import java.sql.Date;
import com.leeheejin.driver.Statement;
import com.leeheejin.util.Prompt;

public class OtherUpdateHandler implements Command {

  Statement stmt;
  ManagerValidator managerValidator;

  public OtherUpdateHandler (Statement stmt,ManagerValidator managerValidator) {
    this.stmt = stmt;
    this.managerValidator = managerValidator;
  }

  @Override

  public void service() throws Exception {
    System.out.println("+-+-+ 기타동물 정보 수정 +-+-+");

    int updateNo = Prompt.inputInt("| 번호? ");

    String writer = managerValidator.inputManager("| 관리자 이름? ");
    if (writer.length() == 0) {
      System.out.println("+---------------------------------------+");
      System.out.println("| 정보 수정은 관리자 권한이 필요합니다. |");
      System.out.println("+---------------------------------------+");
      return;
    }

    String[] fields = stmt.executeQuery("other/select", Integer.toString(updateNo)).next().split(",");
    String species = Prompt.inputString(String.format("| 종류[%s]? ", fields[1]));
    String photos = Prompt.inputString(String.format("| 사진[%s]? ", fields[2]));
    String breeds = Prompt.inputString(String.format("| 품종[%s]? ", fields[3]));
    String genders = Prompt.inputString(String.format("| 성별[%s]? ", fields[4]));
    int ages = Prompt.inputInt(String.format("| 나이[%s]? ", fields[5]));
    Date dates = Prompt.inputDate(String.format("| 구조일[%s]? ", fields[6]));
    String places = Prompt.inputString(String.format("| 구조장소[%s]? ", fields[7]));
    String status = Prompt.inputString(
        String.format("| 상태[%s]\n| >> 공고중 >> 입양완료 ? ", fields[8]));

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

    stmt.executeUpdate("other/update", 
        String.format("%s,%s,%s,%s,%d,%s,%s,%s", 
            species,photos, breeds, genders, ages, dates, places, status));
  }
}
