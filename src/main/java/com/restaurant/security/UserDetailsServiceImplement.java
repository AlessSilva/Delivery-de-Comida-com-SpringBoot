package com.restaurant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.restaurant.models.Cliente;
import com.restaurant.repository.ClienteRepository;

@Repository
public class UserDetailsServiceImplement implements UserDetailsService{

	@Autowired
	ClienteRepository clienteRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Cliente cliente = clienteRepo.findByEmail( username );
		if(cliente == null) {
			throw new UsernameNotFoundException("usuário não encontrado");
		}
		return new User(cliente.getUsername(), cliente.getPassword(), true, true, true, true, cliente.getAuthorities());
	
	}

	
}
