package com.management.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.library.entities.IssuedBook;
import com.management.library.entities.User;
import com.management.library.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<IssuedBook> findBooksIssued(int id){
		
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			User opUser = user.get();
		List<IssuedBook> issuedBooks = opUser.getBooksIssued();
		return issuedBooks;
		}
		
		return null;
		
	}
	
	public User findUserById(int id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) 
			return user.get();
			
		return null;
	}
	
}
