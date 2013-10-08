package br.ufpb.aps.teste.pgto;

import static org.junit.Assert.*;


import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufpb.aps.locar.Cliente;
import br.ufpb.aps.locar.LocadoraDeVeiculos;
import br.ufpb.aps.locar.Operador;
import br.ufpb.aps.locar.Veiculo;
import br.ufpb.aps.locar.VeiculoException;
import br.ufpb.aps.locar.VeiculoPasseio;
import br.ufpb.aps.locar.VeiculoRuntimeException;
import br.ufpb.aps.locar.VeiculoUtilitario;
import br.ufpb.aps.pgto.PagamentoDinheiro;
import br.ufpb.aps.pgto.PagamentoException;
import br.ufpb.aps.pgto.PagamentoFactory;
import br.ufpb.aps.pgto.PagamentoType;
import br.ufpb.aps.pgto.PagamentoPontos;
import br.ufpb.aps.pgto.io.PagamentoIO;

public class PagamentoTest {

	LocadoraDeVeiculos locadora;
	PagamentoFactory pagamentoFactory;
	Operador operador1;
	Operador operador2;
	Veiculo veiculoPasseio;
	Veiculo veiculoUtil;
	Cliente cliente1;
	Cliente cliente2;
	
	
	@Before
	public void setUp(){
		
		pagamentoFactory = PagamentoFactory.getInstance();
		locadora = new LocadoraDeVeiculos();
		operador1 = new Operador("Joao", "12345", "loc123", "vendas", new Date(), "op1-locadora");
		operador2 = new Operador("Maria", "56789", "loc456", "vendas", new Date(), "op2-locadora");
		veiculoPasseio = new VeiculoPasseio("Gol", "111222333", "abc1234");
		veiculoUtil = new VeiculoUtilitario("Elantra", "777888999", "cde5678");
		cliente1 = new Cliente("Obama", "12345");
		cliente2 = new Cliente("Ozama", "67890");
		
		/* Cadastramentos */
		locadora.addCliente(cliente1);
		locadora.addCliente(cliente2);
		locadora.cadastrarVeiculo(veiculoPasseio);
		locadora.cadastrarVeiculo(veiculoUtil);
		
	}
	
	@After
	public void tearDown(){
		locadora = null;
		operador1 = null;
		operador2 = null;
		veiculoPasseio = null;
		veiculoUtil = null;
	}
	
	@Test
	public void testPagamento(){
		try {
			locadora.locarVeiculo(veiculoPasseio, cliente1);
			assertEquals(1, locadora.getVeiculosLocados().size());
			locadora.locarVeiculo(veiculoUtil, cliente2);
			cliente2.setPontos(50); // o cliente ganhou 50 pontos pela locação
			assertEquals(2, locadora.getVeiculosLocados().size());
			
			/* realiza o pagamento em dinheiro da locação para o veículoPasseio */
			PagamentoDinheiro pgto_dinheiro = (PagamentoDinheiro) 
					pagamentoFactory.getPagamento(PagamentoType.DINHEIRO);
			pgto_dinheiro.setValor(125.50f); /* o cálculo do valor fica por conta da regra de negócio */
			pgto_dinheiro.setCliente(cliente1);
			pgto_dinheiro.setVeiculo(veiculoPasseio);
			pgto_dinheiro.setData(new Date());
			pgto_dinheiro.setOperador(operador1);
			locadora.efetuarPagamento(pgto_dinheiro);
			assertEquals(1, locadora.getPagamentos().size());
			
			assertEquals(1, locadora.getVeiculosLocados().size());			
			
			/* realiza o pagamento em pontos da locação para o veículoUtil */
			PagamentoPontos pgto_pontos = (PagamentoPontos)
					pagamentoFactory.getPagamento(PagamentoType.PONTOS);
			pgto_pontos.setCliente(cliente2);
			pgto_pontos.setNumeroPontos(cliente2.getPontos());
			pgto_pontos.setVeiculo(veiculoUtil);
			pgto_pontos.setData(new Date());
			pgto_pontos.setOperador(operador2);
			locadora.efetuarPagamento(pgto_pontos);
			assertEquals(2, locadora.getPagamentos().size());
			
			/* esse teste deve lançar um VeiculoRuntimeException, pois já não existem veículos locados */
			assertEquals(0, locadora.getVeiculosLocados().size());
			
		} catch (VeiculoException e) {
			
			fail("[VeiculoException]: NÃO DEVERIA LANÇAR ESTA EXCEÇÃO");
			
		} catch (PagamentoException e) {
			
			fail("[PagamentoException]: NÃO DEVERIA LANÇAR ESTA EXCEÇÃO");
			
		} catch (VeiculoRuntimeException err) {
			
			assertEquals("Não existem veículos locados no momento!", err.getMessage());
		}
		
		PagamentoDinheiro pgto_dinheiro = (PagamentoDinheiro) 
				pagamentoFactory.getPagamento(PagamentoType.DINHEIRO);		
		try {
			
			locadora.locarVeiculo(veiculoPasseio, cliente1);
			assertEquals(1, locadora.getVeiculosLocados().size());		
			
			pgto_dinheiro.setValor(200.00f);
			/* Não foi esse cliente que fez a locação */
			pgto_dinheiro.setCliente(cliente2); 
			pgto_dinheiro.setVeiculo(veiculoPasseio);
			pgto_dinheiro.setData(new Date());
			pgto_dinheiro.setOperador(operador1);
			locadora.efetuarPagamento(pgto_dinheiro);
			fail("DEVERIA LANÇAR UMA EXCEÇÃO");	
			
		} catch (PagamentoException ex) {
			
			assertEquals("Erro no pagamento. Verifique o status do" +
					" cliente e do veículo e tente novamente!", ex.getMessage());	
			
		} catch (VeiculoException ex) {
			fail("NÃO DEVERIA LANÇAR ESTA EXCEÇÃO");
		}
		
		/* realizar um pagamento duas vezes */
		try {
			
			pgto_dinheiro.setCliente(cliente1);
			locadora.efetuarPagamento(pgto_dinheiro);
			assertEquals(3, locadora.getPagamentos().size());
			locadora.efetuarPagamento(pgto_dinheiro);		
			fail("DEVERIA LANÇAR UMA EXCEÇÃO");
			
		} catch (PagamentoException ex) {
			/* como não existe veículo locado, é lançada uma exceção */
			assertEquals("Não existem veículos locados no momento!", ex.getMessage());
		}
		
	}
	
	@Test
	public void testPagamentosIO() {
		try {			
			locadora.locarVeiculo(veiculoPasseio, cliente2);
			assertEquals(1, locadora.getVeiculosLocados().size());
			
			PagamentoDinheiro pgto_dinheiro = (PagamentoDinheiro)
					pagamentoFactory.getPagamento(PagamentoType.DINHEIRO);
			pgto_dinheiro.setValor(200.00f);
			pgto_dinheiro.setCliente(cliente2);
			pgto_dinheiro.setVeiculo(veiculoPasseio);
			pgto_dinheiro.setData(new Date());
			pgto_dinheiro.setOperador(operador2);
			locadora.efetuarPagamento(pgto_dinheiro);
			assertEquals(1, locadora.getPagamentos().size());
			
			locadora.gravarPagamentosNoArquivo();
			locadora.removerTodosOsPagamentos();
			assertEquals(0, locadora.getPagamentos().size());
			
			assertTrue(locadora.getFilePagamentos().exists());
			assertTrue(locadora.getFilePagamentos().length() > 0);	
			
			locadora.recuperarPagamentosDoArquivo();
			assertEquals(0, locadora.getPagamentos().size());
			fail("DEVERIA LANÇAR UMA EXCEÇÃO");
			
		} catch (VeiculoException e) {
			
			fail("[VeiculoException]: NÃO DEVERIA LANÇAR ESTA EXCEÇÃO" + e.getMessage());	
			
		} catch (PagamentoException e) {
			
			fail("[PagamentoException]: NÃO DEVERIA LANÇAR ESTA EXCEÇÃO" + e.getMessage());		
			
		} catch (AssertionError err) {
			
			assertEquals(1, locadora.getPagamentos().size());
			
		}
	}
	
}