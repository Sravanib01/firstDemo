package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.Student;
import com.spring.exceptions.PersonNotFoundException;
import com.spring.service.StudentService;
import com.spring.util.StudentUtil;

@RestController
@RequestMapping("/data")
public class StudentController {

	@Autowired
	public StudentService service;

	@PostMapping(value = "/studentInfo", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> gettingStudentData(@RequestBody StudentUtil student) {

		try {
			String setData = service.setData(student);
			return new ResponseEntity<String>(setData, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("getting the problem with student info",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/GettinAllData", produces = { "application/json" })
	public ResponseEntity<?> displayAllPersons() {
		try {
			List<Student> allPersonsInfo = service.gettingAllData();
			return new ResponseEntity<>(allPersonsInfo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Problem while rendering Information Information",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getDataByName/{studentName}", produces = "application/json")
	public ResponseEntity<?> getDataById(@PathVariable(name = "studentName") String studentName)
			throws PersonNotFoundException {
		try {
			Student personById = service.getPersonById(studentName);
			return new ResponseEntity<>(personById, HttpStatus.OK);
		} catch (PersonNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * @PutMapping(value = "/updatingTHeData", consumes = { "application/json" },
	 * produces = "application/json") public String updatingTheDataa(@RequestBody
	 * Student student) throws PersonNotFoundException { try { String updateData =
	 * service.updateData(student); return updateData; } catch
	 * (PersonNotFoundException e) { return e.getMessage(); } }
	 */

	@PutMapping(value = "/updatingTHeData", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> updatingTheData(@RequestBody Student student) throws PersonNotFoundException {
		try {
			String updateData = service.updateData(student);
			return new ResponseEntity<String>(updateData, HttpStatus.OK);
		} catch (PersonNotFoundException msg) {
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping(value = "/partialUpdating/{stdName}/{stdAddress}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> partialUpdation(@PathVariable(name = "stdName") String stdName,
			@PathVariable(name = "stdAddress") String stdAddress) throws PersonNotFoundException {
		try {
			String updateSpecifiedField = service.updateSpecifiedField(stdName, stdAddress);
			// here geniric type is optional...
			return new ResponseEntity<String>(updateSpecifiedField, HttpStatus.OK);

		} catch (PersonNotFoundException e) {
			// here geniric type is optional...
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/deletingEntity/{name}")
	public ResponseEntity<String> deletingEntityByName(@PathVariable(name = "name") String name) {
		try {
			String deleteData = service.deleteData(name);
			// here geniric type is optional...
			return new ResponseEntity<String>(deleteData, HttpStatus.OK);

		} catch (PersonNotFoundException e) {
			// here geniric type is optional...
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
