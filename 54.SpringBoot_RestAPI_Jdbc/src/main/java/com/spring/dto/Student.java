package com.spring.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student__Info")
public class Student {
	@Id
	@Column(name = "std_Name")
	private String name;
	@Column(name = "std_address")
	private String address;
	@Column(name = "std_grade")
	private double grade;
	@Column(name = "college_name")
	private String collegeName;
	@Column(name = "year_of_passout")
	private LocalDate passOut;
	
	public Student() {
		System.out.println("default Constructor");
	}

	public Student(String name, String address, double grade, String collegeName, LocalDate passOut) {
		super();
		this.name = name;
		this.address = address;
		this.grade = grade;
		this.collegeName = collegeName;
		this.passOut = passOut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public LocalDate getPassOut() {
		return passOut;
	}

	public void setPassOut(LocalDate passOut) {
		this.passOut = passOut;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", address=" + address + ", grade=" + grade + ", collegeName=" + collegeName
				+ ", passOut=" + passOut + "]";
	}

}
