package br.ufpb.aps.locar;

public class Cliente {
	
private static int quantidade;
	
	
	private String nome;
	private String end;
	private int cpf;
	
	public Cliente(String nome, int cpf, String end){
		this.nome = nome;
		this.cpf = cpf;
		this.end = end;
		quantidade++;
	}

	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEnd() {
		return this.end;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	public int getCpf() {
		return this.cpf;
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public String toString(){
		return "NOME: " +this.nome + "CPF: "+this.cpf + "ENDEREÇO "+ this.end; 
	}
	

}
