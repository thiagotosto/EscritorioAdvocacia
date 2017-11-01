package API;

//import modelo.Advogado;
import modelo.Funcionario;

import DAO.*;
//import modelo.*;


public class MainMenuAPI {

	private Funcionario perfil;
	private String[] privilegios;
	
	public String[] getPrivilegios(){
		return this.privilegios;
	}
	
	public void setPerfil(Funcionario perfil) {
		this.perfil = perfil;
	}
	
	public Funcionario getPerfil() {
		return this.perfil;
	}
		
	//login
	public String login(String login, String senha) {
		
		//intanciando FuncionarioDAO e fazendo conexão com o banco
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); //conectando no db
		
		this.perfil = fdao.consultaPorLogin(login); //retornando usuario desejado
		
		//testando se existe esse usuário
		if (this.perfil != null){
			//testando se a senha é a certa
			if (this.perfil.getSenha().equals(senha)){
				return "Ok";
			} else {
				return "Senha";
			}
		} else {
			return "Usuario";
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
	
	public static String descobreOcupacao(String usuario) {
		//instanciando DAOS e conectando no banco
		AdvogadoDAO adao = new AdvogadoDAO();
		MotoBoyDAO mdao = new MotoBoyDAO();
		SecretariaDAO sdao = new SecretariaDAO();
		adao.conexaoBD();
		mdao.conexaoBD();
		sdao.conexaoBD();
		
		if (adao.consultaPorLogin(usuario) != null) {
			return "Advogado";
		} else if (mdao.consultaPorLogin(usuario) != null) {
			return "MotoBoy";
		} else if (sdao.consultaPorLogin(usuario) != null) {
			return "Secretaria";
		} else {
			return null;
		}
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


