package com.global.student;

import java.sql.Date;

public class Student {

	private String roll;
	private String name;
	private String add;
	private String phno;
	private String gender;
	private Date dob;
	private Date doa;
	private String stud_class;
	private String stud_status;
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(String roll, String name, String add, String phno,
			String gender, Date dob, Date doa, String stud_class, String stud_status) {
		super();
		this.roll = roll;
		this.name = name;
		this.add = add;
		this.phno = phno;
		this.gender = gender;
		this.dob = dob;
		this.doa = doa;
		this.stud_class = stud_class;
		this.stud_status = stud_status;
	}

	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getDoa() {
		return doa;
	}
	public void setDoa(Date doa) {
		this.doa = doa;
	}
	public String getStud_class() {
		return stud_class;
	}
	public void setStud_class(String stud_class) {
		this.stud_class = stud_class;
	}
	
	public String getStud_status() {
		return stud_status;
	}

	public void setStud_status(String stud_status) {
		this.stud_status = stud_status;
	}

	@Override
	public String toString() {
		return "Student [roll=" + roll + ", name=" + name + ", add=" + add
				+ ", phno=" + phno + ", gender=" + gender + ", dob=" + dob
				+ ", doa=" + doa + ", stud_class=" + stud_class + ", stud_status=" + stud_status + "]";
	}
	
}
