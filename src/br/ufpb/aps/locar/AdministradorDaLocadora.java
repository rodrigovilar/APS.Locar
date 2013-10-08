package br.ufpb.aps.locar;

import java.io.Serializable;

public class AdministradorDaLocadora extends Pessoa implements Serializable{
	
	private static int quantidade;
	public String matricula;
	
	public AdministradorDaLocadora(){
		super();
		quantidade ++;
	}
	
	public String getMatricula () {
		return matricula;
	}
	
	public void setMatricula (String matricula) {
		this.matricula = matricula;
	}
}
