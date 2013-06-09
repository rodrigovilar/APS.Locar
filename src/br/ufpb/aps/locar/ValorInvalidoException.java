package br.ufpb.aps.locar;

//Adiconei o exception ValorInvalidoException.
public class ValorInvalidoException extends RuntimeException {
	
	public ValorInvalidoException () {
		super("Valor inválido!");
	}

}
