package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.util.Prompt;

public class CatAddHandler implements Command {

  ManagerValidator managerValidator;

  public CatAddHandler(ManagerValidator managerValidator) {
    this.managerValidator = managerValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 신규 고양이 등록 +-+-+");

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

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "insert into pms_animal_cat(photo,breed,gender,age,rdt,place,writer) values(?,?,?,?,?,?,?)");) {

      stmt.setString(1, c.getPhotos());
      stmt.setString(2, c.getBreeds());
      stmt.setString(3, c.getGenders());
      stmt.setInt(4, c.getAges());
      stmt.setDate(5, c.getDates());
      stmt.setString(6, c.getPlaces());
      stmt.setInt(7, c.getWriter().getNo());
      stmt.executeUpdate();
      System.out.println("+-------------------------------+");
      System.out.println("| 신규 고양이를 등록하였습니다. |");
      System.out.println("+-------------------------------+");
    }
  }
}






