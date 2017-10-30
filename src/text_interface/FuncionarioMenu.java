package text_interface;

import java.util.Scanner;

import modelo.Funcionario;
import DAO.FuncionarioDAO;

//import java.util.Scanner; 


public class FuncionarioMenu {
	 
	//ver perfil do atual usuário
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
	
	public static void mudarSenha(Scanner scan, Funcionario perfil) {
		//instanciando funciionario DAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		
		//inicializando tentativas
		int tentativas = 0;
		
		//loop de tentativas
		while (tentativas < 3) {
			
			//coletando senha nova do usuário
			System.out.print("Digite a nova senha: ");
			String senha1 = scan.nextLine();
			System.out.print("Digite a senha novamente: ");
			String senha2 = scan.nextLine();
			
			//testando se senha bate
			if (senha1.equals(senha2)) {
				perfil.setSenha(senha1);
				
				fdao.atualizar(perfil);
				break;
			} else {
				System.out.println("Senhas não batem!");
				tentativas += 1;
			}
		}
	}
	
	public static void demitir(Scanner scan) {
		//instanciando FuncionarioDAO e conectando no banco
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD();
		
		//coletando usuário a ser excluido
		System.out.print("Digite o usuário do funcionario que deseja demitir: ");
		String usuario_excluido = scan.nextLine();
		Funcionario usuario = fdao.consultaPorLogin(usuario_excluido);
		
		if (usuario != null) {
			//excluindo usuario
			fdao.excluir(usuario);
		} else {
			System.out.println("Este funcionário não consta no registro");
		}
	}
}
