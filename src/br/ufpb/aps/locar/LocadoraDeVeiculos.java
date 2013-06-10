package br.ufpb.aps.locar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocadoraDeVeiculos {
	
	List<Veiculo> veiculos = new ArrayList<Veiculo>();
	List<Cliente> clientes = new ArrayList<Cliente>();
	List<AdministradorDaLocadora> administradoresDaLocadora = new ArrayList<AdministradorDaLocadora>();
	
	public LocadoraDeVeiculos(){
		
	}
	
	public List <Veiculo> addVeiculo(Veiculo veiculo) throws JaCadastradoException {
		for (Veiculo v : veiculos) {
			if (veiculo.getPlaca() == v.getPlaca() && veiculo.getNumeracao() == v.getNumeracao()) {
				throw new JaCadastradoException();
			}
		}
		veiculos.add(veiculo);
		return veiculos;
	}


	public List <Cliente> addCliente(Cliente cliente) throws JaCadastradoException {
		for (Cliente c : clientes) {
			if (cliente.getCpf() == c.getCpf()) {
				throw new JaCadastradoException();
			}
		}
		clientes.add(cliente);
		return clientes;
	}
	
	/*
	public Cliente addCliente(Cliente cliente){
		clientes.add(cliente);
		return cliente;
	}
	
	public Veiculos addVeiculos(Veiculos veiculo){
		veiculos.add(veiculo);
		return veiculo;
	}
	*/
	public Cliente removerCliente(String nome, String cpf){
		for(Cliente cliente : clientes){
			if((cliente.getNome()== nome)&&(cliente.getCpf()==cpf)){
				clientes.remove(cliente);
				return cliente;
			}
		}
		throw new NaoCadastradoNoSistemaException("Nao cadastrado no sistema");
		
	}
	

	
	public List<AdministradorDaLocadora> addAdministradoresDaLocadora(AdministradorDaLocadora administradorDaLocadora) {
		for (AdministradorDaLocadora a : administradoresDaLocadora) {
			if (a.getMatricula() == administradorDaLocadora.getMatricula()) {
				throw new JaCadastradoException();
			}
		}
		administradoresDaLocadora.add(administradorDaLocadora);
		return administradoresDaLocadora;
	}
	
	public List<AdministradorDaLocadora> listarAdministradoresDaLocadora() {
		return administradoresDaLocadora;
	}
	
	
	
	public Veiculo removerVeiculo(String numeracao){
		for(Veiculo v: veiculos){
			if(v.getNumeracao()==numeracao){
				veiculos.remove(v);
				return v;
			}
		}
		throw new NaoCadastradoNoSistemaException("nao cadastrado no sistema");
	}
	

	public AdministradorDaLocadora removerAdm(String matricula) {
		for (AdministradorDaLocadora adm : administradoresDaLocadora){
			if(adm.getMatricula() == matricula) {
				administradoresDaLocadora.remove(adm);
				return adm;
			}
		}
		throw new NaoCadastradoNoSistemaException("nao cadastrado no sistema");
	}
	
	public boolean isAdmExiste(String matricula) {
		for (AdministradorDaLocadora a : administradoresDaLocadora){
			if (a.getMatricula() == matricula) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public boolean clienteEstaVazio(){
		boolean vazio = clientes.isEmpty();
		return vazio;
	}
	
	
	public int quantidadeDeCliente(){
		int tamanho;
		tamanho = clientes.size();
		return tamanho;
	}

	public boolean veiculosEstaVazio(){
		boolean vazio = veiculos.isEmpty();
		return vazio;
	}
	
	public int quantidadeDeVeiculos(){
		int tamanho = veiculos.size();
		return tamanho;
	}
	
	public void listarVeiculos(){
		Iterator<Veiculo> it = veiculos.iterator();
		while(it.hasNext()){
			Veiculo v = it.next();
			System.out.println(v.getMarca()+ v.getNumeracao()+ v.getPlaca());
		}
	}
	
	public void listarClientes(){
		Iterator<Cliente> it = clientes.iterator();
		while(it.hasNext()){
			Cliente c = it.next();
			System.out.println(c.getNome()+" "+ c.getCpf());
		}
		
	}


	public List<Cliente> listarCliente () {
		return clientes;
	}
	
	public List<Veiculo> listarVeiculo () {
		return veiculos;
	}
	

	public Cliente removerCliente(Cliente c) {
		for(Cliente clien: clientes){
			if(clien ==  c){
				clientes.remove(c);
				return c;
			}
		}
		
		return null;
	}
	
	public boolean contemVeiculo(Veiculo veiculo, String placa){
		boolean contem = false;
		for(Veiculo veic: veiculos){
			if(veic.getPlaca().equals(placa)){
				return contem = veiculos.contains(veiculo);	
			}
		}
		return false;
	}
	
	

	public boolean isClienteExiste(String cpf) {
		for (Cliente cliente : clientes){
			if (cliente.getCpf() == cpf) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isVeiculoExiste(String numeracao) {
		for (Veiculo v : veiculos){
			if (v.getNumeracao() == numeracao) {
				return true;
			}
		}
		return false;
	}
	
	public Cliente buscarCliente(String cpf) {
		for (Cliente cliente : clientes){
			if (cliente.getCpf() == cpf) {
				return cliente;
			}
		}
		return null;
	}
	
	public Veiculo buscarVeiculo(String numeracao) {
		for (Veiculo v : veiculos){
			if (v.getNumeracao() == numeracao) {
				return v;
			}
		}
		return null;
	}
	
	
	public void  cadastrarVeiculo(Veiculo veiculo) throws JaCadastradoException {
		for (Veiculo v : veiculos) {
			if (v.getPlaca().equals(veiculo.getPlaca())){
				throw new JaCadastradoException();
			}
		}
		veiculos.add(veiculo);
		
	}

	
	
	
	

}
