package modelo;


public class Tarefa {
	private int id;
	private Funcionario owner;
	private String descricao;
	private String exprData;
	private String expdData;
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setOwner(Funcionario owner) {
		this.owner = owner;
	}
	
	public Funcionario getOwner() {
		return this.owner;
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
