package com.leeheejin.pms.handler;

import com.leeheejin.driver.Statement;
import com.leeheejin.pms.domain.Dog;
import com.leeheejin.util.Prompt;

public class DogAddHandler implements Command {

  Statement stmt;
  ManagerValidator managerValidator;

  public DogAddHandler(Statement stmt,ManagerValidator managerValidator) {
    this.stmt = stmt;
    this.managerValidator = managerValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 신규 개 등록 +-+-+");

    try {
      Dog d = new Dog();

      d.setWriter(managerValidator.inputManager("| 작성자 이름? "));
      if (d.getWriter() == null) {
        System.out.println("+---------------------------------------+");
        System.out.println("| 동물 등록은 관리자 권한이 필요합니다. |");
        System.out.println("+---------------------------------------+");
        return;
      }

      d.setPhotos(Prompt.inputString("사진? "));
      d.setBreeds(Prompt.inputString("품종? "));
      d.setGenders(Prompt.inputString("성별? "));
      d.setAges(Prompt.inputInt("나이? "));
      d.setDates(Prompt.inputDate("구조일? "));
      d.setPlaces(Prompt.inputString("구조장소? "));

      stmt.executeUpdate("dog/insert", 
          String.format("%s,%s,%s,%s,%s,%s,%s,%s", 
              d.getPhotos(), d.getBreeds(), d.getGenders(), 
              d.getAges(), d.getDates(), d.getPlaces(), d.getStatus(), d.getWriter()));

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
