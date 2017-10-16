package modelo;

public class Processo {
	private int id;
	private String nome;
	private String[] documentos;
	private String descricao;
	
	public void setId (int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDocumentos(String[] caminhos) {
		this.documentos = caminhos;
	}
	
	public String[] getDocumentos() {
		return this.documentos;
	}
}
