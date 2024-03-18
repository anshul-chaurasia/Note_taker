package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entities.Note;
import com.helper.FactoryProvider;


public class SaveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveNoteServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
     
		try {//title,content fetch...
			System.out.println("Anshull");
			
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			Note note = new Note(title,content,new Date());
			//System.out.println(note.getId()+" : "+note.getTitle());
			
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			
			//hibernate save..
			Session s = factory.openSession();
			Transaction tx = s.beginTransaction();
			s.save(note);
			tx.commit();
			s.close();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1>Note is added successfully</h1>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
