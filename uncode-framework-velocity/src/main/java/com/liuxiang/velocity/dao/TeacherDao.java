package com.liuxiang.velocity.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.liuxiang.velocity.annotation.Component;
import com.liuxiang.velocity.model.Teacher;

@Component
public class TeacherDao extends BaseDao<Teacher>{
	
	private Map<Integer,Teacher> teachers = new HashMap<Integer,Teacher>();
	private Integer maxId = 0;
	public TeacherDao() {
		Teacher t1 = new Teacher(1, "t1", "T001", "professor", "Math");
		Teacher t2 = new Teacher(2, "t2", "T002", "professor", "Math");
		Teacher t3 = new Teacher(3, "t3", "T003", "professor", "Math");
		Teacher t4 = new Teacher(4, "t4", "T004", "professor", "Math");
		Teacher t5 = new Teacher(5, "t5", "T005", "professor", "Math");
		Teacher t6 = new Teacher(6, "t6", "T006", "professor", "Math");
		Teacher t7 = new Teacher(7, "t7", "T007", "professor", "Math");
		Teacher t8 = new Teacher(8, "t8", "T008", "professor", "Math");
		teachers.put(1, t1);
		teachers.put(2, t2);
		teachers.put(3, t3);
		teachers.put(4, t4);
		teachers.put(5, t5);
		teachers.put(6, t6);
		teachers.put(7, t7);
		teachers.put(8, t8);
		maxId = 8;
	}
	
	public List<Teacher> retrieveAllTeachers() {
		List<Teacher> list = new ArrayList<Teacher>();
		Iterator<Entry<Integer, Teacher>> it = teachers.entrySet().iterator();
		while(it.hasNext()) {
			list.add(it.next().getValue());
		}
		return list;
	}

	public Teacher retrieveById(Integer id) {
		return teachers.get(id);
	}

	public void update(Teacher teacher) {
		teachers.put(teacher.getId(), teacher);
	}

	public void add(Teacher teacher) {
		maxId ++;
		teachers.put(maxId, teacher);
	}

	public void delete(Integer id) {
		teachers.remove(id);
	}
}
