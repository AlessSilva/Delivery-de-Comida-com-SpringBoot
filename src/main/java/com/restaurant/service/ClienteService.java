package com.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurant.models.Cliente;
import com.restaurant.models.Role;
import com.restaurant.repository.ClienteRepository;
import com.restaurant.repository.EnderecoRepository;
import com.restaurant.repository.RoleRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepo;
	
	@Autowired
	EnderecoRepository enderecoRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	public int[] cadastrarCliente( Cliente cliente ) {
		
		int[] retorno = new int[3];
		
		retorno[0] = 0;
		retorno[1] = 0;//clienteRepo.ValidaCPF(cliente.getCpf());
		retorno[2] = 0;//clienteRepo.ValidaEmail(cliente.getEmail());
		
		if( clienteRepo.findByCpf(cliente.getCpf()) != null ) {
			System.out.println("oi");
			retorno[1] = 1;
		}
		if( clienteRepo.findByEmail(cliente.getEmail()) != null ) {
			retorno[2] = 1;
		}
		
		if( (retorno[1] == 0) && (retorno[2] == 0)) {
		
			retorno[0] = 1;
			
			cliente.setSenha( new BCryptPasswordEncoder().encode( cliente.getSenha() ));
	        
			List<Role> roles = new ArrayList<Role>();
			
			roles.add( roleRepo.findByPapel("ROLE_USER") );
			
			cliente.setRoles(roles);
			
			Cliente c = clienteRepo.save( cliente );
			
			//clienteRepo.definirRoleCliente(c.getId());
			c.getEndereco().setCliente(c);
		
			enderecoRepo.save( c.getEndereco() );
			
			return retorno;
			
		}
		return retorno;		
	}
	
    public void atualizarCliente( Cliente cliente ) {
    	
    	List<Role> roles = new ArrayList<Role>();
		
    	cliente.setRoles(roles);
    	
		roles.add( roleRepo.findByPapel("ROLE_USER") );
		
    	cliente.setSenha( new BCryptPasswordEncoder().encode( cliente.getSenha() ));
		Cliente c = clienteRepo.save( cliente );
		enderecoRepo.save(c.getEndereco());
	}

	public List<Cliente> RetornarTodos() {
		
		return clienteRepo.findAll();
		
	}

	public Cliente buscarCliente(Long id) {
		
		Cliente c = clienteRepo.getOne(id);
		return c;
	}

	public void excluirCliente(Long id) {
		
		clienteRepo.deleteById(id);
		
	}

	public Cliente buscarClientePorEmail(String email) {
				
		return clienteRepo.findByEmail(email);
	}
	
}
