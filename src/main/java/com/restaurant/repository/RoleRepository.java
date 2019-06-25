package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.models.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	Role findByPapel( String papel );
	
}
