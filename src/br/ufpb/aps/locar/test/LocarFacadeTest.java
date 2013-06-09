package br.ufpb.aps.locar.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.ufpb.aps.locar.Cliente;
import br.ufpb.aps.locar.LocadoraDeVeiculos;
import br.ufpb.aps.locar.LocarFacade;
import br.ufpb.aps.locar.VeiculoPasseio;
import br.ufpb.aps.locar.Veiculos;

public class LocarFacadeTest {
	Cliente cliente;
	Cliente cliente2;
	Cliente cliente3;
	Veiculos veiculo;
	Veiculos veiculo2;
	Veiculos veiculo3;
	LocadoraDeVeiculos locadora;
	LocarFacade fachada;
	
	
	
	@Before
	public void iniciar(){
		cliente = new Cliente();
		cliente2 = new Cliente();
		cliente3 = new Cliente();
		locadora = new LocadoraDeVeiculos();
		fachada = new LocarFacade();
	}
	

	@Test
	public void testCriarCliente() {
		
	}

	@Test
	public void testCriarVeiculoPasseio() {
		veiculo = fachada.criarVeiculoPasseio("fiat", "uno", "MNO1212");
		Assert.assertEquals(veiculo, locadora.addVeiculos(veiculo));
		
	}

	@Test
	public void testCriarVeiculoUtilitario() {
		veiculo = fachada.criarVeiculoUtilitario("ford", "fiesta", "KLA1234");
		Assert.assertEquals(veiculo, locadora.addVeiculos(veiculo));
		
	}

	

	@Test
	public void testCriaVeiculos() {
		veiculo = fachada.criarVeiculoPasseio("WV", "Gol", "WER6787");
		veiculo2 = fachada.criarVeiculoUtilitario("Fiat", "estrada", "HGU3243");
		Assert.assertEquals(veiculo, locadora.addVeiculos(veiculo));
		Assert.assertEquals(veiculo2, locadora.addVeiculos(veiculo2));
		
	}

	@Test
	public void testQuantidadeDeCliente() {
		
		locadora.addCliente(cliente);
		locadora.addCliente(cliente2);
		locadora.addCliente(cliente3);
		Assert.assertEquals(3, locadora.quantidadeDeCliente());
		
		
		
	}

	@Test
	public void testQuantidadeDeVeiculos() {
		locadora.addVeiculos(veiculo);
		locadora.addVeiculos(veiculo2);
		Assert.assertEquals(2, locadora.quantidadeDeVeiculos());
		
	}

	@Test
	public void testClienteEstaVazio() {
		locadora.addCliente(cliente);
		Assert.assertEquals(true, fachada.clienteEstaVazio());
	
	}

	@Test
	public void testVeiculoEstaVazio() {
		
	}

	@Test
	public void testRemoverVeiculo() {
		fachada.criarVeiculoPasseio("Ford", "Fiesta", "MNO7635");
		fachada.removerVeiculo("MNO7635");
		Assert.assertEquals(true, fachada.veiculoEstaVazio());
		
	}

	@Test
	public void testVerificarSeListaDeVeiculoNaoEstaVazia() {
		fachada.criarVeiculoPasseio("Fiat", "Uno", "MNO7635");
		fachada.criarVeiculoUtilitario("wv", "Amarok", "IHJ0098");
		Assert.assertEquals(false, fachada.veiculoEstaVazio());
		Assert.assertEquals(2, fachada.quantidadeDeVeiculos());
			
	}

	@Test
	public void testListarCliente() {
		
	}

	@Test
	public void testListarVeiculos() {
		
	}

}
