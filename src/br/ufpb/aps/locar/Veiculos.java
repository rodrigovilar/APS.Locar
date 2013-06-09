package br.ufpb.aps.locar;

public abstract class Veiculos {
	
	private String numeracao;
	private String marca;
	private String placa;

	
	
	public Veiculos(String marca, String numeracao, String placa){
		this.marca = marca;
		this.numeracao = numeracao;
		this.placa = placa;
		
	}
	
	public Veiculos(){
		
	}
	

	public String getNumeracao() {
		return numeracao;
	}
	public void setNumeracao(String numeracao) {
		this.numeracao = numeracao;
	}
	
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	
	
	
	public String toString(){
		return "MARCA: "+this.getMarca()+"MODELO: "+this.getNumeracao()+"PLACA: "+this.getPlaca();
	}
	

}
