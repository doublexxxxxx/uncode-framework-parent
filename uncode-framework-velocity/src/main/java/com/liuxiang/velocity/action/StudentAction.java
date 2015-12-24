package com.liuxiang.velocity.action;

import java.util.List;

import com.liuxiang.velocity.annotation.Action;
import com.liuxiang.velocity.annotation.Autowired;
import com.liuxiang.velocity.dao.StudentDao;
import com.liuxiang.velocity.model.Student;
import com.liuxiang.velocity.util.WebUtil;

@Action("studentAction")
public class StudentAction extends BaseAction{
	@Autowired
	public StudentDao studentDao;
	private List<Student> students;
	private Student student;
	private Integer id;
	private String name;
	private Integer age;
	private Integer height;
	private Integer weight;
	public String studentList() {
		students = studentDao.retrieveAllStudents();
		return "studentList.jsp";
	}
	
	public String toStudentModify() {
		student = studentDao.retrieveById(id);
		return "studentModify.jsp";
	}
	public void studentModify() {
		student = new Student(id,name,age,height,weight);
		studentDao.update(student);
		WebUtil.sendMessage("success");
	}
	
	public String toStudentAdd() {
		return "studentAdd.jsp";
	}
	public void studentAdd() {
		student = new Student(id,name,age,height,weight);
		studentDao.add(student);
		WebUtil.sendMessage("success");
	}
	public void studentDelete() {
		studentDao.delete(id);
		WebUtil.sendMessage("success");
	}
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
}
