package br.ufpb.aps.locar;

import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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


