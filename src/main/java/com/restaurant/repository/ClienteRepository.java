package com.restaurant.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restaurant.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Cliente findByEmail( String email );
	
	Cliente findByCpf( String cpf );
	
	/*@Query(value = "SELECT count(*) FROM cliente WHERE cpf = :cpfV ", nativeQuery = true)
	public int ValidaCPF( @Param("cpfV") String cpfV);
	
	@Query(value = "SELECT count(*) FROM cliente WHERE email = :emailV ", nativeQuery = true)
	public int ValidaEmail( @Param("emailV") String email);
	
	@Modifying
	@Transactional
	@Query(value = "insert into usuario_roles (usuario_id,role_id) VALUES (:id,'ROLE_USER')", nativeQuery = true)
	void definirRoleCliente( @Param("id") Long id );
	 */
}
