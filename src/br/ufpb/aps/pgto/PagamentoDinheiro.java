package br.ufpb.aps.pgto;

import java.io.Serializable;

public class PagamentoDinheiro extends Pagamento implements Serializable {
	
	private float valor;

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
}
