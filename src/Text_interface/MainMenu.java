package Text_interface;

import java.util.Scanner;

import modelo.Advogado;
import modelo.Funcionario;
import DAO.*;
//import modelo.*;
import Text_interface.*;

public class MainMenu {
	
	private Funcionario perfil;
	
	//login
	public boolean login(Scanner scan) {
		
		//escanenando usuario e senha
		System.out.print("Usuario: ");
		String login = scan.nextLine();
		System.out.print("Senha: ");
		String senha = scan.nextLine();		
	
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
	public String[] descobrePrivilegios(String usuario){
		
		String[] privilegios = {};
		
		//instanciando DAOs
		GerenteDAO gdao = new GerenteDAO();
		AdvogadoDAO adao = new AdvogadoDAO();
		
		//teste se é gerente
		if (gdao.consultaPorLogin(usuario) != null) {
			privilegios[0] = "Gerente";
		} 
		
		//teste se é advogado
		if (adao.consultaPorLogin(usuario) != null) {
			privilegios[1] = "Advogado";
		}
		
		return privilegios;
	}
	
	public void displayMenu() {
	}
}
