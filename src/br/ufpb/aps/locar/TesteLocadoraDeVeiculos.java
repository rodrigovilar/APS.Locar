package br.ufpb.aps.locar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class TesteLocadoraDeVeiculos {
	
	LocadoraDeVeiculos locadoraDeVeiculos;
	Cliente cliente;
	Cliente cliente2;
	LocarFacade facade;
	VeiculoPasseio veiculo;
	VeiculoPasseio veiculo2;
	VeiculoUtilitario veiculoUtilitario;
	VeiculoUtilitario veiculoUtilitario2;
	VeiculoPasseio veiculoPasseio;
	VeiculoPasseio veiculoPasseio2;
	AdministradorDaLocadora adm;
	AdministradorDaLocadora adm2;
	
	@Before
	public void inicializacaoLocadora () {
		locadoraDeVeiculos = new LocadoraDeVeiculos();
	}
	
	@Before
	public void inicializacaoVeiculo () {
		this.veiculo = new VeiculoPasseio();
		this.veiculo2 = new VeiculoPasseio();
		this.veiculoUtilitario = new VeiculoUtilitario();
		this.veiculoUtilitario2 = new VeiculoUtilitario();
		this.veiculoPasseio = new VeiculoPasseio();
		this.veiculoPasseio2 = new VeiculoPasseio();
	}
	
	@Before
	public void inicializacaoAdm () {
		this.adm = new AdministradorDaLocadora();
		adm.setMatricula("12345");
		this.adm2 = new AdministradorDaLocadora();
		adm2.setMatricula("54321");
	}
	
	@Before
	public void inicializacaoFacade () {
		facade = new LocarFacade();
	}
	
	@Before
	public void inicializacaoClientes () {
		cliente = new Cliente();
		cliente2 = new Cliente();
		cliente.setCpf("123.123.123-22");
		cliente2.setCpf("321.321.321-33");
		cliente.setNome("Neemias");
		cliente2.setNome("De paula");
	}
	
	@Test
	public void testeAddVeiculoUtilitario () {
		veiculoUtilitario.setNumeroChasi("1234565");
		locadoraDeVeiculos.addVeiculo(veiculoUtilitario);
	}
	
	@Test (expected = ValorInvalidoException.class)
	public void testeNumeroChassiInvalido () {
		veiculoUtilitario.setNumeroChasi("123ss");
		facade.adicionarVeiculoUtilitario(veiculoUtilitario.getNumeroChasi(), veiculoUtilitario);
	}
	
	@Test
	public void testeAddCliente () {
		locadoraDeVeiculos.addCliente(cliente);
		locadoraDeVeiculos.addCliente(cliente2);
		assertEquals(2, locadoraDeVeiculos.clientes.size());
	}
	
	@Test
	public void testeAddAdm () {
		locadoraDeVeiculos.addAdministradoresDaLocadora(adm);
		assertEquals(1, locadoraDeVeiculos.administradoresDaLocadora.size());
	}
	
	@Test
	public void testeRemoverAdm () {
		locadoraDeVeiculos.addAdministradoresDaLocadora(adm);
		locadoraDeVeiculos.addAdministradoresDaLocadora(adm2);
		locadoraDeVeiculos.removerAdm("12345");
		assertEquals(1, locadoraDeVeiculos.administradoresDaLocadora.size());
	}

	@Test
	public void testeRemoverCliente () {
		locadoraDeVeiculos.addCliente(cliente);
		locadoraDeVeiculos.addCliente(cliente2);
		locadoraDeVeiculos.removerCliente(cliente.getNome(), "123.123.123-22");
		assertEquals(1, locadoraDeVeiculos.clientes.size());
	}
	
	@Test
	public void testeEditarCliente () {
		Cliente clienteQualquer = new Cliente();
		clienteQualquer.setCpf("123.123.123-12");
		locadoraDeVeiculos.addCliente(clienteQualquer);
		Cliente maisUmCliente = locadoraDeVeiculos.buscarCliente("123.123.123-12");
		maisUmCliente.setCpf("444.444.444-12");
		assertEquals("444.444.444-12", clienteQualquer.getCpf());
	}
	
	@Test
	public void testeAddVeiculo () {
		veiculo.setMarca("UNO");
		veiculo.setPlaca("ASC-1232");
		locadoraDeVeiculos.addVeiculo(veiculo);
		assertEquals(1,locadoraDeVeiculos.veiculos.size());
	}
	
	@Test
	public void testeRemoverVeiculo () {
		veiculo.setNumeracao("1233456");
		veiculo2.setNumeracao("12334");
		locadoraDeVeiculos.addVeiculo(veiculo);
		locadoraDeVeiculos.addVeiculo(veiculo2);
		locadoraDeVeiculos.removerVeiculo("12334");
		assertEquals(1,locadoraDeVeiculos.veiculos.size());
	}
	
	@Test
	public void testeEditarVeiculo () {
		VeiculoPasseio veiculoQualquer = new VeiculoPasseio();
		veiculoQualquer.setNumeracao("12345");
		veiculoQualquer.setPlaca("JSD-0987");
		locadoraDeVeiculos.addVeiculo(veiculoQualquer);
		VeiculoPasseio maisUmVeiculo = (VeiculoPasseio) locadoraDeVeiculos.buscarVeiculo("12345");
		maisUmVeiculo.setPlaca("ABC-1234");
		assertEquals("ABC-1234", veiculoQualquer.getPlaca());
	}

	@Test (expected = ValorInvalidoException.class)
	public void testePlacaVeiculoInvalida () {
		veiculo.setPlaca("ABC-13s23");
		facade.adicionarVeiculo(veiculo.getPlaca(), "", veiculo);
	}

	@Test (expected = ValorInvalidoException.class)
	public void testeNumeracaoInvalida () {
		veiculo.setNumeracao("12s345");
		facade.adicionarVeiculo("", veiculo.getNumeracao(), veiculo);
	}
	
	@Test (expected = ValorInvalidoException.class)
	public void testeNomePessoaInvalido () {
		cliente.setNome("Neemias123");
		facade.adiconarCliente("", cliente.getNome(),"", cliente);
	}
	
	@Test (expected = ValorInvalidoException.class)
	public void testeCEPPessoaInvalido () {
		cliente.setCEP("asihdgsgh2");
		facade.adiconarCliente(cliente.getCEP(), "","", cliente);
	}
	
	@Test (expected = ValorInvalidoException.class)
	public void cadastrarCPFInvalido () {
		cliente.setCpf("Oi, eu sou abestalhado, e não quero colocar meu CPF aqui! ahushaushau");
		facade.adiconarCliente("","", cliente.getCpf(), cliente);
	}
	
	@Test (expected = JaCadastradoException.class)
	public void cadastrarNumeracaoJaExistentete () {
		veiculo.setNumeracao("12345");
		veiculo2.setNumeracao("12345");
		locadoraDeVeiculos.addVeiculo(veiculo);
		locadoraDeVeiculos.addVeiculo(veiculo2);
	}
	
	@Test (expected = JaCadastradoException.class)
	public void cadastrarPlacaJaExistente () {
		veiculo.setPlaca("ABC-1234");
		veiculo2.setPlaca("ABC-1234");
		locadoraDeVeiculos.addVeiculo(veiculo);
		locadoraDeVeiculos.addVeiculo(veiculo2);
	}
	
	@Test (expected = JaCadastradoException.class)
	public void cadastrasCpfClienteExistente () {
		cliente.setCpf("12345");
		cliente2.setCpf("12345");
		locadoraDeVeiculos.addCliente(cliente);
		locadoraDeVeiculos.addCliente(cliente2);
	}
	
	@Test
	public void buscaAdmNaoExistente () {
		locadoraDeVeiculos.addAdministradoresDaLocadora(adm);
		assertFalse(locadoraDeVeiculos.isAdmExiste("12344"));
	}
	
	@Test
	public void buscarVeiculoNaoExistente () {
		veiculo.setNumeracao("12345");
		locadoraDeVeiculos.addVeiculo(veiculo);
		assertFalse(locadoraDeVeiculos.isVeiculoExiste("123456"));
	}
	
	@Test
	public void buscarClienteNaoExistente () {
		locadoraDeVeiculos.addCliente(cliente);
		assertFalse(locadoraDeVeiculos.isClienteExiste("123-123-123-11"));
	}
	
	@Test (expected = ValorNuloException.class)
	public void addClienteSemCPFeNome () {
		cliente.setNome(null);
		cliente.setCpf(null);
		facade.adiconarCliente("",cliente.getNome(), cliente.getCpf(), cliente);
	}
	
	@Test (expected = ValorNuloException.class)
	public void addVeiculoSemNumeracaoePlaca () {
		veiculo.setPlaca(null);
		veiculo.setNumeracao(null);
		facade.adicionarVeiculo(veiculo.getPlaca(), veiculo.getNumeracao(), veiculo);
	}
	
	@Test (expected = ValorNuloException.class)
	public void addAdmSemMatricula () {
		adm.setMatricula(null);
		facade.adicionarAdministrador(adm.getMatricula(), adm);
	}
	
	@Test (expected = NaoCadastradoNoSistemaException.class)
	public void removerClienteInexistente () {
		locadoraDeVeiculos.addCliente(cliente);
		locadoraDeVeiculos.removerCliente("Neemias", "222.122.123-22");
	}
	
	@Test (expected = NaoCadastradoNoSistemaException.class)
	public void removerVeiculoInexistente() {
		locadoraDeVeiculos.addVeiculo(veiculo);
		locadoraDeVeiculos.removerVeiculo("909090");
	}
	
	@Test (expected = NaoCadastradoNoSistemaException.class)
	public void removerAdmInexistente() {
		locadoraDeVeiculos.addAdministradoresDaLocadora(adm);
		locadoraDeVeiculos.removerAdm("34750934");
	}
	
	@Test (expected = JaCadastradoException.class)
	public void addAdmJaCadastrado () {
		adm.setMatricula("12345");
		adm2.setMatricula("12345");
		locadoraDeVeiculos.addAdministradoresDaLocadora(adm);
		locadoraDeVeiculos.addAdministradoresDaLocadora(adm2);
	}
	
	@Test (expected = ValorInvalidoException.class)
	public void valorMatAdmIvalido () {
		adm.setMatricula("asdasd");
		facade.adicionarAdministrador(adm.getMatricula(), adm);
	}

	 @Test 
	public void removerVeiculoUtilitarioInexistente() {
				
		veiculo.setNumeracao("123");
		veiculo.setPlaca("ABC123");
		veiculoUtilitario.setPlaca("CDE789");
		veiculoUtilitario2.setPlaca("FGH876");
		
		locadoraDeVeiculos.cadastrarVeiculo(veiculoUtilitario);
		locadoraDeVeiculos.cadastrarVeiculo(veiculoUtilitario2);
	
		System.out.println(locadoraDeVeiculos.listarVeiculo().size());
		
		try{			
			assertEquals(veiculo,locadoraDeVeiculos.removerVeiculo("123"));
			fail("Deveria falhar");
			
		} catch (NaoCadastradoNoSistemaException ex) {
			assertEquals("nao cadastrado no sistema", ex.getMessage());
		}
	
    }
	
    @Test
  	public void removerVeiculoPasseioInexistente() {
  				
  		veiculo.setNumeracao("234");
  		veiculo.setPlaca("ABC123");
  		veiculoPasseio.setPlaca("CDE780");
  		veiculoPasseio2.setPlaca("FGH877");
  		
  		locadoraDeVeiculos.cadastrarVeiculo(veiculoPasseio);
  		locadoraDeVeiculos.cadastrarVeiculo(veiculoPasseio2);
  	
  		System.out.println(locadoraDeVeiculos.listarVeiculo().size());
  		
  		try{			
  			assertEquals(veiculo,locadoraDeVeiculos.removerVeiculo("234"));
  			fail("Deveria falhar");
  			
  		} catch (NaoCadastradoNoSistemaException ex) {
  			assertEquals("nao cadastrado no sistema", ex.getMessage());
  		}
  		
      }
	
}