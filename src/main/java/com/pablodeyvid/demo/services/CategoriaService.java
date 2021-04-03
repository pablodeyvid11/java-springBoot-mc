package com.pablodeyvid.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public List<CategoriaDTO> getAll() {
		List<Categoria> lista = repo.findAll();
		return lista.stream().map((categoria) -> new CategoriaDTO(categoria)).collect(Collectors.toList());
	}
}
