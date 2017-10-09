package modelo;

/**
 * @author Orlando
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Funcionario {

	int Id;
	String Nome;
	String Matricula;
	String login;
	String senha;
	
	
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getMatricula() {
		return Matricula;
	}
	public void setMatricula(String matricula) {
		this.Matricula = matricula;
	}
	/**
	 * @return Returns the Id.
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param Id The Id to set.
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return Returns the nome.
	 */
	public String getNome() {
		return Nome;
	}
	/**
	 * @param nome The nome to set.
	 */
	public void setNome(String nome) {
		Nome = nome;
	}
}
