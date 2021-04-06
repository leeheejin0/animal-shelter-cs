package com.leeheejin.pms.handler;

import com.leeheejin.driver.Statement;
import com.leeheejin.util.Prompt;

public class DogDetailHandler implements Command {

  Statement stmt;

  public DogDetailHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {

    System.out.println("+-+-+ 개 상세보기 +-+-+");

    int no = Prompt.inputInt("| 번호? ");

    String[] fields = 
        stmt.executeQuery("dog/select", Integer.toString(no)).next().split(",");
    System.out.println("+");
    System.out.printf("| 사진: %s\n ", fields[1]);
    System.out.printf("| 품종: %s\n ", fields[2]);
    System.out.printf("| 성별: %s\n ", fields[3]);
    System.out.printf("| 나이: %s\n ", fields[4]);
    System.out.printf("| 구조일: %s\n ", fields[5]);
    System.out.printf("| 구조장소: %s\n ", fields[6]);
    System.out.printf("| 상태: %s\n ", fields[7]);
    System.out.printf("| 등록자: %s\n ", fields[8]);
    System.out.println("+");
  }
}
