package br.ufpb.aps.locar;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import java.util.*;

import java.util.Iterator;

public class LocarFacade {
	LocadoraDeVeiculos locadora = new LocadoraDeVeiculos();
	Pattern placa;
	
	
	
		public void adiconarCliente (String CEP, String nome, String cpf, Cliente cliente) {
			cliente.setCEP(CEP);
			cliente.setNome(nome);
			cliente.setCpf(cpf);
			if (nome != null && cpf != null) {
				if (Pattern.matches("[a-zA-Z]+", nome) || (Pattern.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", cpf)) || Pattern.matches("^\\d{5,5}-?\\d{3,3}$+", CEP)) {			
				locadora.addCliente(cliente);
				} else {
					throw new ValorInvalidoException();
				}
			} else {
				throw new ValorNuloException();
			}
		}
		

		
		public void adicionarVeiculo (String placaVeiculo, String numeracao, Veiculos veiculo) throws ValorInvalidoException {
			veiculo.setPlaca(placaVeiculo);
			veiculo.setNumeracao(numeracao);
			if (placaVeiculo != null && numeracao != null) {
				if (Pattern.matches("[a-zA-Z]{3,3}-\\d{4,4}+", placaVeiculo) || (Pattern.matches("^[0-9]+", numeracao))) {
					locadora.addVeiculo(veiculo);
				} else {
					throw new ValorInvalidoException();
				}
			} else {
				throw new ValorNuloException();
			}
		}
		

		public void adicionarVeiculoUtilitario (String numeracaoChasi, Veiculos veiculo) {
			if (Pattern.matches("^[0-9]+", numeracaoChasi)) {
				locadora.addVeiculo(veiculo);
			} else {
				throw new ValorInvalidoException();
			}
		}
		
		public void adicionarAdministrador (String matricula, AdministradorDaLocadora a) {
			a.setMatricula(matricula);
			if (matricula != null) {
				if (Pattern.matches("^[0-9]+", matricula)) {
					locadora.addAdministradoresDaLocadora(a);
				} else {
					throw new ValorInvalidoException();
				}
			} else {
				throw new ValorNuloException();
			}
		}
		
		public List<AdministradorDaLocadora> listarAdministradores () {
			return locadora.listarAdministradoresDaLocadora();
		}
		
		public List<Cliente> listarClientes () {
			return locadora.listarCliente();
		}
		
		public List<Veiculos> listarVeiculos () {
			return locadora.listarVeiculo();
		}
	
		
	/*
	
	public Cliente criarCliente(String nome, String cpf, String end){
		Cliente c = new Cliente(nome, cpf, end);
		locadora.addCliente(c);
		return c;
	}
	*/
	public Cliente getCliente(){
		return getCliente();
	}
	
	
	public Veiculos criarVeiculoPasseio(String marca, String modelo, String placa){
		Veiculos v =  criaVeiculoPasseio(marca, modelo, placa);
		locadora.addVeiculo(v);
		return v;
	}
	
	public Veiculos criarVeiculoUtilitario(String marca, String modelo, String placa){
		Veiculos v = criaVeiculoUtilitario(marca, modelo, placa);
		locadora.addVeiculo(v);
		return v;
	}
	
	protected Veiculos criaVeiculoPasseio(String marca, String numeracao, String placa){
		return new VeiculoPasseio(marca, numeracao, placa);
	}
	
	protected Veiculos criaVeiculoPasseio(){
		return new VeiculoPasseio();
	}
	
	protected Veiculos criaVeiculoUtilitario(){
		return new VeiculoUtilitario();
	}
	protected Veiculos criaVeiculoUtilitario(String marca, String numeracao, String placa){
		return new VeiculoUtilitario(marca, numeracao, placa);
	}
	
	
	
	public int quantidadeDeCliente(){
		int quantidade = locadora.quantidadeDeCliente();
		return quantidade;
	}
	
	public int quantidadeDeVeiculos(){
		int quantidade = locadora.quantidadeDeVeiculos();
		return quantidade;
	}
	
	
	public boolean clienteEstaVazio(){
		boolean existe = locadora.clienteEstaVazio();
		return existe;
	}
	
	public boolean veiculoEstaVazio(){
		boolean existe = locadora.veiculosEstaVazio();
		return existe;
	}
	
	public void removerCliente(String nome, String cpf){
		locadora.removerCliente(nome, cpf);	
	}
	
	public void removerVeiculo(String placa){
		locadora.removerVeiculo(placa);
	}
	
	/*
	public void listarCliente(){
		locadora.listarClientes();
	}
	
	public void listarVeiculos(){
		locadora.listarVeiculos();
	}
	*/
	
}
