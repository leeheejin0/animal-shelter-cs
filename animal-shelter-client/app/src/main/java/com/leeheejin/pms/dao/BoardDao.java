package com.leeheejin.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.pms.domain.Member;

public class BoardDao {
  public static int insert(Board board) throws Exception {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt =
            con.prepareStatement("insert into pms_board(title, content, writer) values(?,?,?)");) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getWriter().getNo());

      return stmt.executeUpdate();
    } 
  }

  public static List<Board> findAll() throws Exception {
    ArrayList<Board> list = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/asdb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement(
            "select"
                + " b.no,"
                + " b.title,"
                + " b.rdt,"
                + " b.vw_cnt,"
                + " b.like_cnt,"
                + " m.no as writer_no,"
                + " m.name as writer_name"
                + " from pms_board b"
                + "   inner join pms_member m on m.no=b.writer"
                + " order by b.no desc");
        ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("no"));
        board.setTitle(rs.getString("title"));
        board.setRegisteredDate(rs.getDate("rdt"));
        board.setViewCount(rs.getInt("vw_cnt"));

        Member writer = new Member();
        writer.setNo(rs.getInt("writer_no"));
        writer.setName(rs.getString("writer_name"));
        board.setWriter(writer);

        list.add(board);
      }
    }
    return list;
  }



}
