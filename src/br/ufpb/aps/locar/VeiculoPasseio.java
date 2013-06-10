package br.ufpb.aps.locar;

public class VeiculoPasseio extends Veiculo{
	
	private final int QTDE_MAXIMA_DE_PASSAGEIRO = 5;

	LocarFacade facade;
	LocadoraDeVeiculos locadora;
	Veiculo veiculos;
	
	public VeiculoPasseio(){
		super();
	}
	
	public VeiculoPasseio(String marca, String numeracao, String placa){
		super(marca,numeracao,placa);
	}

	public int getQTDE_MAXIMA_DE_PASSAGEIRO() {
		return QTDE_MAXIMA_DE_PASSAGEIRO;
	}
	
	
		
		
	
	
	
	

}
