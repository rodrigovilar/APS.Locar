package br.ufpb.aps.locar;


public abstract class Pessoa {
	
	private String nome;
	private String end;
	private String cpf;
	
	public Pessoa (){
		
	}
	
	public Pessoa(String _nome, String _cpf) {
		this(_nome, _cpf, "");
	}
	
	public Pessoa(String _nome, String _cpf, String _end) {
		nome = _nome;
		cpf = _cpf;
		end = _end;
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
	public String getCpf() {
		return this.cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String toString(){
		return "NOME: " +this.nome + "CPF: "+this.cpf + "ENDEREÇO "+ this.end; 
	}
	

}