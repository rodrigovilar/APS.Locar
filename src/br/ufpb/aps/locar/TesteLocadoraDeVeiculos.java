package br.ufpb.aps.locar;

import static br.ufpb.aps.locar.RestaurarObjeto.restaurar;
import static br.ufpb.aps.locar.SalvarObjetoPersistencia.salvar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



public class TesteLocadoraDeVeiculos {

	LocadoraDeVeiculos locadoraDeVeiculos;
	Cliente cliente;
	Cliente cliente2;
	Cliente cliente3;
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
		cliente3 = new Cliente();
		cliente.setCpf("123.123.123-22");
		cliente2.setCpf("321.321.321-33");
		cliente3.setCpf("101.101.101-10");
		cliente.setNome("Neemias");
		cliente2.setNome("De paula");
		cliente3.setNome("Jessyca");
	}

	@Test
	public void testeSalvarArquivo () {
		//Para Cliente
		List<Cliente> listaRecuperada;
		cliente = criarCliente();
		LocadoraDeVeiculos lc = new LocadoraDeVeiculos();
		lc.addCliente(cliente);
		salvar(lc.getClientes(), "e://clientes.bin");
		
		try {
			listaRecuperada = (List<Cliente>) restaurar("e://clientes.bin");
		} catch (FileNotFoundException e) {
			listaRecuperada = new ArrayList<Cliente>();
		}
		assertEquals(lc.getClientes().get(0), listaRecuperada.get(0));
		
		//Para Veículo
		List<Veiculo> listaRecuperada2;
		veiculo.setMarca("FIAT");
		veiculo.setPlaca("SDF-2345");
		veiculo.setCategoria(Categoria.PASSEIO);
		veiculo.setNumeracao("12345678");
		lc.addVeiculo(veiculo);
		salvar(lc.getVeiculos(), "e://veiculos.bin");
		try {
			listaRecuperada2 = (List<Veiculo>) restaurar("e://veiculos.bin");
		} catch (FileNotFoundException e) {
			listaRecuperada2 = new ArrayList<Veiculo>();
		}
		assertEquals(lc.getVeiculos().get(0), listaRecuperada2.get(0));
	}

	private Cliente criarCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Neemias");
		cliente.setCpf("123.123.123-22");
		cliente.setEnd("Rua Sem Numero");
		cliente.setCEP("58000-000");
		return cliente;
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
		facade.adicionarVeiculo(veiculo.getPlaca(), 
				veiculo.getNumeracao(), veiculo);
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



	@Test
	public void testLocarVeiculo() {   	

		try {    		
			veiculo.setPlaca("ABC123");
			locadoraDeVeiculos.cadastrarVeiculo(veiculo);
			locadoraDeVeiculos.locarVeiculo(veiculo, cliente);
			assertEquals(1, locadoraDeVeiculos.getVeiculosLocados().size());

		} catch (VeiculoException e) {
			fail("NÃO DEVERIA TER LANÇADO ESSA EXCEÇÃO");
		}

		try {
			locadoraDeVeiculos.locarVeiculo(veiculo, cliente);
			fail("DEVERIA TER LANÇADO UMA EXCEÇÃO!");

		} catch (VeiculoException e) {			
			assertEquals(1, locadoraDeVeiculos.getVeiculosLocados().size());
		}

		try {
			veiculo2.setPlaca("DEF456");
			locadoraDeVeiculos.cadastrarVeiculo(veiculo2);
			locadoraDeVeiculos.locarVeiculo(veiculo2, cliente2);
			assertEquals(2, locadoraDeVeiculos.getVeiculosLocados().size());

		} catch (VeiculoException e) {
			fail("NÃO DEVERIA TER LANÇADO ESSA EXCEÇÃO");
		}

		try {
			veiculoPasseio.setPlaca("GHI789");
			locadoraDeVeiculos.cadastrarVeiculo(veiculoPasseio);
			locadoraDeVeiculos.locarVeiculo(veiculoPasseio, cliente3);
			assertEquals(3, locadoraDeVeiculos.getVeiculosLocados().size());

		} catch (VeiculoException e) {
			fail("NÃO DEVERIA TER LANÇADO ESSA EXCEÇÃO");
		}

	}


	@Test
	public void testVeiculoLocado() {

		try {

			veiculoPasseio.setPlaca("GHI789");
			locadoraDeVeiculos.cadastrarVeiculo(veiculoPasseio);
			locadoraDeVeiculos.locarVeiculo(veiculoPasseio, cliente3);
			assertTrue(locadoraDeVeiculos.veiculoLocado("GHI789"));

		} catch (VeiculoException e) {
			fail("NÃO DEVERIA TER LANÇADO ESSA EXCEÇÃO");
		}    	    		

		try {
			veiculoPasseio2.setPlaca("ABC123");
			locadoraDeVeiculos.cadastrarVeiculo(veiculoPasseio2);
			assertFalse(locadoraDeVeiculos.veiculoLocado("ABC123"));
		} catch (VeiculoRuntimeException e) {
			assertEquals("O Veículo com a placa ABC123 não está locado!", e.getMessage());
		}

		try {
			locadoraDeVeiculos.locarVeiculo(veiculoPasseio2, cliente);
			assertTrue(locadoraDeVeiculos.veiculoLocado("ABC123"));

			veiculo.setPlaca("KJF122");
			locadoraDeVeiculos.cadastrarVeiculo(veiculo);
			locadoraDeVeiculos.locarVeiculo(veiculo, cliente);

			veiculo2.setPlaca("FGA798");
			locadoraDeVeiculos.cadastrarVeiculo(veiculo2);
			locadoraDeVeiculos.locarVeiculo(veiculo2, cliente3);

			assertTrue(locadoraDeVeiculos.veiculoLocado("GHI789"));
			assertTrue(locadoraDeVeiculos.veiculoLocado("ABC123"));
			assertTrue(locadoraDeVeiculos.veiculoLocado("KJF122"));
			assertTrue(locadoraDeVeiculos.veiculoLocado("FGA798"));

			assertEquals(4, locadoraDeVeiculos.getVeiculosLocados().size());

		} catch (VeiculoException e) {
			fail("NÃO DEVERIA TER LANÇADO ESSA EXCEÇÃO");
		}

	}


	@Test
	public void testEncerrarLocacao() {

		try {

			veiculo.setPlaca("ABC123");
			locadoraDeVeiculos.cadastrarVeiculo(veiculo);
			locadoraDeVeiculos.locarVeiculo(veiculo, cliente);
			assertTrue(locadoraDeVeiculos.veiculoLocado("ABC123"));

		} catch (VeiculoException e) {
			fail("NÃO DEVERIA TER LANÇADO ESSA EXCEÇÃO");
		}

		try {
			veiculoPasseio.setPlaca("GHI789");
			locadoraDeVeiculos.cadastrarVeiculo(veiculoPasseio);
			assertFalse(locadoraDeVeiculos.veiculoLocado("GHI789"));
		} catch (VeiculoRuntimeException e) {
			assertEquals("O Veículo com a placa GHI789 não está locado!", e.getMessage());
		}

		try {

			locadoraDeVeiculos.encerrarLocacao("ABC123");
			assertTrue(locadoraDeVeiculos.veiculoLocado("ABC123"));
			fail("DEVERIA TER LANÇADO ESSA EXCEÇÃO");

		} catch (VeiculoRuntimeException ex) {

			assertEquals("Não existem veículos locados no momento!", ex.getMessage());
		}

		try {

			locadoraDeVeiculos.locarVeiculo(veiculo, cliente);

			veiculo2.setPlaca("JHG645");
			locadoraDeVeiculos.cadastrarVeiculo(veiculo2);
			locadoraDeVeiculos.locarVeiculo(veiculo2, cliente2);

			locadoraDeVeiculos.locarVeiculo(veiculoPasseio, cliente3);

			veiculoPasseio2.setPlaca("FGS421");
			locadoraDeVeiculos.cadastrarVeiculo(veiculoPasseio2);
			locadoraDeVeiculos.locarVeiculo(veiculoPasseio2, cliente3);

			assertEquals(4, locadoraDeVeiculos.getVeiculosLocados().size());

			locadoraDeVeiculos.encerrarLocacao("ABC123");			
			locadoraDeVeiculos.encerrarLocacao("GHI789");			
			locadoraDeVeiculos.encerrarLocacao("JHG645");			
			locadoraDeVeiculos.encerrarLocacao("FGS421");

			assertEquals(1, locadoraDeVeiculos.getVeiculosLocados().size());
			fail("DEVERIA TER LANÇADO ESSA EXCEÇÃO");

		} catch (VeiculoException e) {
			fail(e.getMessage());
		} catch (VeiculoRuntimeException e) {
			assertEquals("Não existem veículos locados no momento!", e.getMessage());
		}

	}


	@Test
	public void testClientesComVeiculosLocados () {

		try {

			veiculo.setPlaca("ABC123");
			locadoraDeVeiculos.cadastrarVeiculo(veiculo);
			locadoraDeVeiculos.locarVeiculo(veiculo, cliente);

			veiculo2.setPlaca("JHG645");
			locadoraDeVeiculos.cadastrarVeiculo(veiculo2);
			locadoraDeVeiculos.locarVeiculo(veiculo2, cliente2);

			veiculoPasseio.setPlaca("IUS987");
			locadoraDeVeiculos.cadastrarVeiculo(veiculoPasseio);
			locadoraDeVeiculos.locarVeiculo(veiculoPasseio, cliente3);			

			assertEquals(3, locadoraDeVeiculos.getVeiculosLocados().size());

			assertEquals(2, locadoraDeVeiculos.getClientesComVeiculoLocado().size());
			fail("DEVERIA TER LANÇADO UMA EXCEÇÃO");

		} catch (VeiculoException e) {
			fail(e.getMessage());

		} catch (AssertionError ex) {
			assertEquals(3, locadoraDeVeiculos.getClientesComVeiculoLocado().size());
		}	

	}


}