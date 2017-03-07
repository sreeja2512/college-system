package com.global.student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		UserService us = new UserService();
		//User user = us.authUser(user);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String responseMsg = "";
		String validUser = "";
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
		JSONObject joUser = null;
		try {
			joUser = (JSONObject) parser.parse(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String name = (String) joUser.get("name");
		String email = (String) joUser.get("emailid");
		String pass = (String) joUser.get("password");
		String mob = (String) joUser.get("mobno");
		
		User user = new User();

		
		user.setName(name);
		user.setEmail(email);
		user.setMob(mob);
		user.setPass(pass);
		
		
		
		UserService userService = new UserService();
		String authUser = request.getParameter("authUser");
		if(authUser != null && authUser.equals("authUser")){
			//ss.updateStudent(s);
			user = userService.authUser(user);
			validUser = new Gson().toJson(user);
			responseMsg = "Valid User";
		}else{
			//ss.saveStudent(s);
			userService.registerUser(user);
			responseMsg = "User Registered Successfully..";
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		/*out.println("Sucess..");
		out.write(responseMsg);*/
		out.write(validUser);
		out.flush();
		out.close();
	}
	

}
