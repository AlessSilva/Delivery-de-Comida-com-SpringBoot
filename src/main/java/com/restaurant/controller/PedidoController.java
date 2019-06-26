package com.restaurant.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.restaurant.models.Endereco;
import com.restaurant.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoServ;
	
	@RequestMapping("/confirmar")
	public ModelAndView confirmar( String pagamento , @Validated Endereco end, BindingResult result, HttpSession session ) {
		
		ModelAndView mv;
		
		if( result.hasErrors() ) {
			System.out.println("aqui");
			mv = new ModelAndView( "listagemCarrinho" );
			mv.addObject("endereco", end);
			return mv;
		}
		
		pedidoServ.adicionarPedido(session,pagamento,end);
		
		mv = new ModelAndView("home");
		
		return mv;
	}
	
	@RequestMapping("/listar")
	public ModelAndView listar() {
		
		ModelAndView mv = new ModelAndView("cliente/listagemPedido");
		
		mv.addObject("pedidos", pedidoServ.buscarPedidos());
		
		return mv;
	}
	
	@RequestMapping("/listarTodos")
	public ModelAndView listarTodos() {
		
		ModelAndView mv = new ModelAndView("cliente/listagemPedido");
		
		mv.addObject("pedidos", pedidoServ.buscarTodosPedidos());
		
		return mv;
	}
	
	@RequestMapping("/recibo/{id}")
	public ModelAndView recibo( @PathVariable Long id ) {
		
		ModelAndView mv = new ModelAndView("cliente/recibo");
		mv.addObject("pedido", pedidoServ.buscarPedido( id ));
		return mv;
		
	}
	
}
