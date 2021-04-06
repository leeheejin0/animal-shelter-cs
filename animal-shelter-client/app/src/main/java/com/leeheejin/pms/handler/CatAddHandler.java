package com.leeheejin.pms.handler;

import com.leeheejin.driver.Statement;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.util.Prompt;

public class CatAddHandler implements Command {

  Statement stmt;
  ManagerValidator managerValidator;

  public CatAddHandler(Statement stmt,ManagerValidator managerValidator) {
    this.stmt = stmt;
    this.managerValidator = managerValidator;
  }

  @Override
  public void service() throws Exception {

    System.out.println("+-+-+ 신규 고양이 등록 +-+-+");

    try {
      Cat c = new Cat();

      c.setWriter(managerValidator.inputManager("| 작성자 이름? "));
      if (c.getWriter() == null) {
        System.out.println("+---------------------------------------+");
        System.out.println("| 동물 등록은 관리자 권한이 필요합니다. |");
        System.out.println("+---------------------------------------+");
        return;
      }

      c.setPhotos(Prompt.inputString("| 사진? "));
      c.setBreeds(Prompt.inputString("| 품종? "));
      c.setGenders(Prompt.inputString("| 성별? "));
      c.setAges(Prompt.inputInt("| 나이? "));
      c.setDates(Prompt.inputDate("| 구조일? "));
      c.setPlaces(Prompt.inputString("| 구조장소? "));

      stmt.executeUpdate("cat/insert", 
          String.format("%s,%s,%s,%s,%s,%s,%s,%s", 
              c.getPhotos(), c.getBreeds(), c.getGenders(), c.getAges(), 
              c.getDates(), c.getPlaces(), c.getStatus(), c.getWriter()));

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
