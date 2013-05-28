package br.ufpb.aps.locar;

public class VeiculoUtilitario extends Veiculos {
	
private final int QTDE_MAXIMA_DE_PASSAGEIRO = 2;
	
	
	public VeiculoUtilitario(String marca, String modelo, String placa){
		super(marca, modelo, placa);
	}
	
	
	public int getQTDE_MAXIMA_DE_PASSAGEIRO(){
		return QTDE_MAXIMA_DE_PASSAGEIRO;
	}


}
