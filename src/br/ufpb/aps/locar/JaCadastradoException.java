package br.ufpb.aps.locar;


public class JaCadastradoException extends RuntimeException {
	
	public JaCadastradoException () {
		super ("Valor já cadastrado!");
	}
}
