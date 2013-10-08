package br.ufpb.aps.pgto.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import br.ufpb.aps.pgto.Pagamento;

public class PagamentoIO {

	private File file;
	
	public PagamentoIO(String _filepath) {
		file = new File(_filepath);
	}

	/**
	 * Recupera uma estrutura List<Pagamento> do arquivo especificado.
	 * @return {@link List}
	 * @exception {@link NullPointerException}
	 */
	public List<Pagamento> getPagamentosRegistradosNoArquivo() throws NullPointerException {
		List<Pagamento> pgtos = null;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			pgtos = (List<Pagamento>) input.readObject();
			input.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		if (pgtos == null)
			throw new NullPointerException("Estrutura de pagamentos nula!");
		return pgtos;
	}
	
	/**
	 * Salva uma estrutura List<Pagamento> em um arquivo local. No arquivo,
	 * será registrado o objeto que representa essa estrutura.
	 * @param {@link List}
	 */
	public void registrarPagamentosNoArquivo(List<Pagamento> pagamentos) {
		try {			
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(pagamentos);
			out.flush();
			out.close();			
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public boolean removerArquivo() {
		if (file.exists())
			return file.delete();
		return false;
	}
		
}
