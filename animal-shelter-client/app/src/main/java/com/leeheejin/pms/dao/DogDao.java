package com.leeheejin.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.leeheejin.pms.domain.Dog;
import com.leeheejin.pms.domain.Manager;

public class DogDao {

  Connection con;

  public DogDao() throws Exception {
    this.con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
  }

  public int insert(Dog dog) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("insert into pms_animal_dog(photo,breed,gender,age,rdt,place,writer) values(?,?,?,?,?,?,?)");) {

      stmt.setString(1, dog.getPhotos());
      stmt.setString(2, dog.getBreeds());
      stmt.setString(3, dog.getGenders());
      stmt.setInt(4, dog.getAges());
      stmt.setDate(5, dog.getDates());
      stmt.setString(6, dog.getPlaces());

      return stmt.executeUpdate();
    } 
  }

  public List<Dog> findAll() throws Exception {
    ArrayList<Dog> list = new ArrayList<>();

    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " d.id,"
            + " d.photo,"
            + " d.breed,"
            + " d.age,"
            + " d.status,"
            + " m.no as writer_no,"
            + " m.name as writer_name"
            + " from pms_animal_dog d"
            + "   inner join pms_manager m on m.no=b.writer"
            + " order by b.no desc"
        );
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        Dog d = new Dog();
        d.setIds(rs.getInt("id"));
        d.setPhotos(rs.getString("photo"));
        d.setBreeds(rs.getString("breed"));
        d.setAges(rs.getInt("age"));
        d.setStatus(rs.getString("status"));

        Manager writer = new Manager();
        writer.setNo(rs.getInt("writer_no"));
        writer.setName(rs.getString("writer_name"));
        d.setWriter(writer);

        list.add(d);
      }
    }
    return list;
  }

  public Dog findById(int id) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from pms_animal_dog where id = ?")) {

      stmt.setInt(1, id);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        Dog d = new Dog();
        d.setIds(id);
        d.setPhotos(rs.getString("photo"));
        d.setBreeds(rs.getString("email"));
        d.setGenders(rs.getString("gender"));
        d.setAges(rs.getInt("age"));
        d.setDates(rs.getDate("rdt"));
        d.setPlaces(rs.getString("place"));
        d.setStatus(rs.getString("status"));

        Manager writer = new Manager();
        writer.setNo(rs.getInt("writer_no"));
        writer.setName(rs.getString("writer_name"));
        d.setWriter(writer);

        return d;
      }
    }
  }

  public int update(Dog dog) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update pms_animal_dog set status=? where id=?")) {

      stmt.setString(1, dog.getStatus());
      stmt.setInt(2, dog.getIds());

      return stmt.executeUpdate();
    }
  }

  public int delete(int id) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from pms_animal_dog where id=?")) {
      stmt.setInt(1, id);
      return stmt.executeUpdate();
    }
  }
}
