package com.koreait.Springtest4.student.domain.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

	private static final Map<Long, Student> school = new HashMap<>();
	private static long seq = 0L;
	
	// 학생 등록
	public Student save(Student student) {
		student.setId(++seq);
		school.put(student.getId(), student);
		return student;
	}
	
	// 찾기
	public Student findById(Long id) {
		return school.get(id);
	}
	
	// 전체 검색
	public List<Student> findAll(){
		return new ArrayList<Student>(school.values());
	}
	
	// 수정
	public void update( Long id, Student updateParam) {
		Student findStudent = findById(id);
		findStudent.setStudentName(updateParam.getStudentName());
		findStudent.setAge(updateParam.getAge());
		findStudent.setPhone(updateParam.getPhone());
		findStudent.setSubject(updateParam.getSubject());
		findStudent.setAddr(updateParam.getAddr());
	}
	
}
