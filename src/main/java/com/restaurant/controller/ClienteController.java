package com.restaurant.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.restaurant.models.Cliente;
import com.restaurant.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService clienteServ; 
	
	@RequestMapping("/cadastro")
	public ModelAndView form( Cliente cliente ) {
		
		ModelAndView mv = new ModelAndView("cliente/formularioCliente");
		mv.addObject("cliente", cliente);
		return mv;
		
	}
	 
	@PostMapping("/salvar")
	public ModelAndView salvar( @Validated Cliente cliente,  BindingResult result) {
		
		
		ModelAndView mv = null;
		
		if( result.hasErrors() ) {
			
			return form(cliente);
			
		}
		
		if( cliente.getEndereco().getRua().isBlank() ) {
			
			mv = new ModelAndView("cliente/formularioCliente");
			mv.addObject("mensagemRuaV", "Preencha o campo 'Rua'.");
			return mv;
		
		}
		
        if( cliente.getEndereco().getBairro().isBlank() ) {
			
			mv = new ModelAndView("cliente/formularioCliente");
			mv.addObject("mensagemBairroV", "Preencha o campo 'Bairro'.");
			return mv;
		
		}
        
        if( cliente.getEndereco().getNumero().isBlank() ) {
			
			mv = new ModelAndView("cliente/formularioCliente");
			mv.addObject("mensagemNumeroV", "Preencha o campo 'Numero'.");
			return mv;
		
		}
		
        if( cliente.getEndereco().getCidade().isBlank() ) {
			
			mv = new ModelAndView("cliente/formularioCliente");
			mv.addObject("mensagemCidadeV", "Preencha o campo 'Cidade'.");
			return mv;
		
		}
        
        if( cliente.getEndereco().getEstado().isBlank() ) {
			
			mv = new ModelAndView("cliente/formularioCliente");
			mv.addObject("mensagemEstadoV", "Preencha o campo 'Estado'.");
			return mv;
		
		}
		
		if( cliente.getId() != null ) {
			
			cliente.getEndereco().setCliente(cliente);
			clienteServ.atualizarCliente(cliente);
			mv = new ModelAndView("home");
			mv.addObject("mensagemSucesso", "Cliente Atualizado com sucesso.");
			
		}else {
		
			int[] mensagem = new int[3];
			mensagem = clienteServ.cadastrarCliente(cliente);
			
			if( mensagem[0] == 1 ) {
				mv = new ModelAndView("home");
				mv.addObject("mensagemSucesso", "Cliente Cadastrado com sucesso.");
				
			}
			
			if( mensagem[1] != 0 ) {
				mv = new ModelAndView("cliente/formularioCliente");
				mv.addObject("mensagemCpfV", "CPF já Cadastrado. Informe um CPF válido");
				
			}
			
	        if( mensagem[2] != 0 ) {
	        	mv = new ModelAndView("cliente/formularioCliente");
				mv.addObject("mensagemEmailV", "Email já Cadastrado. Informe um Email válido");
				
			}
			
		}
		
		return mv;
		
	}
	
	@RequestMapping("/listar")
	public ModelAndView listar() {
		
		List<Cliente> clientes = clienteServ.RetornarTodos();
		ModelAndView mv = new ModelAndView("cliente/listagemCliente");
		mv.addObject("clientes", clientes);
		
		return mv;
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizar( @PathVariable Long id ) {
		
		Cliente cliente = clienteServ.buscarCliente(id);
		ModelAndView mv = new ModelAndView("cliente/formularioCliente");
		mv.addObject("cliente", cliente);
		return mv;
		
	}
	
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluir( @PathVariable Long id ) {
		
		clienteServ.excluirCliente(id);
		ModelAndView mv = new ModelAndView("redirect:/logout");
	
		return mv;
		
	}
	
	@RequestMapping("/perfil")
	public ModelAndView perfil() {
		
		ModelAndView mv = new ModelAndView("cliente/perfilCliente");
		
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String email = user.getUsername();
		
		mv.addObject("cliente", clienteServ.buscarClientePorEmail(email));
		
		return mv;
	}
	
	
}