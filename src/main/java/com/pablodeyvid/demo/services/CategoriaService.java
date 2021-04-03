package com.pablodeyvid.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pablodeyvid.demo.domain.Categoria;
import com.pablodeyvid.demo.domain.DTO.CategoriaDTO;
import com.pablodeyvid.demo.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public CategoriaDTO findById(Integer id) {
		Optional<Categoria> opional = repo.findById(id);
		return new CategoriaDTO(opional.orElse(null));
	}
}
