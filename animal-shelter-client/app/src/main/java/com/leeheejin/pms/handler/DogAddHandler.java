package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.leeheejin.pms.domain.Dog;
import com.leeheejin.util.Prompt;

public class DogAddHandler implements Command {

  ManagerValidator managerValidator;

  public DogAddHandler(ManagerValidator managerValidator) {
    this.managerValidator = managerValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 신규 개 등록 +-+-+");

    Dog d = new Dog();

    d.setWriter(managerValidator.inputManager("| 작성자 이름? "));
    if (d.getWriter() == null) {
      System.out.println("+---------------------------------------+");
      System.out.println("| 동물 등록은 관리자 권한이 필요합니다. |");
      System.out.println("+---------------------------------------+");
      return;
    }

    d.setPhotos(Prompt.inputString("| 사진? "));
    d.setBreeds(Prompt.inputString("| 품종? "));
    d.setGenders(Prompt.inputString("| 성별? "));
    d.setAges(Prompt.inputInt("| 나이? "));
    d.setDates(Prompt.inputDate("| 구조일? "));
    d.setPlaces(Prompt.inputString("| 구조장소? "));

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "insert into pms_animal_dog(photo,breed,gender,age,rdt,place,writer) values(?,?,?,?,?,?,?)");) {

      stmt.setString(1, d.getPhotos());
      stmt.setString(2, d.getBreeds());
      stmt.setString(3, d.getGenders());
      stmt.setInt(4, d.getAges());
      stmt.setDate(5, d.getDates());
      stmt.setString(6, d.getPlaces());
      stmt.setInt(7, d.getWriter().getNo());
      stmt.executeUpdate();
      System.out.println("+---------------------------+");
      System.out.println("| 신규 개를 등록하였습니다. |");
      System.out.println("+---------------------------+");
    }
  }
}






