package br.ufpb.aps.locar;

public class VeiculoPasseio extends Veiculos{
	
private final int QTDE_MAXIMA_DE_PASSAGEIRO = 5;
	
	public VeiculoPasseio(String marca, String modelo, String placa){
		super(marca, modelo, placa);
	}

	public int getQTDE_MAXIMA_DE_PASSAGEIRO() {
		return QTDE_MAXIMA_DE_PASSAGEIRO;
	}
	

}
