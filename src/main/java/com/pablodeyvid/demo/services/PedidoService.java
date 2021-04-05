package com.pablodeyvid.demo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pablodeyvid.demo.domain.Cliente;
import com.pablodeyvid.demo.domain.Pedido;
import com.pablodeyvid.demo.repositories.PedidoRepository;
import com.pablodeyvid.demo.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;

	@Transactional
	public Pedido findById(Integer id) {
		Optional<Pedido> opional = repo.findById(id);

		if (opional.orElse(null) != null) {
			Hibernate.initialize(opional.orElse(null).getCliente().getEnderecos());
			Hibernate.initialize(opional.orElse(null).getCliente().getTelefones());
			Hibernate.initialize(opional.orElse(null).getPagamento());
			Hibernate.initialize(opional.orElse(null).getEnderecoDeEntrega());
			Hibernate.initialize(opional.orElse(null).getItens());
		}

		return opional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public List<Pedido> getAll() {
		List<Pedido> lista = repo.findAll();

		for (Pedido p : lista) {
			Hibernate.initialize(p.getCliente().getEnderecos());
			Hibernate.initialize(p.getCliente().getTelefones());
			Hibernate.initialize(p.getPagamento());
			Hibernate.initialize(p.getEnderecoDeEntrega());
			Hibernate.initialize(p.getItens());
		}

		return lista;
	}
}
