package br.ufpb.aps.locar;

public class VeiculoException extends Exception {

	public VeiculoException () {
		this("[ERROR]: VeiculoException!");
	}
	public VeiculoException (String msg) {
		super(msg);
	}
	
}
