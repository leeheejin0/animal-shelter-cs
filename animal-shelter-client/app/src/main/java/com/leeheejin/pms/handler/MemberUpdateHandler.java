package com.leeheejin.pms.handler;

import java.util.List;
import com.leeheejin.pms.domain.Member;
import com.leeheejin.util.Prompt;

public class MemberUpdateHandler extends AbstractMemberHandler {

  public MemberUpdateHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[ 홈 > 관리자 메뉴 > 회원정보수정* ]");
    Member m = memberList.get(logInAccount);
    System.out.printf("[ %s | %s ] %s | %s | %s (%s)\n", 
        m.getId(), m.getPassword(), m.getName(), m.getEmail(), 
        m.getTel(), m.getRegisteredDate());

    int command = Prompt.inputInt("1: 상태수정 | 2: 회원탈퇴 | 3: 뒤로가기\n>> ");
    switch (command) {
      case 1:
        try {
          String id = Prompt.inputString("아이디: ");
          String password = Prompt.inputString("비밀번호: ");
          String name = Prompt.inputString("이름: ");
          String email = Prompt.inputString("이메일: ");
          String tel = Prompt.inputString("전화번호: ");

          m.setId(id);
          m.setPassword(password);
          m.setName(name);
          m.setEmail(email);
          m.setTel(tel);
          System.out.printf("[ %s | %s ] %s | %s | %s (%s)\n", 
              m.getId(), m.getPassword(), m.getName(), m.getEmail(), 
              m.getTel(), m.getRegisteredDate());
          System.out.println("- 회원정보가 변경되었습니다. ");
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.println("---------------------");
        }
        break;
      case 2:
        String dcommand = Prompt.inputString("- 회원정보를 삭제하시겠습니까?(y/N) ");
        if (dcommand.isEmpty() || dcommand.equalsIgnoreCase("n")) {
          System.out.println("- 회원정보 삭제가 취소되었습니다. ");
          System.out.println();
          break;
        } else if (dcommand.equalsIgnoreCase("y")) {
          memberList.remove(m);
          System.out.println("- 회원정보가 삭제되었습니다. ");
          System.out.println();
          accountRemove = -1;
        } else {
          System.out.println("- 잘못 입력하셨습니다. ");
          System.out.println();
        }
        break;
      case 3:
        return;
      default:
        System.out.println("실행할 수 없는 명령입니다.");
        System.out.println();
        break;
    }
  }
}

