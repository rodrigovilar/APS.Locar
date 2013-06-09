package br.ufpb.aps.locar.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.ufpb.aps.locar.Cliente;
import br.ufpb.aps.locar.LocadoraDeVeiculos;
import br.ufpb.aps.locar.LocarFacade;

public class LocadoraDeVeiculosTest {
	
	LocadoraDeVeiculos locadora;
	Cliente cliente;
	Cliente cliente2;
	Cliente cliente3;
	LocarFacade fachada;
	
	@Before
	public void iniciar(){
		locadora = new LocadoraDeVeiculos();
		
		fachada = new LocarFacade();
	}
	@Test
	public void testLocadoraDeVeiculos() {
	
	}

	@Test
	public void testAddCliente() {
		cliente = fachada.criarCliente("wellyson", 123, "valentina");
		cliente2 = fachada.criarCliente("neemias", 456, "bancarios");
		cliente3 = fachada.criarCliente("jessyca", 789, "joao pessoa");
		
		Assert.assertEquals(cliente, locadora.addCliente(cliente));	
		Assert.assertEquals(cliente2, locadora.addCliente(cliente2));
		Assert.assertEquals(cliente3, locadora.addCliente(cliente3));
	}

	@Test
	public void testAddVeiculos() {
		
	}

	@Test
	public void testRemoverClienteStringInt() {
		
	}

	@Test
	public void testRemoverVeiculos() {
		
	}

	@Test
	public void testClienteEstaVazio() {
		
	}

	@Test
	public void testQuantidadeDeCliente() {
		
	}

	@Test
	public void testVeiculosEstaVazio() {
		
	}

	@Test
	public void testQuantidadeDeVeiculos() {
		
	}

	@Test
	public void testListarVeiculos() {
		
	}

	@Test
	public void testListarClientes() {
		
	}

	@Test
	public void testRemoverClienteCliente() {
		
	}

	@Test
	public void testContemVeiculo() {
		
	}

}
