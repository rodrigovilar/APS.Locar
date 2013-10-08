package br.ufpb.aps.locar;

import java.io.Serializable;

public abstract class Pessoa implements Serializable{

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

	@Override
	public String toString(){
		return "NOME: " +this.nome + "CPF: "+this.cpf + "ENDEREÇO "+ this.end; 
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pessoa))
			return false;
		Pessoa other = (Pessoa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

}