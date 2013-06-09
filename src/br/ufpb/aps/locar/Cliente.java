package br.ufpb.aps.locar;


public class Cliente extends Pessoa {
	
	private static int quantidade;
	
	public String CEP;
	
	public void setCEP (String CEP) {
		this.CEP = CEP;
	}
	
	public String getCEP () {
		return CEP;
	}
	
	public Cliente(){
		super();
		quantidade ++;
	}

}


