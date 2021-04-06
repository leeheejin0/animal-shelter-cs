package com.leeheejin.pms.handler;

import java.util.List;
import com.leeheejin.pms.domain.Member;
import com.leeheejin.util.Prompt;

public class ManagerLogInHandler extends AbstractMemberHandler {

  public ManagerLogInHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {

  }

  public int logIn() {
    System.out.println("[ 홈 > 로그인* ]");
    System.out.println("(뒤로가기| 빈 문자열)");
    while (true) {
      String id = Prompt.inputString("아이디: ");
      String password = Prompt.inputString("비밀번호: ");
      if(exist(id, password)) {      
        logInAccount = nowLogIn - 1;
        accountRemove = 1;
        System.out.println("- 로그인 되었습니다. ");
        System.out.println();
        return 1;
      } else if (id.length() == 0 && password.length() == 0) {
        break;
      } else {
        System.out.println("- 회원 정보를 찾을 수 없습니다. ");
        System.out.println();
      }
    }
    return 0;
  }
}

