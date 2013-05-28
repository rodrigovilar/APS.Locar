package br.ufpb.aps.locar;

public class Main {

	
	public static void main(String[] args) {
		
		LocarFacade facade = new LocarFacade();
		
		
		
		facade.criarCliente("well", 123, "valentina figueiredo");
		facade.criarCliente("depaula", 321, "bancarios");
		facade.criarCliente("luiz", 345, "mangabeira");
		
			
		boolean existe = facade.clienteEstaVazio();
		System.out.println(existe);
		
			
		int tamanho = facade.tamanhoCliente();
		System.out.println(tamanho);
		
		facade.listarCliente();
		
		facade.removeCliente("well", 123);
		facade.listarCliente();
		
		

	}
		

	

}
