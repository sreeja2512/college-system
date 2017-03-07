package com.global.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DaoImpl {

	public static Connection getConnection(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}
}
