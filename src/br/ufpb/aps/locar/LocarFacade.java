package br.ufpb.aps.locar;

import java.util.Iterator;

public class LocarFacade {
LocadoraDeVeiculos locadora = new LocadoraDeVeiculos();
	
	
	
	public Cliente criarCliente(String nome, int cpf, String end){
		Cliente c = new Cliente(nome, cpf, end);
		locadora.addCliente(c);
		return c;
	}
	
	
	public Veiculos criarVeiculoPasseio(String marca, String modelo, String placa){
		Veiculos v = new VeiculoPasseio(marca, modelo, placa);
		locadora.addVeiculos(v);
		return v;
	}
	
	public Veiculos criarVeiculoUtilitario(String marca, String modelo, String placa){
		Veiculos v = new VeiculoUtilitario(marca, modelo, placa);
		locadora.addVeiculos(v);
		return v;
	}
	
	
	public int tamanhoCliente(){
		int tamanho = locadora.tamanhoDoCliente();
		return tamanho;
	}
	
	public boolean clienteEstaVazio(){
		boolean existe = locadora.clienteEstaVazio();
		return existe;
	}
	
	public void removeCliente(String nome, int cpf){
		locadora.removerCliente(nome, cpf);	
	}
	
	public void listarCliente(){
		locadora.listarClientes();
	}
	
	public void listarVeiculos(){
		locadora.listarVeiculos();
	}
	
	
}
