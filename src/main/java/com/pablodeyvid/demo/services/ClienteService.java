package com.pablodeyvid.demo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pablodeyvid.demo.domain.Cliente;
import com.pablodeyvid.demo.repositories.ClienteRepository;
import com.pablodeyvid.demo.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;

	@Transactional
	public Cliente findById(Integer id) {
		Optional<Cliente> opional = repo.findById(id);

		if (opional.orElse(null) != null) {
			Hibernate.initialize(opional.orElse(null).getEnderecos());
			Hibernate.initialize(opional.orElse(null).getTelefones());
		}

		return opional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public List<Cliente> getAll() {
		List<Cliente> lista = repo.findAll();
		for (Cliente c : lista) {
			Hibernate.initialize(c.getEnderecos());
			Hibernate.initialize(c.getTelefones());
		}
		return lista;
	}
}
