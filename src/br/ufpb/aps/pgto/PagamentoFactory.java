package br.ufpb.aps.pgto;

import java.security.InvalidParameterException;

public class PagamentoFactory {
	
	/* Singleton */
	private static PagamentoFactory factory;	
	public static PagamentoFactory getInstance() {
		if (factory == null)
			factory = new PagamentoFactory();
		return factory;		
	}
	
	/* factory method */
	public Pagamento getPagamento(PagamentoType type) throws InvalidParameterException{
		switch (type) {
			case DINHEIRO:
				return new PagamentoDinheiro();
			case PONTOS:
				return new PagamentoPontos();
		}
		throw new InvalidParameterException("[ERROR]: " + 
				"\nDescription: PagamentoType not found!");
	}
	
}
