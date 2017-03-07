package com.global.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StudentService {

	
	
	public String saveStudent(Student s){
		s.setStud_status("ENABLE");
		try {
			Connection con = DaoImpl.getConnection();
			String sql = "insert into stud_info_table(roll,name,addr,phno,gender,dob,dateOfAdmission,class) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, s.getRoll());
			ps.setString(2, s.getName());
			ps.setString(3, s.getAdd());
			ps.setString(4, s.getPhno());
			ps.setString(5, s.getGender());
			ps.setDate(6, s.getDob());
			ps.setDate(7, s.getDoa());
			ps.setString(8, s.getStud_class());
			ps.setString(9, s.getStud_status());
			ps.execute();
			System.out.println("execute");
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}
	
	public String updateStudent(Student s){
		try {
			Connection con = DaoImpl.getConnection();
			String sql = "update stud_info_table set name=?,addr=?,phno=?,gender=?,dob=?,dateOfAdmission=?,class=? where roll=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, s.getName());
			ps.setString(2, s.getAdd());
			ps.setString(3, s.getPhno());
			ps.setString(4, s.getGender());
			ps.setDate(5, s.getDob());
			ps.setDate(6, s.getDoa());
			ps.setString(7, s.getStud_class());
			ps.setString(8, s.getRoll());
			ps.execute();
			ps.close();
			con.close();
			System.out.println("success.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Updated Successfully..";
	}
	
	public String disableStudent(Student s){
		try {
			Connection con = DaoImpl.getConnection();
			String sql = "update stud_info_table set stud_status=? where roll=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "DISABLE");
			ps.setString(2, s.getRoll());
			ps.execute();
			ps.close();
			con.close();
			System.out.println("success.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Updated Successfully..";
	}
	
	public String enableStudent(Student s){
		try {
			Connection con = DaoImpl.getConnection();
			String sql = "update stud_info_table set stud_status=? where roll=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "ENABLE");
			ps.setString(2, s.getRoll());
			ps.execute();
			ps.close();
			con.close();
			System.out.println("success.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Updated Successfully..";
	}
	
	public List<Student> getStudentList(){
		List<Student> studentList = new ArrayList<Student>();
		
		try {
			Connection con = DaoImpl.getConnection();
			String sql = "select * from stud_info_table";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Student s = new Student();
				s.setRoll(rs.getString(1));
				s.setName(rs.getString(2));
				s.setAdd(rs.getString(3));
				s.setPhno(rs.getString(4));
				s.setGender(rs.getString(5));
				s.setDob(rs.getDate(6));
				s.setDoa(rs.getDate(7));
				s.setStud_class(rs.getString(8));
				s.setStud_status(rs.getString(9));
				studentList.add(s);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return studentList;
	}
}
