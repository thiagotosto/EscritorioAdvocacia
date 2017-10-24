package modelo;

public class Processo {
	private int id;
	private int numero;
	private String[][] documentos;
	private Cliente[] clientes;
	private String descricao;
	
	public void setId (int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDocumentos(String[][] caminhos) {
		this.documentos = caminhos;
	}
	
	public String[][] getDocumentos() {
		return this.documentos;
	}
	
	public void setClientes(Cliente[] clientes) {
		this.clientes = clientes;
	}
	
	public Cliente[] getClientes() {
		return this.clientes;
	}
}
