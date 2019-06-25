package com.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.restaurant.models.Prato;
import com.restaurant.repository.PratoRepository;
import com.restaurant.util.FileUtil;

@Service
public class PratoService {

	@Autowired
	PratoRepository pratoRepo;

	public void cadastrarPrato(Prato prato, MultipartFile imagem) {
		
		pratoRepo.save(prato);
		String path = "images/pratos/"+prato.getId()+ ".png";
		FileUtil.salvarImagem( path, imagem );
		
		
	}

	public List<Prato> RetornarTodos() {
		
		return pratoRepo.findAll();

	}

	public Prato buscarPrato(Long id) {
		
		return pratoRepo.getOne(id);
	}

	public void excluirPrato(Long id) {
		
		pratoRepo.deleteById(id);
		
	}
	
}
