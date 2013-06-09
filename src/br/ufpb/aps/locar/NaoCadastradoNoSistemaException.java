package br.ufpb.aps.locar;

public class NaoCadastradoNoSistemaException extends RuntimeException {
	
	public NaoCadastradoNoSistemaException () {
		super("Não cadastrado no sistema, impossível remover!");
	}
}
