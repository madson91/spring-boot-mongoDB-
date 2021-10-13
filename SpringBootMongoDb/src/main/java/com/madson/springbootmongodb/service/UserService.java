package com.madson.springbootmongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madson.springbootmongodb.domain.User;
import com.madson.springbootmongodb.dto.UserDTO;
import com.madson.springbootmongodb.repository.UserRepository;
import com.madson.springbootmongodb.service.exception.copy.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findById(String id) {

		Optional<User> user = userRepo.findById(id);
		if (user.isEmpty())
			throw new ObjectNotFoundException("Usuario não encontrado");
		return user.get();
	}

	public User insert(User obj) {
		return userRepo.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		userRepo.deleteById(id);
	}

	public User update(User obj) {
		User newUser = findById(obj.getId());
		updateData(newUser, obj);
		return userRepo.save(newUser);
	}

	private void updateData(User newUser, User obj) {
		newUser.setName(obj.getName());
		newUser.setEmail(obj.getEmail());
	}

	public User fromUserDto(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}

}
