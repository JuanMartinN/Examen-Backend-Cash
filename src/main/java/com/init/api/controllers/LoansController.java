package com.init.api.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.api.entidades.Loan;
import com.init.api.exception.ApiRequestException;
import com.init.api.repository.LoanRepository;
import com.init.api.repository.UserRepository;

@RestController
@RequestMapping("loans")
public class LoansController {

	private static Logger log=org.slf4j.LoggerFactory.getLogger(LoansController.class);
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public Page<Loan> findAll(Integer page,Integer size,Optional<Long> user_id){
		
		if(user_id.isPresent()) {
			log.info("LOG-->LOANS FIND ALL BY USER:"+ user_id);
			
			if(!userRepository.findById(user_id.orElse(null)).isEmpty()) {
				
				return loanRepository.findByUserId(user_id,PageRequest.of(page,size));
			}else {
				throw new ApiRequestException("El User ID no existe");
			}
		}else {
			log.info("LOG-->LOANS FIND ALL");
			
			return loanRepository.findAll(PageRequest.of(page,size));
		}
		
	}
}
