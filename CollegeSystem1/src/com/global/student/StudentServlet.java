package com.global.student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

/**
 * Servlet implementation class AbcServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		StudentService ss = new StudentService();
		List<Student> studentList = ss.getStudentList();
		String joStudent = new Gson().toJson(studentList);
		
		out.write(joStudent);
		/**
		 * old code
		 * try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
			String sql = "insert into stud_info_table values(3,'Sham','Pune',9999999999,'male','2000-07-11','2016-07-11','Bcom')";
			Statement st = con.createStatement();
			st.execute(sql);
			out.print("success....");
			st.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				StringBuffer sb = new StringBuffer();
				try {
					BufferedReader reader = request.getReader();
					String line = null;
					while((line = reader.readLine()) != null){
						sb.append(line);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				JSONParser parser = new JSONParser();
				JSONObject joStudent = null;
				try {
					joStudent = (JSONObject) parser.parse(sb.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				String roll = "";
				if(joStudent.get("roll") instanceof String){
					roll = (String) joStudent.get("roll");
				}else{
					roll = (joStudent.get("roll").toString());
				}
				
				String name = (String) joStudent.get("name");
				String add = (String) joStudent.get("add");
				String phno = (String) joStudent.get("phno");
				String gender = (String) joStudent.get("gender");
				String dob = (String) joStudent.get("dob");
				String doa = (String) joStudent.get("doa");
				String stud_class = (String) joStudent.get("stud_class");
				Student s = new Student();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateOfBirth=null, dateOfAdd=null;
				try {
					 dateOfBirth = sdf.parse(dob);
					 dateOfAdd = sdf.parse(doa);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Date sqlDob = new Date(dateOfBirth.getTime());
				Date sqlDoa = new Date(dateOfAdd.getTime());
				s.setRoll(roll);
				s.setName(name);
				s.setAdd(add);
				s.setPhno(phno);
				s.setGender(gender);
				s.setDob(sqlDob);
				s.setDoa(sqlDoa);
				s.setStud_class(stud_class);
				
				StudentService ss = new StudentService();
				String update = request.getParameter("update");
				String disable = request.getParameter("disableStud");
				String enable = request.getParameter("enableStud");
				if(update != null && update.equals("update")){
					ss.updateStudent(s);
				}else if(disable != null && disable.equals("disableStud")){
					ss.disableStudent(s);
				}else if(enable != null && enable.equals("enableStud")){
					ss.enableStudent(s);
				}else{
					ss.saveStudent(s);
				}
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("Sucess..");
				out.write("Student created sucessfully");
				out.flush();
				out.close();
			}
	}

