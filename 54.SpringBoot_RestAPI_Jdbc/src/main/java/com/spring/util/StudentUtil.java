package com.spring.util;


import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentUtil {
	private String name;
	private String address;
	private double grade;
	private String collegeName;
	private LocalDate passOut;
	
	
}
