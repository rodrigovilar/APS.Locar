package br.ufpb.aps.locar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocadoraDeVeiculos {
	
	List<Veiculos> veiculos = new ArrayList<Veiculos>();
	List<Cliente> clientes = new ArrayList<Cliente>();
	
	public LocadoraDeVeiculos(){
		
	}
	
	
	
	public Cliente addCliente(Cliente cliente){
		clientes.add(cliente);
		return cliente;
	}
	
	public Veiculos addVeiculos(Veiculos veiculo){
		veiculos.add(veiculo);
		return veiculo;
	}
	
	public Cliente removerCliente(String nome, int cpf){
		for(Cliente cliente : clientes){
			if((cliente.getNome()== nome)&&(cliente.getCpf()==cpf)){
				clientes.remove(cliente);
				return cliente;
			}
		}
		return null;
		
	}
	
	public Veiculos removerVeiculos(String placa){
		for(Veiculos v: veiculos){
			if(v.getPlaca()==placa){
				veiculos.remove(v);
				return v;
			}
		}
		return null;
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
		Iterator<Veiculos> it = veiculos.iterator();
		while(it.hasNext()){
			Veiculos v = it.next();
			System.out.println(v.getMarca()+ v.getModelo()+ v.getPlaca());
		}
	}
	
	public void listarClientes(){
		Iterator<Cliente> it = clientes.iterator();
		while(it.hasNext()){
			Cliente c = it.next();
			System.out.println(c.getNome()+" "+ c.getCpf());
		}
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
	
	
	

	
	
	
	
	
	
	

}
