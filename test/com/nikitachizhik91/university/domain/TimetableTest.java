package com.nikitachizhik91.university.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

//razbit klassi
//ubrat lishnuu inizializaziu
public class TimetableTest {
	University university;
	List<Faculty> faculties;
	Faculty faculty1;
	ArrayList<Department> departments;
	Department department1;
	List<Subject> subjects;
	List<Teacher> teachers;
	List<Group> groups;
	List<Room> rooms;
	Room room1;
	Group group1;
	Subject subject1;
	Timetable timetable;
	Teacher teacher1;
	Teacher teacher2;
	Lesson lesson1;
	Lesson lesson2;
	Student student1;
	Student student3;
	SimpleDateFormat dateFormat;
	String dateString;
	Date dateToCheck;
	Timetable expectedTimetable;
	List<Lesson> expectedLessons;

	@Before
	public void initialize() throws ParseException {

		university = new University();
		faculties = new ArrayList<Faculty>();
		faculty1 = new Faculty();
		departments = new ArrayList<Department>();
		department1 = new Department();
		subjects = new ArrayList<Subject>();
		teachers = new ArrayList<Teacher>();
		groups = new ArrayList<Group>();
		rooms = new ArrayList<Room>();
		timetable = new Timetable();

		department1.setId(1);
		department1.setName("Phonetics");

		subject1 = new Subject();
		subject1.setId(1);
		subject1.setName("english speaking practice");
		subjects.add(subject1);
		Subject subject2 = new Subject();
		subject2.setId(2);
		subject2.setName("LISTENING");
		subjects.add(subject2);
		department1.setSubjects(subjects);

		teacher1 = new Teacher();
		teacher1.setId(1);
		teacher1.setName("Nikita Chizhik");
		teacher1.setSubject(subject1);
		teachers.add(teacher1);
		teacher2 = new Teacher();
		teacher2.setId(2);
		teacher2.setName("Mikola brutski");
		teachers.add(teacher2);
		department1.setTeachers(teachers);

		departments.add(department1);
		faculty1.setDepartments(departments);

		Group group2 = new Group();
		group2.setId(2);
		group2.setName("37");
		List<Student> students = new ArrayList<Student>();
		student1 = new Student();
		student1.setId(2);
		student1.setName("Sasha");
		students.add(student1);
		Student student2 = new Student();
		students.add(student2);
		group2.setStudents(students);
		groups.add(group2);

		group1 = new Group();
		group1.setId(1);
		group1.setName("36");
		List<Student> students2 = new ArrayList<Student>();
		student3 = new Student();
		student3.setId(1);
		student3.setName("David");
		student3.setGroup(group1);
		students2.add(student3);
		Student student4 = new Student();
		students2.add(student4);
		group1.setStudents(students2);
		groups.add(group1);

		faculty1.setId(4455);
		faculty1.setGroups(groups);
		faculty1.setName("Faculty of English Language");

		faculties.add(faculty1);

		university.setFaculties(faculties);

		room1 = new Room();
		room1.setNumber("11");
		Room room2 = new Room();
		room2.setNumber("12");
		Room room3 = new Room();
		room3.setNumber("13");
		Room room4 = new Room();
		room4.setNumber("13b");
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);

		university.setRooms(rooms);

		lesson1 = new Lesson();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String dateInString = "16-02-2017 10:20";
		Date date = sdf.parse(dateInString);
		lesson1.setDate(date);

		lesson1.setGroup(group1);
		lesson1.setSubject(subject1);

		lesson1.setNumber(3);
		lesson1.setRoom(room4);
		lesson1.setTeacher(teacher1);

		timetable.addLesson(lesson1);

		lesson2 = new Lesson();

		String dateInString2 = "17-03-2017 08:20";
		Date date2 = sdf.parse(dateInString2);
		lesson2.setDate(date2);
		lesson2.setGroup(group2);
		lesson2.setSubject(subject2);

		lesson2.setNumber(3);
		lesson2.setRoom(room2);
		teacher2.setSubject(subject2);
		lesson2.setTeacher(teacher2);

		timetable.addLesson(lesson2);

		university.setTimetable(timetable);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForMonth_NullandNull() throws ParseException {

		university.getTimetable().getStudentsTimetableForMonth(null, null);

	}

	@Test
	public void getTeachersTimetableForMonth_general() throws ParseException {

		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);
		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson1);
		expectedTimetable = new Timetable();
		expectedTimetable.setLessons(expectedLessons);

		assertEquals("two timetables are not equal.", expectedTimetable.getLessons(), university.getTimetable()
				.getTeachersTimetableForMonth(teacher1, dateToCheck).getLessons());

	}

	@Test
	public void getTeachersTimetableForDay_general() throws ParseException {

		dateFormat = new SimpleDateFormat("dd-M-yyyy");
		dateString = "17-03-2017";
		dateToCheck = dateFormat.parse(dateString);

		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson2);
		expectedTimetable = new Timetable();
		expectedTimetable.setLessons(expectedLessons);

		assertEquals("two timetables are not equal.", expectedTimetable.getLessons(), university.getTimetable()
				.getTeachersTimetableForMonth(teacher2, dateToCheck).getLessons());

	}

	@Test
	public void getStudentsTimetableForMonth_general() throws ParseException {
		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);

		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson1);
		expectedTimetable = new Timetable();
		expectedTimetable.setLessons(expectedLessons);

		assertEquals("two timetables are not equal.", expectedTimetable.getLessons(), university.getTimetable()
				.getStudentsTimetableForMonth(student3, dateToCheck).getLessons());

	}

	@Test
	public void getStudentsTimetableForDay_general() throws ParseException {

		dateFormat = new SimpleDateFormat("dd-M-yyyy");
		dateString = "16-02-2017";
		dateToCheck = dateFormat.parse(dateString);

		expectedLessons = new ArrayList<Lesson>();
		expectedLessons.add(lesson1);
		expectedTimetable = new Timetable();
		expectedTimetable.setLessons(expectedLessons);

		assertEquals("two timetables are not equal.", expectedTimetable.getLessons(), university.getTimetable()
				.getStudentsTimetableForDay(student3, dateToCheck).getLessons());

	}

	@Test
	public void getTeachersTimetableForMonth_requiredDate() throws ParseException {

		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);

		Date recivedDate = university.getTimetable().getTeachersTimetableForMonth(teacher1, dateToCheck).getLessons()
				.get(0).getDate();

		Calendar inputDate = Calendar.getInstance();
		inputDate.setTime(dateToCheck);

		Calendar expectedDate = Calendar.getInstance();
		inputDate.setTime(recivedDate);

		assertEquals("Month is wrong.", expectedDate.get(Calendar.MONTH), inputDate.get(Calendar.MONTH));
		assertEquals("Year is wrong.", expectedDate.get(Calendar.YEAR), inputDate.get(Calendar.YEAR));

	}

	@Test
	public void getTeachersTimetableForMonth_requiredTeacher() throws ParseException {
		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);

		Teacher recivedTeacher = university.getTimetable().getTeachersTimetableForMonth(teacher1, dateToCheck)
				.getLessons().get(0).getTeacher();

		assertEquals("teacher is wrong.", teacher1, recivedTeacher);

	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForMonth_NullandOK() throws ParseException {
		dateFormat = new SimpleDateFormat("M-yyyy");
		dateString = "02-2017";
		dateToCheck = dateFormat.parse(dateString);
		university.getTimetable().getTeachersTimetableForMonth(null, dateToCheck);

	}

	@Test(expected = IllegalArgumentException.class)
	public void getTeachersTimetableForMonth_OkandNull() {
		university.getTimetable().getTeachersTimetableForMonth(teacher1, null);

	}

	// ////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void addFaculty() {
		Faculty facultyTest = new Faculty();
		facultyTest.setId(52234);
		university.addFaculty(facultyTest);

		assertTrue("faculty is not added", university.getFaculties().contains(facultyTest));

	}

	@Test(expected = IllegalArgumentException.class)
	public void addFaculty_Null() {

		university.addFaculty(null);

	}

	@Test
	public void deleteFaculty() {

		university.deleteFaculty(faculty1);
		;

		assertFalse("faculty is not deleted", university.getFaculties().contains(faculty1));

	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteFaculty_Null() {

		university.deleteFaculty(null);

	}

	@Test
	public void addRoom() {
		Room roomTest = new Room();
		roomTest.setId(12);
		university.getRooms().add(roomTest);

		assertTrue("room is not added", university.getRooms().contains(roomTest));

	}

	@Test(expected = IllegalArgumentException.class)
	public void addRoom_Null() {

		university.addRoom(null);

	}

	@Test
	public void deleteRoom() {

		university.getRooms().remove(room1);

		assertFalse("room is not deleted", university.getFaculties().contains(room1));

	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteRoom_Null() {

		university.deleteRoom(null);

	}

	@Test
	public void addLesson() {
		Lesson lessonTest = new Lesson();
		lessonTest.setId(2525);
		university.getTimetable().addLesson(lessonTest);

		assertTrue("lesson is not added.", university.getTimetable().getLessons().contains(lessonTest));

	}

	@Test(expected = IllegalArgumentException.class)
	public void addLesson_Null() {

		university.getTimetable().addLesson(null);

	}

	@Test
	public void deleteLesson() {

		university.getTimetable().getLessons().remove(lesson2);

		assertFalse("lesson is not deleted.", university.getTimetable().getLessons().contains(lesson2));

	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteLesson_Null() {

		university.getTimetable().deleteLesson(null);

	}

	@Test
	public void addStudent() {
		Student studentTest = new Student();
		studentTest.setId(6655);
		university.getFaculties().get(0).getGroups().get(0).addStudent(studentTest);

		assertTrue("student is not added.",
				university.getFaculties().get(0).getGroups().get(0).getStudents().contains(studentTest));

	}

	@Test(expected = IllegalArgumentException.class)
	public void addStudent_Null() {

		university.getFaculties().get(0).getGroups().get(0).addStudent(null);

	}

	@Test
	public void deleteStudent() {

		university.getFaculties().get(0).getGroups().get(0).deleteStudent(student1);

		assertFalse("student is not deleted.", university.getFaculties().get(0).getGroups().get(0).getStudents()
				.contains(student1));

	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteStudent_Null() {

		university.getFaculties().get(0).getGroups().get(0).deleteStudent(null);

	}

}