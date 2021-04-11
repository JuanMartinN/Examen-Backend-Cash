package com.init.api.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.api.entidades.Loan;
import com.init.api.entidades.User;
import com.init.api.exception.ApiRequestException;
import com.init.api.repository.LoanRepository;
import com.init.api.repository.UserRepository;

@RestController
@RequestMapping("users")
public class UsersController {

	private static Logger log=org.slf4j.LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@GetMapping
	public ResponseEntity< List<User>> getUsers(){
		log.info("LOG-->USER FIND ALL");
		
		List<User> usuarios=userRepository.findAll();
		
		return ResponseEntity.ok(usuarios);
	}
	
	@RequestMapping(value="{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId){

		log.debug("LOG--> USER FIND BY ID");
		
		Optional<User> usuario=userRepository.findById(userId);
		
		if(usuario.isPresent()) {
			
			return ResponseEntity.ok(usuario.get());
			
		}else {
			throw new ApiRequestException("El ID ingresado no existe");
		}
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		log.info("LOG--> USER CREATE");
		
		if(!userRepository.findByEmail(user.getEmail()).isEmpty()) {
			
			throw new ApiRequestException("El Email ingresado ya existe");
		}else {
			
			User newUser= userRepository.save(user);
			
			return ResponseEntity.ok(newUser);	
		}	
	}
	
	@DeleteMapping(value="{userId}")
	public String deleteUser(@PathVariable("userId") Long userId){
		
		log.info("LOG--> USER DELETE");
		
		if(!userRepository.findById(userId).isEmpty()) {
			
			Optional<User> user = userRepository.findById(userId);
			
			if(null!=user.get().getLoans()) {

				List<Loan> loans = user.get().getLoans();
				
				for (Loan loan : loans) {
					loanRepository.deleteById(loan.getId());
				}
			}

			userRepository.deleteById(userId);
		}else {
			throw new ApiRequestException("El ID ingresado no existe");
		}
		
		return "Usuario eliminado exitosamente.";
		
	}
}
