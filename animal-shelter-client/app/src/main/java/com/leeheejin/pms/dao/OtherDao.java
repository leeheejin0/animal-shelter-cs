package com.leeheejin.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.pms.domain.Manager;

public class OtherDao {

  Connection con;

  public OtherDao() throws Exception {
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
        c.setBreeds(rs.getString("breed"));
        c.setAges(rs.getInt("age"));
        c.setStatus(rs.getString("status"));

        Manager writer = new Manager();
        writer.setNo(rs.getInt("writer_no"));
        writer.setName(rs.getString("writer_name"));
        c.setWriter(writer);

        list.add(c);
      }
    }
    return list;
  }

  public Cat findById(int id) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from pms_animal_cat where id = ?")) {

      stmt.setInt(1, id);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        Cat c = new Cat();
        c.setIds(id);
        c.setPhotos(rs.getString("photo"));
        c.setBreeds(rs.getString("email"));
        c.setGenders(rs.getString("gender"));
        c.setAges(rs.getInt("age"));
        c.setDates(rs.getDate("rdt"));
        c.setPlaces(rs.getString("place"));
        c.setStatus(rs.getString("status"));

        Manager writer = new Manager();
        writer.setNo(rs.getInt("writer_no"));
        writer.setName(rs.getString("writer_name"));
        c.setWriter(writer);

        return c;
      }
    }
  }

  public int update(Cat cat) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update pms_animal_cat set status=? where id=?")) {

      stmt.setString(1, cat.getStatus());
      stmt.setInt(2, cat.getIds());

      return stmt.executeUpdate();
    }
  }

  public int delete(int id) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from pms_animal_cat where id=?")) {
      stmt.setInt(1, id);
      return stmt.executeUpdate();
    }
  }
}
