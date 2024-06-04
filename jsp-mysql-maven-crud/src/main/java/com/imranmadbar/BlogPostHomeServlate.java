package com.imranmadbar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/blog")
public class BlogPostHomeServlate extends HttpServlet {

    public BlogPostHomeServlate() { 
        super(); 
        // TODO Auto-generated constructor stub 
    } 
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	
    	BlogPostService blogObj = new BlogPostService();
    	
    	List<PostsModel> postModelList = new ArrayList<PostsModel>();
    	
    	postModelList = blogObj.index();
    	
    	//System.out.println("BlogData:"+postModelList);
  	  
        request.setAttribute("postModelList", postModelList);

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/blogPost/postListView.jsp");
        dispatcher.forward(request, response);
      
    }  


}
