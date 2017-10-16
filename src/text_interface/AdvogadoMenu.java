package text_interface;

import java.util.Scanner;

import DAO.AdvogadoDAO;
//import DAO.AdvogadoDAO;
import modelo.Advogado;
import modelo.Funcionario;


//import java.util.Scanner;

public class AdvogadoMenu {
	
	public static void verPerfil(Advogado perfil) {
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
		System.out.print(perfil.getMatricula());
		
		//pulando linha
		System.out.println();
		
		//imprimindo oab
		System.out.print("Oab: ");
		System.out.print(perfil.getOab());
	}

	/*
	public void editarPerfil(Scanner scan){
		
		//pedindo usuário a ser atualizado
		System.out.print("Qual usuário deseja atualizar: ");
		String login_escolhido = scan.nextLine();
		
		//instanciando AdvogadoDAO
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD(); //conectando com o banco
		
		//select no banco do objeto a ser atualizado
		Advogado usuario = adao.consultaPorLogin(login_escolhido);
		System.out.println(usuario.getLogin()+", "+usuario.getId()); 
		
		String[] opcoes = {"Senha"};
		
		//menu de opções
		for (int i = 0; i < opcoes.length; i++){
			System.out.println(Integer.toString(i+1) + ". " + opcoes[i]);
		}
		
		System.out.print("Escolha o número da opção que deseja atualizar: ");
		int opcao = Integer.parseInt(scan.nextLine());
		
		//ação da escolha
		if (opcao == 1) {	//atualizando Nome
			System.out.print("Digite a novs Senha ");
			usuario.setSenha(scan.nextLine());
		}
		
		//persistindo em banco
		adao.atualizar(usuario);
	}*/

		
}

