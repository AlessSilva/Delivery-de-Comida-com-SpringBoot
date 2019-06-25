package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.models.Cliente;
import com.restaurant.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	public List<Pedido> findByCliente( Cliente cliente );
	
}
