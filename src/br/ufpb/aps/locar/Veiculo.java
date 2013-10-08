package br.ufpb.aps.locar;

import java.io.Serializable;

public abstract class Veiculo implements Serializable {
	
	private String numeracao;
	private String marca;
	private String placa;
	Categoria categoria;
	
	
	public Veiculo(String marca, String numeracao, String placa){
		this.marca = marca;
		this.numeracao = numeracao;
		this.placa = placa;
		
	}
	
	public Veiculo(){
		
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Veiculo))
			return false;
		Veiculo other = (Veiculo) obj;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!numeracao.equals(other.numeracao))
			return false;
		return true;
	}

}
