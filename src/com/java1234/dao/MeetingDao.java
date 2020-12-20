package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.Meeting;
import com.java1234.model.Room;

public class MeetingDao {
	/**
	 * 查询菲空闲的会议
	 * @param con
	 * @param meeting
	 * @return
	 * @throws Exception
	 */
	public ResultSet list (Connection con, int usersid)throws Exception{
		String sql="select * from t_meeting where userid=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,usersid);
		return pstmt.executeQuery();
	}
	public ResultSet search (Connection con, Meeting meeting)throws Exception{
		String sql="select roomid from t_meeting where date=? and (start<=? and end>? or start<? and end>=? or start>=? and end<=?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,meeting.getDate());
		pstmt.setInt(2,meeting.getStart());
		pstmt.setInt(3,meeting.getStart());
		pstmt.setInt(4,meeting.getEnd());
		pstmt.setInt(5,meeting.getEnd());
		pstmt.setInt(6,meeting.getStart());
		pstmt.setInt(7,meeting.getEnd());
		return pstmt.executeQuery();
	}
	
	public int delete(Connection con, String id)throws Exception{
		String sql="delete from t_meeting where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,id);
		return pstmt.executeUpdate();
	}
	
	public int delete(Connection con, String nowdate, String hours)throws Exception{
		String sql="delete from t_meeting where date<? or (date=? and end<=?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,nowdate);
		pstmt.setString(2,nowdate);
		pstmt.setInt(3,Integer.valueOf(hours));
		return pstmt.executeUpdate();
	}
	
	public int add (Connection con, Meeting meeting)throws Exception{
		String sql="insert into t_meeting values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,meeting.getRoomid());
		pstmt.setString(2,meeting.getDate());
		pstmt.setInt(3,meeting.getStart());
		pstmt.setInt(4,meeting.getEnd());
		pstmt.setInt(5,meeting.getUserid());
		pstmt.setString(6,meeting.getReason());
		return pstmt.executeUpdate();
	}
}
