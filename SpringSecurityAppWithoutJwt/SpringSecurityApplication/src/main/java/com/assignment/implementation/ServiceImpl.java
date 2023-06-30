package com.assignment.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.exception.UserException;
import com.assignment.model.Student;
import com.assignment.repository.UserRepo;
import com.assignment.service.UserService;

@Service
public class ServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public Student createUser(Student user) {
		return userRepo.save(user);
	}

	@Override
	public void delete(Integer id) throws UserException {
		Student user = userRepo.findById(id).orElseThrow(()->new UserException("id not found"+id));
		userRepo.delete(user);
	}

	@Override
	public List<Student> getAllUser() throws UserException {
		List<Student> users=userRepo.findAll();
		
		if(!users.isEmpty())return users;
		else throw new UserException("Not found users");
	}

	@Override
	public Student getStudentByEmail(String email) throws UserException {
		return userRepo.findByEmail(email).orElseThrow(()->new UserException("email not found "+email));
	}

}
