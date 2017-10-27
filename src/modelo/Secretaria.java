package modelo;

public class Secretaria extends Funcionario{

	int idSecretaria;
	int idFuncionario;
	
	public void setIdSecretaria(int id){
		this.idSecretaria = id;
	}
	
	public int getIdSecretaria(){
		return this.idSecretaria;
	}
	
	public void setIdFuncionario(int id) {
		this.idFuncionario = id;
	}
	
	public int getIdFuncionario() {
		return this.idFuncionario;
	}
}
