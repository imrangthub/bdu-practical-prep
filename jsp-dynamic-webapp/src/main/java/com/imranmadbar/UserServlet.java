package com.imranmadbar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    public UserServlet() { 
        super(); 
        // TODO Auto-generated constructor stub 
    } 
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	  
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/user.jsp");
        dispatcher.forward(request, response);
      
    }  

}
