package com.nikitachizhik91.university.web.servlets.students;

import java.io.IOException;
import java.util.List;

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
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(StudentsServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.trace("Get request to find all students");

		StudentManager studentManager = new StudentManagerImpl();
		List<Student> students = null;

		try {
			students = studentManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all students.", e);
			throw new WebException("Cannot find all students.", e);
		}

		request.setAttribute("students", students);
		request.getRequestDispatcher("/students.jsp").forward(request, response);

		log.trace("Found all students");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");

		log.trace("Post request to create student with name=" + name);

		Student student = new Student();
		student.setName(name);

		StudentManager studentManager = new StudentManagerImpl();

		try {
			studentManager.create(student);

		} catch (DomainException e) {

			log.error("Cannot add student=" + student, e);
			throw new WebException("Cannot add student=" + student, e);
		}

		response.sendRedirect("students");

		log.trace("Created student with name=" + name);
	}
}
