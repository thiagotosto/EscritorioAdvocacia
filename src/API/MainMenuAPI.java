package API;

//import modelo.Advogado;
import modelo.Funcionario;

import DAO.*;
//import modelo.*;


public class MainMenuAPI {

	private Funcionario perfil;
	private String[] privilegios;
	
	public Funcionario getPerfil() {
		return this.perfil;
	}
		
	//login
	public boolean login(String login, String senha) {
		
		//intanciando FuncionarioDAO e fazendo conexão com o banco
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); //conectando no db
		
		this.perfil = fdao.consultaPorLogin(login); //retornando usuario desejado
		
		//testando se existe esse usuário
		if (this.perfil != null){
			//testando se a senha é a certa
			if (this.perfil.getSenha().equals(senha)){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	//descobre se os privilégios que tem
	public void descobrePrivilegios(String usuario){
		
		String[] privilegios = new String[3];
		
		//instanciando DAOs
		GerenteDAO gdao = new GerenteDAO();
		AdvogadoDAO adao = new AdvogadoDAO();
		
		//conectando ao banco
		gdao.conexaoBD();
		adao.conexaoBD();
		
		//teste se é gerente
		if (gdao.consultaPorLogin(usuario) != null) {
			privilegios[0] = "Gerente";
		} 
		
		//teste se é advogado
		if (adao.consultaPorLogin(usuario) != null) {
			privilegios[1] = "Advogado";
		}
		
		this.privilegios = privilegios;
	}
	
	/*
	public static void mostraFuncionarios() {
		//pulando linha
		System.out.println();
		
		//listando advogados
		System.out.println("ADVOGADOS---------------------------------------------------\n");
		AdvogadoMenu.verTodos();
		
		//pulando linha
		System.out.println();
		
		System.out.println("SECRETARIAS ------------------------------------------------\n");
		SecretariaMenu.verTodos();
		
		//pulando linha
		System.out.println();
		
		System.out.println("MOTOBOY ----------------------------------------------------\n");
		MotoboyMenu.verTodos();		
		
	}*/
	
}


