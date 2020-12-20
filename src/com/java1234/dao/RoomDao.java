package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.java1234.model.Room;
import com.java1234.util.StringUtil;

public class RoomDao {

	public int add (Connection con, Room room)throws Exception{
		String sql="insert into t_room values(null,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,room.getName());
		pstmt.setInt(2,room.getSize());
		pstmt.setString(3,room.getLocation());
		return pstmt.executeUpdate();
	}
	
	public ResultSet list(Connection con, Room room)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_room");
		if(StringUtil.isNotEmpty(room.getName())) {
			sb.append(" and name like '%"+room.getName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public String findroom(Connection con, int rst)throws Exception{
		String sql="select location from t_room where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,rst);
		ResultSet rs=pstmt.executeQuery();
		String loc="";
		if(rs.next()) {
			loc=rs.getString("location");
		}
		return loc;
	}
	
	public ResultSet findid(Connection con, ResultSet rs,int member)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_room");
		while(rs.next()) {
			sb.append(" and id!="+rs.getInt("roomid"));
		}
		sb.append(" and size>="+member);
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	public ResultSet findid(Connection con, Room room)throws Exception{
		String sql="select * from t_room where name=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,room.getName());
		return pstmt.executeQuery();
	}
	public int delete(Connection con, String id)throws Exception{
		String sql="delete from t_room where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,id);
		return pstmt.executeUpdate();
	}
	
	public int update(Connection con, Room room)throws Exception{
		String sql="update t_room set name=?,size=?,location=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,room.getName());
		pstmt.setInt(2,room.getSize());
		pstmt.setString(3,room.getLocation());
		pstmt.setInt(4,room.getId());
		return pstmt.executeUpdate();
	}
}
