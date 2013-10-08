package br.ufpb.aps.pgto;
import java.io.Serializable;
import java.util.Date;

import br.ufpb.aps.locar.Cliente;
import br.ufpb.aps.locar.Operador;
import br.ufpb.aps.locar.Veiculo;

public abstract class Pagamento implements Serializable {
	
	private Cliente cliente;
	private Veiculo veiculo;
	private Operador operador;
	private Date data;	
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}
		
}
