package com.nikitachizhik91.university.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nikitachizhik91.university.domain.DomainException;
import com.nikitachizhik91.university.domain.StudentManager;
import com.nikitachizhik91.university.domain.impl.StudentManagerImpl;
import com.nikitachizhik91.university.model.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(StudentServlet.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		findAll(request, response);
		
		log.info("Found all students.");
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.trace("Started findAll() method.");

		List<Student> students = null;
		StudentManager studentManager = new StudentManagerImpl();

		try {
			students = studentManager.findAll();
		} catch (DomainException e) {
			log.error("Cannot find all students.", e);
			throw new ServletException("Cannot find all students.", e);
		}

		request.setAttribute("allStudents", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/allStudents.jsp");
		dispatcher.forward(request, response);

		log.trace("Dispathcer forwarded requeset and response to /Studentservlet.");
		log.trace("Started findAll() method.");
	}

}
