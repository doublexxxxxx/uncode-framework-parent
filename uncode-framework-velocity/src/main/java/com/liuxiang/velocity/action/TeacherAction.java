package com.liuxiang.velocity.action;

import java.util.List;

import com.liuxiang.velocity.annotation.Action;
import com.liuxiang.velocity.annotation.Autowired;
import com.liuxiang.velocity.dao.TeacherDao;
import com.liuxiang.velocity.model.Teacher;
import com.liuxiang.velocity.util.WebUtil;

@Action("teacherAction")
public class TeacherAction extends BaseAction{
	@Autowired
	public TeacherDao teacherDao;
	private List<Teacher> teachers;
	private Teacher teacher;
	private Integer id;
	private String name;
	private String serializeNo;
	private String titile;
	private String subject;
	public String teacherList() {
		teachers = teacherDao.retrieveAllTeachers();
		return "teacherList.jsp";
	}
	
	public String toTeacherModify() {
		teacher = teacherDao.retrieveById(id);
		return "teacherModify.jsp";
	}
	public void teacherModify() {
		teacher = new Teacher(id,name,serializeNo,titile,subject);
		teacherDao.update(teacher);
		WebUtil.sendMessage("success");
	}
	
	public String toTeacherAdd() {
		return "teacherAdd.jsp";
	}
	public void teacherAdd() {
		teacher = new Teacher(id,name,serializeNo,titile,subject);
		teacherDao.add(teacher);
		WebUtil.sendMessage("success");
	}
	public void teacherDelete() {
		teacherDao.delete(id);
		WebUtil.sendMessage("success");
	}
	
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getSerializeNo() {
		return serializeNo;
	}

	public void setSerializeNo(String serializeNo) {
		this.serializeNo = serializeNo;
	}
	public String getTitile() {
		return titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
