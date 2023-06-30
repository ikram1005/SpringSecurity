package com.assignment.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import com.assignment.model.Student;
import com.assignment.repository.UserRepo;

@Service
public class UserSecurityDetails implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Student> optional=userRepo.findByEmail(username);
		
		if (optional.isPresent()) {
			
			Student student=optional.get();
			
			List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
			
//			return new User(student.getEmail(),student.getPassword(), grantedAuthorities);
			
			return new StudentUserDetails(student);
		}
		else {
			throw new BadCredentialsException("Student not found with this name "+username);
		}
		
	}

}
