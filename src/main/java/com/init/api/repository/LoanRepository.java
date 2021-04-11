package com.init.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.init.api.entidades.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{	
	
	Page<Loan> findByUserId(Optional<Long> userid,Pageable pageable);
}
