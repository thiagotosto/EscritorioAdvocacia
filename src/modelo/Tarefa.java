package modelo;


public class Tarefa {
	private int id;
	private String nome;
	private Funcionario owner;
	private Funcionario criador;
	private String descricao;
	private String exprData;
	private String expdData;
	
	
	public void setId(int id) {
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
	
	public void setOwner(Funcionario owner) {
		this.owner = owner;
	}
	
	public Funcionario getOwner() {
		return this.owner;
	}
	
	public void setCriador(Funcionario criador) {
		this.criador = criador;
	}
	
	public Funcionario getCriador() {
		return this.criador;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	//REFERENTE A DATA DE EXPIRAÇÃO DA TAREFA
	
	public void setExprData(String exprData) {
		this.exprData = exprData;
	}
	
	public String getExprData() {
		return this.exprData;
	}
	
	//REFERENTE A DATA DE EXPEDIÇÃO DA TAREFA
	
	public void setExpdData(String expdData) {
		this.expdData = expdData;
	}
	
	public String getExpdData() {
		return this.expdData;
	}
}
