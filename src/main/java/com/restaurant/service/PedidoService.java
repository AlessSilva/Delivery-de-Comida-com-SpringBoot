package com.restaurant.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.restaurant.models.Endereco;
import com.restaurant.models.Item;
import com.restaurant.models.Pedido;
import com.restaurant.repository.ClienteRepository;
import com.restaurant.repository.EnderecoRepository;
import com.restaurant.repository.PedidoRepository;
import com.restaurant.util.Carrinho;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepo;
	
	@Autowired
	ClienteRepository clienteRepo;
	
	@Autowired
	ItemService itemServ;
	@Autowired
	EnderecoRepository enderecoRepo;
	
	public void adicionarPedido( HttpSession session, String pagamento, Endereco end ) {
		
		Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
		
		Date data = new Date(System.currentTimeMillis());  
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy"); 
		formatarDate.format(data);
		
		end.setCliente(null);
		enderecoRepo.save(end);
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String email = user.getUsername();
		
		
		
		Pedido p = new Pedido( clienteRepo.findByEmail(email), data, carrinho.precoTotal(), pagamento, end);
		
		p = pedidoRepo.save(p);
		
		for( Item i : carrinho.getItens() ) {
			
			i.setPedido(p);
			itemServ.salvarItem(i);
			
		}
		
		session.removeAttribute("carrinho");
	}

	public List<Pedido> buscarPedidos() {
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String email = user.getUsername();
		
		return pedidoRepo.findByCliente( clienteRepo.findByEmail(email) );
		
	}
	
    public List<Pedido> buscarTodosPedidos() {
		
		
		return pedidoRepo.findAll();
		
	}

	public Pedido buscarPedido(Long id) {
		
		return pedidoRepo.getOne(id);
	
	}
	
}
