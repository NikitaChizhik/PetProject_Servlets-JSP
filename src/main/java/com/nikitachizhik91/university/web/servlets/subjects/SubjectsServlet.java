package com.nikitachizhik91.university.web.servlets.subjects;

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
import com.nikitachizhik91.university.domain.SubjectManager;
import com.nikitachizhik91.university.domain.impl.SubjectManagerImpl;
import com.nikitachizhik91.university.model.Subject;
import com.nikitachizhik91.university.web.WebException;

@WebServlet("/subjects")
public class SubjectsServlet extends HttpServlet {

	private final static Logger log = LogManager.getLogger(SubjectsServlet.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.trace("Get request to find all subjects");

		SubjectManager subjectManager = new SubjectManagerImpl();
		List<Subject> subjects = null;

		try {
			
			subjects = subjectManager.findAll();

		} catch (DomainException e) {

			log.error("Cannot find all subjects.", e);
			throw new WebException("Cannot find all subjects.", e);
		}

		request.setAttribute("subjects", subjects);
		request.getRequestDispatcher("/subjects.jsp").forward(request, response);

		log.trace("Found all subjects");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");

		log.trace("Post request to create subject with name=" + name);

		Subject subject = new Subject();
		subject.setName(name);

		SubjectManager subjectManager = new SubjectManagerImpl();

		try {

			subjectManager.create(subject);

		} catch (DomainException e) {

			log.error("Cannot add subject=" + subject, e);
			throw new WebException("Cannot add subject=" + subject, e);
		}

		response.sendRedirect("subjects");

		log.trace("Created subject with name=" + name);
	}
}
