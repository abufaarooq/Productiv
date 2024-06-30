package com.productiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.productiv.model.User;
import com.productiv.repository.UserRepository;

@Service
public class RegistrationService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {
		user.setPassWord(new BCryptPasswordEncoder().encode(user.getPassword())); // This statement encodes the password
																					// using BCrypt.
		return userRepository.save(user);
	}

	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "User, " + userRepository.findById(id) + "has been deleted.";
	}
}