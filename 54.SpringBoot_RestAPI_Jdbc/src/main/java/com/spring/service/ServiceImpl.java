package com.spring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.StudentDao;
import com.spring.dto.Student;
import com.spring.exceptions.PersonNotFoundException;
import com.spring.util.StudentUtil;

@Service
public class ServiceImpl implements StudentService {
	@Autowired
	public StudentDao dao;

	@Autowired
	public ModelMapper mapper;

	@Override
	public String setData(StudentUtil student) {
		student.getPassOut();
		// converting the studentUtil into studentEntity
		Student obj = new Student(student.getName(), student.getAddress(), student.getGrade(), student.getCollegeName(),
				LocalDate.now());

		Student map = mapper.map(student, Student.class);
		map.setPassOut(LocalDate.now());
		Student save2 = dao.save(map);
		return save2.getCollegeName() + "- data inserted sucessfully....";

	}

	@Override
	public List<Student> gettingAllData() {
		List<Student> findAll = dao.findAll();
		findAll.forEach(std -> System.out.println(std));
		return findAll;
	}

	@Override
	public Student getPersonById(String stusdentName) throws PersonNotFoundException {

		// get the particular Person Details
		Optional<Student> personDetails = dao.findById(stusdentName);

		// utility method to verify data is present or not in optional object
		if (personDetails.isPresent()) {
			Student student = personDetails.get();
			return mapper.map(student, Student.class);
		} else {
			throw new PersonNotFoundException(stusdentName + " is Not Found....");
		}
	}

	@Override
	public String updateData(Student student) throws PersonNotFoundException {
		Optional<Student> findById = dao.findById(student.getName());
		if (findById.isPresent()) {
			Student updatingData = mapper.map(student, Student.class);
			dao.save(updatingData);
			return updatingData.getName() + " updated sucessfully";
		} else {
			throw new PersonNotFoundException("provided name is not available so unable to update the required data");
		}
	}

	@Override
	public String updateSpecifiedField(String stdName, String stdAddress) throws PersonNotFoundException {
		Optional<Student> isExisted = dao.findById(stdName);
		if(isExisted.isPresent()) {
			Student student = isExisted.get();
			student.setAddress(stdAddress);
			Student save = dao.save(student);
			return save.getName()+" is existed and location got updated";
							
		}
		else {
			 throw new PersonNotFoundException(stdName+" not existed");
		}
	}

	@Override
	public String deleteData(String name) throws PersonNotFoundException {
		Optional<Student> findName = dao.findById(name);
		if(findName.isPresent()) {
			Student student = findName.get();
			dao.delete(student);
			return "provided name entity got deleted";
		}else {
			throw new PersonNotFoundException(name+" not there please check your request... ");
		}
		
		
		
	}
}
