package com.leeheejin.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.leeheejin.pms.domain.Manager;

public class ManagerDao {

  Connection con;

  public ManagerDao() throws Exception {
    this.con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
  }

  public int insert(Manager manager) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("insert into pms_manager(name,id,password,email,tel,photo) values(?,?,?,?,?,?)");) {

      stmt.setString(1, manager.getName());
      stmt.setString(2, manager.getId());
      stmt.setString(3, manager.getPassword());
      stmt.setString(4, manager.getEmail());
      stmt.setString(5, manager.getTel());
      stmt.setString(6, manager.getPhoto());

      return stmt.executeUpdate();
    } 
  }

  public List<Manager> findAll() throws Exception {
    ArrayList<Manager> list = new ArrayList<>();

    try (PreparedStatement stmt = con.prepareStatement(
        "select no,name,id,email,photo,tel from pms_manager order by name asc");
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        Manager m = new Manager();
        m.setNo(rs.getInt("no"));
        m.setId(rs.getString("id"));
        m.setName(rs.getString("name"));
        m.setEmail(rs.getString("email"));
        m.setPhoto(rs.getString("photo"));
        m.setTel(rs.getString("tel"));

        list.add(m);
      }
    }
    return list;
  }

  public Manager findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from pms_manager where no = ?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        Manager m = new Manager();
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

  public int update(Manager manager) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update pms_manager set name=?,id=?,email=?,password=password(?),photo=?,tel=? where no=?")) {

      stmt.setString(1, manager.getName());
      stmt.setString(2, manager.getId());
      stmt.setString(3, manager.getEmail());
      stmt.setString(4, manager.getPassword());
      stmt.setString(5, manager.getPhoto());
      stmt.setString(6, manager.getTel());
      stmt.setInt(7, manager.getNo());
      return stmt.executeUpdate();
    }
  }

  public int delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from pms_manager where no=?")) {
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }

  public Manager findByName(String name) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from pms_manager where name=?")) {

      stmt.setString(1, name);

      ResultSet rs = stmt.executeQuery();

      if (!rs.next()) {
        return null;
      }

      Manager m = new Manager();
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
