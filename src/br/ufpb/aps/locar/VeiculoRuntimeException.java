package br.ufpb.aps.locar;

public class VeiculoRuntimeException extends RuntimeException {

	public VeiculoRuntimeException () {
		this("[ERROR]: VeiculoRuntimeException!");
	}
	public VeiculoRuntimeException (String msg) {
		super(msg);
	}
	
}
