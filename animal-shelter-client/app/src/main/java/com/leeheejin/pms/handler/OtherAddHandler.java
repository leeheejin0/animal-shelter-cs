package com.leeheejin.pms.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.leeheejin.pms.domain.Other;
import com.leeheejin.util.Prompt;

public class OtherAddHandler implements Command {

  @Override
  public void service() throws Exception {
    System.out.println("+-+-+ 신규 기타동물 등록 +-+-+");

    Other o = new Other();

    //    o.setWriter(managerValidator.inputManager("| 작성자 이름? "));
    //    if (o.getWriter() == null) {
    //      System.out.println("+---------------------------------------+");
    //      System.out.println("| 동물 등록은 관리자 권한이 필요합니다. |");
    //      System.out.println("+---------------------------------------+");
    //      return;
    //    }

    o.setSpecies(Prompt.inputString("| 동물 종류? "));
    o.setPhotos(Prompt.inputString("| 사진? "));
    o.setBreeds(Prompt.inputString("| 품종? "));
    o.setGenders(Prompt.inputString("| 성별? "));
    o.setAges(Prompt.inputInt("| 나이? "));
    o.setDates(Prompt.inputDate("| 구조일? "));
    o.setPlaces(Prompt.inputString("| 구조장소? "));

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "insert into pms_animal_othr(species,photo,breed,gender,age,date,place) values(?,?,?,?,?,?,?)");) {

      stmt.setString(1, o.getSpecies());
      stmt.setString(2, o.getPhotos());
      stmt.setString(3, o.getBreeds());
      stmt.setString(4, o.getGenders());
      stmt.setInt(5, o.getAges());
      stmt.setDate(6, o.getDates());
      stmt.setString(7, o.getPlaces());
      stmt.executeUpdate();
      System.out.println("+---------------------------------+");
      System.out.println("| 신규 기타동물을 등록하였습니다. |");
      System.out.println("+---------------------------------+");
    }
  }
}






