package br.ufpb.aps.locar;

public class AdministradorDaLocadora extends Pessoa {
	
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
