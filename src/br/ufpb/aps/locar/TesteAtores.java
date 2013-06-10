package br.ufpb.aps.locar;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class TesteAtores {
	
	Cliente pessoa;
	VeiculoPasseio veiculo;
	LocarFacade facade;
	LocadoraDeVeiculos locadoraDeVeiculos;
	
	@Before
	public void inicializar () {
		this.facade = new LocarFacade();
		this.locadoraDeVeiculos = new LocadoraDeVeiculos();
	}
	
	@Test
	public void testCriarCliente() {
		Cliente cliente1 = new Cliente();
		cliente1.setCEP("58052-200");
		facade.adiconarCliente(cliente1.getCEP(),"Neemias","", cliente1);
		List <Cliente> clientesSalvos = facade.listarClientes();
		assertEquals(1, clientesSalvos.size());
		Cliente cliente1Salvo = clientesSalvos.get(0);
		assertEquals(cliente1, cliente1Salvo);
	}
	
	@Test
	public void testCriarVeiculoPasseio() {
		VeiculoPasseio veiculo1 = new VeiculoPasseio();
		veiculo1.setMarca("UNO");
		veiculo1.setNumeracao("MX404");
		facade.adicionarVeiculo("MNO-1506",veiculo1.getNumeracao(), veiculo1);
		List <Veiculo> veiculosSalvos = facade.listarVeiculos();
		assertEquals(1, veiculosSalvos.size());
		VeiculoPasseio veiculo1Salvo = (VeiculoPasseio) veiculosSalvos.get(0);
		assertEquals(veiculo1, veiculo1Salvo);
	}
	
	@Test
	public void testCriarVeiculoUtilitario() {
		VeiculoUtilitario veiculoUm = new VeiculoUtilitario();
		veiculoUm.setNumeroChasi("12345");
		facade.adicionarVeiculo("MNO-1506","", veiculoUm);
		List <Veiculo> veiculosSalvos = facade.listarVeiculos();
		VeiculoUtilitario veiculo1Salvo = (VeiculoUtilitario) veiculosSalvos.get(0);
		assertEquals(veiculoUm, veiculo1Salvo);
	}
	
	@Test
	public void testCriarAdministradorDaLocadora () {
		AdministradorDaLocadora administradorDaLocadora = new AdministradorDaLocadora();
		administradorDaLocadora.setNome("Jubileu");
		administradorDaLocadora.setCpf("123345");
		administradorDaLocadora.setEnd("Rua do Porto");
		administradorDaLocadora.setMatricula("123456");
		facade.adicionarAdministrador("12345", administradorDaLocadora);
		List <AdministradorDaLocadora> admSalvo = facade.listarAdministradores();
		assertEquals(1, admSalvo.size());
		AdministradorDaLocadora outroAdmSalvo = admSalvo.get(0);
		assertEquals(administradorDaLocadora, outroAdmSalvo);
	}
}