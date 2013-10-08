package br.ufpb.aps.pgto;

import java.io.Serializable;

public class PagamentoPontos extends Pagamento implements Serializable {
	
	private int numero;

	public int getNumeroPontos() {
		return numero;
	}

	public void setNumeroPontos(int numero) {
		this.numero = numero;
	}
	
}
