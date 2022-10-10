package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
//		Optional<User> user = userRepository.findById(id);
//		if(user.isPresent()) {
//			return user.get();
//		} else {
//		throw new Throwable("No existe ese usuario");
//		}
		
		return userRepository.findById(id).orElseThrow();
		
	}

	@Override
	public User updateUser(User user, long id) {
		//we need to check whether user with given id is exits in DB or not
		User existUser = userRepository.findById(id).orElseThrow();
		existUser.setNombre(user.getNombre());
		existUser.setCentro(user.getCentro());
		existUser.setIdiomas(user.getIdiomas());
		existUser.setProvincia(user.getProvincia());
		existUser.setTelefono(user.getTelefono());
		userRepository.save(existUser);
		
		return existUser;
	}

	@Override
	public void deleteUser(long id) {
		//We need to check whether user with given id is exits in DB or not
		userRepository.findById(id).orElseThrow();
		userRepository.deleteById(id);
		
	}
	
}
