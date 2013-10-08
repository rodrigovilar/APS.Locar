package br.ufpb.aps.locar;

import java.io.Serializable;
import java.util.Date;

public class Operador extends Pessoa implements Serializable {

	private String matricula;
	private String setor;
	private Date admissao; /* cálculo de férias */
	private String email;
	private String senha;
	
	
	public Operador(String _nome, String _cpf, 
			String _mat, String _setor, Date _adms, String _senha) {
		this(_nome, _cpf, _mat, _setor, _adms, _senha, "");
	}
	
	public Operador(String _nome, String _cpf,
			String _mat, String _setor, Date _adms, String _senha, String _email) {
		super(_nome, _cpf);
		matricula = _mat;
		setor = _setor;
		admissao = _adms;
		email = _email;
		senha = _senha;
	}

	/* Getters and Setters */

	public String getMatricula() {
		return matricula;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public Date getAdmissao() {
		return admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
