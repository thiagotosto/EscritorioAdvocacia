package text_interface;

import java.util.Scanner;

import modelo.Funcionario;
import DAO.FuncionarioDAO;

//import java.util.Scanner; 


public class FuncionarioMenu {
	 
	
	public static void verPerfil(Funcionario perfil) {
		//imprimindo nome
		System.out.print("Nome: ");
		System.out.println(perfil.getNome());
		
		//pulando linhas
		System.out.println();
		System.out.println();
		
		//imprimindo matrícula
		System.out.print("Matrícula: ");
		System.out.print(perfil.getMatricula());
		
		//pulando linha
		System.out.println();
		
		//imprimindo login
		System.out.print("Login: ");
		System.out.print(perfil.getLogin());
	}
	
	public static void consultarAgenda() {
		
	}
	
	public static void mudarSenha(Funcionario perfil, Scanner scan) {
		//instanciando funciionario DAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		
		//coletando senha nova do usuário
		System.out.print("Digite a nova senha: ");
		String senha1 = scan.nextLine();
		System.out.print("Digite a senha novamente: ");
		String senha2 = scan.nextLine();
		
		//testando se senha bate
		if (senha1.equals(senha2)) {
			perfil.setSenha(senha1);
			
			fdao.atualizar(perfil);
		}
	}
}
