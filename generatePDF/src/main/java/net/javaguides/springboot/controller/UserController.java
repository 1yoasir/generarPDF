package net.javaguides.springboot.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.service.impl.PdfService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PdfService pdfService;
	
	//build create REST API
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}
	
	//Build getAll REST API
	@GetMapping
	public List<User> getAllUser(){
		return userService.getAllUsers();
	}
	
	//Build get user by id REST API
	//http://localhost:8080/api/users/1
	@GetMapping("{id}")
	public ResponseEntity<User> getuserById(@PathVariable("id") long userId){
		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	//Build update user REST API
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") long userId){
		return new ResponseEntity<User>(userService.updateUser(user, userId), HttpStatus.OK);
		
	}
	
	//Build delete user REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") long userId){
		userService.deleteUser(userId);
		return new ResponseEntity<String>("User deleted successfully!", HttpStatus.OK);
	}
	
	//Build generate PDf userDAta REST API
	@GetMapping("{id}/pdf")
	public void downloadPdf(HttpServletResponse response, @PathVariable("id") long userId) {
		try {
			Path file = Paths.get(pdfService.generateUsuarioPerfilPdf(userId).getAbsolutePath());
			if(Files.exists(file)){
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename" + file.getFileName());
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			} 
		} catch (IOException e) {
				e.printStackTrace();
		}
			
	}
}
