package com.leeheejin.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.pms.domain.Member;

public class CatDao {

  Connection con;

  public CatDao() throws Exception {
    this.con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
  }

  public int insert(Cat cat) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("insert into pms_animal_cat(photo,breed,gender,age,rdt,place,writer) values(?,?,?,?,?,?,?)");) {

      stmt.setString(1, cat.getPhotos());
      stmt.setString(2, cat.getBreeds());
      stmt.setString(3, cat.getGenders());
      stmt.setInt(4, cat.getAges());
      stmt.setDate(5, cat.getDates());
      stmt.setString(6, cat.getPlaces());

      return stmt.executeUpdate();
    } 
  }

  public List<Cat> findAll() throws Exception {
    ArrayList<Cat> list = new ArrayList<>();

    try (PreparedStatement stmt = con.prepareStatement(
        "select id,photo,breed,age,status"
            + " c.id,"
            + " c.photo,"
            + " c.breed,"
            + " c.age,"
            + " c.status,"
            + " m.no as writer_no,"
            + " m.name as writer_name"
            + " from pms_animal_cat c"
            + "   inner join pms_manager m on m.no=b.writer"
            + " order by b.no desc"
        );
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        Cat c = new Cat();
        c.setIds(rs.getInt("id"));
        c.setPhotos(rs.getString("photo"));
        c.setBreeds(rs.getString("email"));
        c.setAges(rs.getInt("photo"));
        c.setStatus(rs.getString("tel"));

        list.add(c);
      }
    }
    return list;
  }

  public Member findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from pms_animal_cat where no = ?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        Member m = new Member();
        m.setNo(no);
        m.setName(rs.getString("name"));
        m.setEmail(rs.getString("email"));
        m.setPhoto(rs.getString("photo"));
        m.setTel(rs.getString("tel"));
        m.setRegisteredDate(rs.getDate("rdt"));

        return m;
      }
    }
  }

  public int update(Member member) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update pms_animal_cat set name=?,id=?,email=?,password=password(?),photo=?,tel=? where no=?")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getId());
      stmt.setString(3, member.getEmail());
      stmt.setString(4, member.getPassword());
      stmt.setString(5, member.getPhoto());
      stmt.setString(6, member.getTel());
      stmt.setInt(7, member.getNo());
      return stmt.executeUpdate();
    }
  }

  public int delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from pms_animal_cat where no=?")) {
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }

  public Member findByName(String name) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from pms_animal_cat where name=?")) {

      stmt.setString(1, name);

      ResultSet rs = stmt.executeQuery();

      if (!rs.next()) {
        return null;
      }

      Member m = new Member();
      m.setNo(rs.getInt("no"));
      m.setName(rs.getString("name"));
      m.setId(rs.getString("id"));
      m.setEmail(rs.getString("email"));
      m.setPhoto(rs.getString("photo"));
      m.setTel(rs.getString("tel"));
      m.setRegisteredDate(rs.getDate("rdt"));

      return m;
    }
  }

}
