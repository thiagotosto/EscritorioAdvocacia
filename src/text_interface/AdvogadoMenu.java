package text_interface;

import java.util.Scanner;

import DAO.AdvogadoDAO;
//import DAO.AdvogadoDAO;
import modelo.Advogado;
import modelo.Funcionario;


//import java.util.Scanner;

public class AdvogadoMenu {
	
	//ve o perfil do atual usuário
	public static void verPerfil(Advogado perfil) {
		
		//pulando uma linha
		System.out.println();
		
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
		
		//pulando linha
		System.out.println();
		
		//imprimindo oab
		System.out.print("Oab: ");
		System.out.print(perfil.getOab());
	}
	
	//mostra todos os advogados
	public static void verTodos(){
		//instanciando AdvogadoDAO e fazendo conexão com o banco
		AdvogadoDAO adao = new AdvogadoDAO(); 
		adao.conexaoBD();
		Advogado[] advogados = adao.consultaTodos(); //retornando advogados da consulta
		
		//printando tabela de saída
		System.out.printf("\n%-30s%-15s%-30s\n", "Nome", "Login", "Oab"); //print header
		
		for (int i = 0; i < advogados.length; i++) {	
			System.out.printf("%-30s%-15s%-30s\n", advogados[i].getNome(),
							  advogados[i].getLogin(), advogados[i].getOab());
		}
	}
	
	//admite um advogado
	public static void admitirAdvogado(Scanner scan) {
		
		//instanciando AdvogadoDAO
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD(); // conectando com o banco
		
		//instanciando e populando Advogado novo
		Advogado novoa = new Advogado();
		
		//pedindo Nome
		System.out.print("Nome: ");
		novoa.setNome(scan.nextLine());
		
		//pedindo Matrícula
		System.out.print("Matricula: ");
		novoa.setMatricula(scan.nextLine());
		
		//pedindo Login
		System.out.print("Login: ");
		novoa.setLogin(scan.nextLine());
		
		//pedindo Senha
		System.out.print("Senha: ");
		novoa.setSenha(scan.nextLine());
		
		//pedindo Oab
		System.out.print("Oab: ");
		novoa.setOab(scan.nextLine());
		
		//persistindo no banco
		adao.inserir(novoa);
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

