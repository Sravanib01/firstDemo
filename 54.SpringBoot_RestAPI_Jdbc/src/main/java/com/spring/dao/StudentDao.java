package com.spring.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.dto.Student;
import com.spring.util.StudentUtil;

@Repository
public interface StudentDao extends JpaRepository<Student, Serializable> {

}
