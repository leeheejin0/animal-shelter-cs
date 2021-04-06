package com.leeheejin.pms.handler;

import java.util.Iterator;
import com.leeheejin.driver.Statement;

public class DogListHandler implements Command {

  Statement stmt;

  public DogListHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {

    System.out.println("+-+-+ 개 구조목록 +-+-+");

    Iterator<String> results = stmt.executeQuery("dog/selectAll");

    while (results.hasNext()) {
      String[] fields = results.next().split(",");
      System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s\n",
          fields[0], 
          fields[1], 
          fields[2],
          fields[3],
          fields[4],
          fields[5], 
          fields[6],
          fields[7],
          fields[8]);
    }
  }
}
