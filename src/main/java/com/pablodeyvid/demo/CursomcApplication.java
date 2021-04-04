package com.pablodeyvid.demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pablodeyvid.demo.domain.Categoria;
import com.pablodeyvid.demo.domain.Cidade;
import com.pablodeyvid.demo.domain.Cliente;
import com.pablodeyvid.demo.domain.Endereco;
import com.pablodeyvid.demo.domain.Estado;
import com.pablodeyvid.demo.domain.Pagamento;
import com.pablodeyvid.demo.domain.PagamentoComBoleto;
import com.pablodeyvid.demo.domain.PagamentoComCartao;
import com.pablodeyvid.demo.domain.Pedido;
import com.pablodeyvid.demo.domain.Produto;
import com.pablodeyvid.demo.domain.enums.EstadoPagamento;
import com.pablodeyvid.demo.domain.enums.TipoCliente;
import com.pablodeyvid.demo.repositories.CategoriaRepository;
import com.pablodeyvid.demo.repositories.CidadeRepository;
import com.pablodeyvid.demo.repositories.ClienteRepository;
import com.pablodeyvid.demo.repositories.EnderecoRepository;
import com.pablodeyvid.demo.repositories.EstadoRepository;
import com.pablodeyvid.demo.repositories.PagamentoRepository;
import com.pablodeyvid.demo.repositories.PedidoRepository;
import com.pablodeyvid.demo.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepo;

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private EstadoRepository estadoRepo;

	@Autowired
	private CidadeRepository cidadeRepo;

	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;

	@Autowired
	private PagamentoRepository pagamentoRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf2.parse("20/10/2017"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1, pagto2));
	}
}
