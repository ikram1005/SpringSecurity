package com.assignment.service;

import java.util.List;

import com.assignment.exception.UserException;
import com.assignment.model.Student;

public interface UserService {
	
	Student createUser(Student user);
	
	void delete(Integer id) throws UserException;
	
	List<Student> getAllUser() throws UserException;
	
	Student getStudentByEmail(String email)throws UserException;

}
