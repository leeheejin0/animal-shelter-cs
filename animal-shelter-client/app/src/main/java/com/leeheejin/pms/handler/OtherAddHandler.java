package com.leeheejin.pms.handler;

import com.leeheejin.driver.Statement;
import com.leeheejin.pms.domain.Other;
import com.leeheejin.util.Prompt;

public class OtherAddHandler implements Command {

  Statement stmt;
  ManagerValidator managerValidator;

  public OtherAddHandler(Statement stmt,ManagerValidator managerValidator) {
    this.stmt = stmt;
    this.managerValidator = managerValidator;
  }

  @Override
  public void service() throws Exception {

    System.out.println("+-+-+ 신규 기타동물 등록 +-+-+");

    try {
      Other o = new Other();

      o.setWriter(managerValidator.inputManager("| 작성자 이름? "));
      if (o.getWriter() == null) {
        System.out.println("+---------------------------------------+");
        System.out.println("| 동물 등록은 관리자 권한이 필요합니다. |");
        System.out.println("+---------------------------------------+");
        return;
      }

      o.setSpecies(Prompt.inputString("종류? "));
      o.setPhotos(Prompt.inputString("사진? "));
      o.setBreeds(Prompt.inputString("품종? "));
      o.setGenders(Prompt.inputString("성별? "));
      o.setAges(Prompt.inputInt("나이? "));
      o.setDates(Prompt.inputDate("구조일? "));
      o.setPlaces(Prompt.inputString("구조장소? "));

      stmt.executeUpdate("other/insert", 
          String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", 
              o.getSpecies(), o.getPhotos(), o.getBreeds(), o.getGenders(), 
              o.getAges(), o.getDates(), o.getPlaces(), o.getStatus(), o.getWriter()));

      System.out.println("+------------------------+");
      System.out.println("| 등록이 완료되었습니다. |");
      System.out.println("+------------------------+");
      System.out.println();

    } catch (Exception e) {
      System.out.println("+----------------------+");
      System.out.println("|  잘못된 입력입니다.  |");
      System.out.println("+----------------------+");
    }
  }
}
