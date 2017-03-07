package com.global.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

	public String registerUser(User user){
		try {
			Connection con = DaoImpl.getConnection();
			String sql = "insert into register(name,email,pass,mob) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setString(1, s.getRoll());
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPass());
			ps.setString(4, user.getMob());
			ps.execute();
			System.out.println("execute");
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}
	
	public User authUser(User user){
		User validUser = new User();
		try {
			Connection con = DaoImpl.getConnection();
			String sql = "select * from register where email=? and pass=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPass());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				validUser.setId(rs.getInt(1));
				validUser.setName(rs.getString(2));
				validUser.setEmail(rs.getString(3));
				validUser.setPass(rs.getString(4));
				validUser.setMob(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return validUser;
	}
}
