package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.exception.UserException;
import com.assignment.model.Student;
import com.assignment.service.UserService;

@RestController
@RequestMapping("/users")
public class Controller {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/hello")
	public String get() {
		return "Welcome";
	}
	
	@PostMapping("/user")
	public ResponseEntity<Student> create(@RequestBody Student user){
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Student createUser = userService.createUser(user);
		return new ResponseEntity<>(createUser,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) throws UserException{
		userService.delete(id);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Student>> getAll() throws UserException{
		List<Student> users=userService.getAllUser();
		return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/signIn")
	public ResponseEntity<String> signIn(Authentication authentication) throws UserException{
		
		System.out.println(authentication);
	
		Student student=userService.getStudentByEmail(authentication.getName());
		
		return new ResponseEntity<>(student.getName()+" Logged in successfully",HttpStatus.ACCEPTED);
	}
}
