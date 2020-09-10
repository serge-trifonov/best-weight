package com.best.weight.desk.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.best.weight.desk.entities.BestWeightProject;
import com.best.weight.desk.entities.User;
import com.best.weight.desk.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Map <String, Object> create(User user) {
		Map <String, Object> model = new HashMap<>();
		System.out.println("height "+user.getHeight());
		boolean nameexist = userRepository.findByUsername(user.getUsername())!=null;
		boolean emailexist = userRepository.findByEmail(user.getEmail())!=null;
		model.put("nameexist", nameexist);
		model.put("emailexist", emailexist);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		model.put("user", nameexist||emailexist? null:userRepository.save(user));
		return model;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
		

        return user;
    }

	public User updateUser(User user) {
		return userRepository.save(user);
	}

}
