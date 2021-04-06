package com.leeheejin.pms.handler;

import java.sql.Date;
import java.util.List;
import com.leeheejin.pms.domain.Member;
import com.leeheejin.util.Prompt;

public class MemberAddHandler extends AbstractMemberHandler {

  public MemberAddHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("[ 홈 > 회원가입* ]");
    try {
      Member m = new Member();
      m.setNo(memberList.size() + 1);
      m.setId(Prompt.inputString("아이디: "));
      m.setName(Prompt.inputString("이름: "));
      m.setEmail(Prompt.inputString("이메일: "));
      m.setTel(Prompt.inputString("전화번호: "));
      m.setPassword(Prompt.inputString("비밀번호: "));
      m.setRegisteredDate(new Date(System.currentTimeMillis()));

      memberList.add(m);

      System.out.println("- 가입이 완료되었습니다. ");
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
    System.out.println();
  }
}
