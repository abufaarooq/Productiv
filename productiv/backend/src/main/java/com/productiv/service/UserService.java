package com.productiv.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.productiv.model.User;
import com.productiv.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException(userName + ", user not found!");
		} else {

			return new User(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(),
					user.getPassWord(), user.getemail(), user.getRole(), user.getLocked(), user.getEnabled());
//    				userRepository.findByUserName(userName);
		}
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	public String deleteId(Long id) {
		userRepository.deleteById(id);
		return "User number, " + id + " has been deleted!";
	}

	public User update(User user) {
		return userRepository.save(user);
	}
}