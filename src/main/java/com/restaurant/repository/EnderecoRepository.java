package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.models.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
}
