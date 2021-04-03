package com.pablodeyvid.demo.domain.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pablodeyvid.demo.domain.Categoria;
import com.pablodeyvid.demo.domain.Produto;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	private List<Produto> produtos = new ArrayList<>();
	
	public CategoriaDTO() {
	}

	public CategoriaDTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public CategoriaDTO(Categoria c) {
		if (c != null) {
			this.id = c.getId();
			this.nome = c.getNome();
			getProdutos().addAll(c.getProdutos());
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaDTO other = (CategoriaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoriaDTO [id=" + id + ", nome=" + nome + "]";
	}
}
