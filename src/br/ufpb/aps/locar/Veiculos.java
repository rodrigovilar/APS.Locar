package br.ufpb.aps.locar;

public abstract class Veiculos {
	
	private String modelo;
	private String marca;
	private String placa;

	
	
	public Veiculos(String marca, String modelo, String placa){
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
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
		return "MARCA: "+this.getMarca()+"MODELO: "+this.getModelo()+"PLACA: "+this.getModelo();
	}
	

}
