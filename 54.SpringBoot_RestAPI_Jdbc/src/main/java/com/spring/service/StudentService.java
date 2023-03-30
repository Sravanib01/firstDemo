package com.spring.service;

import java.util.List;

import com.spring.dto.Student;
import com.spring.exceptions.PersonNotFoundException;
import com.spring.util.StudentUtil;

public interface StudentService {
	public String setData(StudentUtil student);

	public List<Student> gettingAllData();

	public Student getPersonById(String stusdentName) throws PersonNotFoundException;

	public String updateData(Student student) throws PersonNotFoundException;

	public String updateSpecifiedField(String stdName, String stdAddress) throws PersonNotFoundException;
	
	public String deleteData(String name) throws PersonNotFoundException;
	
	

}
