package com.java1234.util;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * 数据库工具类
 * @author zhang
 *
 */
public class DbUtil {

	private String dbUrl="jdbc:mysql://localhost:3306/db_meeting";
	private String dbUserName="root";
	private String dbPassword="root";
	private String jdbcName="com.mysql.jdbc.Driver";
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	/**
	 * 关闭连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con)throws Exception{
		if(con!=null) {
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("yes");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("no");
		}
	}
}
