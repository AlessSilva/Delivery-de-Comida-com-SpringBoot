package com.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.restaurant.models.Prato;
import com.restaurant.service.PratoService;

@Controller
@RequestMapping("/prato")
public class PratoController {
	
	@Autowired
	PratoService pratoServ;

	@RequestMapping("/cadastro")
	public ModelAndView form() {
		
		ModelAndView mv = new ModelAndView("prato/formularioPrato");
		mv.addObject("prato", new Prato());
		return mv;
		
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar( Prato prato, @RequestParam(value="imagem") MultipartFile imagem ) {
		
		pratoServ.cadastrarPrato(prato, imagem);
		ModelAndView mv = new ModelAndView("redirect:/");
		
		return mv;
		
	}
	
	@RequestMapping("/listar")
	public ModelAndView listar() {
		
		List<Prato> pratos = pratoServ.RetornarTodos();
		ModelAndView mv = new ModelAndView("prato/listagemPrato");
		mv.addObject("pratos", pratos);
		
		return mv;
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizar( @PathVariable Long id ) {
		
		Prato prato = pratoServ.buscarPrato(id);
		ModelAndView mv = new ModelAndView("prato/formularioPrato");
		mv.addObject("prato", prato);
		return mv;
		
	}
	
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluir( @PathVariable Long id ) {
		
		pratoServ.excluirPrato(id);
		ModelAndView mv = new ModelAndView("redirect:/");
	
		return mv;
		
	}
	
}
