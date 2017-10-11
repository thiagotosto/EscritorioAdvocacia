package modelo;

public class Gerente extends Funcionario{

	int idGerente;
	int idFuncionario;
	
	public void setIdGerente(int id){
		this.idGerente = id;
	}
	
	public int getIdGerente(){
		return this.idGerente;
	}
	
	public void setIdFuncionario(int id) {
		this.idFuncionario = id;
	}
	
	public int getIdFuncionario() {
		return this.idFuncionario;
	}
}
