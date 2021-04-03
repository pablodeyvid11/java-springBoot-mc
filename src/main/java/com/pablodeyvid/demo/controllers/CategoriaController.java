package com.pablodeyvid.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablodeyvid.demo.domain.DTO.CategoriaDTO;
import com.pablodeyvid.demo.services.CategoriaService;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> getAll(){
		List<CategoriaDTO> lista = service.getAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id) {
		CategoriaDTO cat = service.findById(id);
		return ResponseEntity.ok().body(cat);
	}
}