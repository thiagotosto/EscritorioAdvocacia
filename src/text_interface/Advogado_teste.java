package text_interface;

import DAO.AdvogadoDAO;
import modelo.Advogado;


import java.util.Scanner;

public class Advogado_teste {

	private static boolean login(Scanner scan) {
		
		//scanenando usuario e senha
		System.out.print("Usuario: ");
		String login = scan.nextLine();
		System.out.print("Senha: ");
		String senha = scan.nextLine();		
	
		//intanciando AdvogadoDAO e fazendo conexão com o banco
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD(); //conectando no db
		Advogado usuario = adao.consultaPorLogin(login); //retornando usuario desejado
		
		if (usuario != null){
			if (usuario.getSenha().equals(senha)){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	//mostra todods os advogados
	private static void mostraTodos(){
		//instanciando AdvogadoDAO e fazendo conexão com o banco
		AdvogadoDAO adao = new AdvogadoDAO(); 
		adao.conexaoBD();
		Advogado[] advogados = adao.consultaTodos(); //retornando advogados da consulta
		
		//printando tabela de saída
		System.out.printf("\n%-15s%-30s%-15s%-30s\n","Id","Nome","Matrícula", "Oab"); //print header
		
		for (int i = 0; i < advogados.length; i++) {	
			System.out.printf("%-15s%-30s%-15s%-30s\n", advogados[i].getId(), advogados[i].getNome(),
							  advogados[i].getMatricula(), advogados[i].getOab());
		}
	}
	
	//insere um advogado
	private static void inserir(Scanner scan){
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
	
	//atualiza um advogado
	private static void atualizar(Scanner scan){
		//pedindo usuário a ser atualizado
		System.out.print("Qual usuário deseja atualizar: ");
		String login_escolhido = scan.nextLine();
		
		//instanciando AdvogadoDAO
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD(); //conectando com o banco
		
		//select no banco do objeto a ser atualizado
		Advogado usuario = adao.consultaPorLogin(login_escolhido);
		System.out.println(usuario.getLogin()+", "+usuario.getId()); 
		
		String[] opcoes = {"Nome", "Matrícula", "Senha", "Oab"};
		
		//menu de opções
		for (int i = 0; i < opcoes.length; i++){
			System.out.println(Integer.toString(i+1) + ". " + opcoes[i]);
		}
		
		System.out.print("Escolha o número da opção que deseja atualizar: ");
		int opcao = Integer.parseInt(scan.nextLine());
		
		//ação da escolha
		if (opcao == 1) {	//atualizando Nome
			System.out.print("Digite o novo nome: ");
			usuario.setNome(scan.nextLine());
		} else if (opcao == 2) {	//atualizando matrícula
			System.out.print("Digite a nova matrícula: ");
			usuario.setMatricula(scan.nextLine());
		} else if (opcao == 3) {	//atualizando senha
			System.out.print("Digite a nova senha: ");
			usuario.setSenha(scan.nextLine());
		} else if (opcao == 4) {	//atualizando senha
			System.out.print("Digite a nova senha: ");
			usuario.setOab(scan.nextLine());
		}
		
		//persistindo em banco
		adao.atualizar(usuario);
	}

	//exclui um advogado
	private static void excluir(Scanner scan) {
		//instanciando AdvogadoDAO e conectando no banco
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD();
		
		//coletando usuário a ser excluido
		System.out.print("Digite o usuário que deseja excluir: ");
		String usuario_excluido = scan.nextLine();
		Advogado usuario = adao.consultaPorLogin(usuario_excluido);
		
		//excluindo usuario
		adao.excluir(usuario);
	}
	
	//sai do menu
	private static void sair(Scanner scan){
		scan.close();
		System.exit(1);
	}
	
	//principal
	public static void main(String[] args) {
		//instanciando scanner global
		Scanner scan = new Scanner(System.in);
		
		//populando lista de opçoes do menu principal
		String[] opcoes = {"Mostrar todos", "Adicionar advogado", "Editar advogado", "Excluir advogado", "Sair"};
		
		//criando teste para login
		boolean login_tentativa = false;
		
		//loop da validação da senha
		while(!login_tentativa){
			
			//validando login
			if(login_tentativa = Advogado_teste.login(scan)) {
				
				//loop principal
				while (true) {	
					//cabeçalho
					System.out.println("\nESCOLHA UMA OPÇÃO ABAIXO:\n");
					
					//listando opções
					for (int i = 0; i < opcoes.length; i++){
						System.out.println(Integer.toString(i+1) + ". " + opcoes[i]);
					}
					
					//entrada de opção do usuário
					System.out.print("-> ");
					int opcao = Integer.parseInt(scan.nextLine());
					
					//navegando de acordo com a opção
					if (opcao == 1) {
						Advogado_teste.mostraTodos();
					} else if (opcao == 2) {
						Advogado_teste.inserir(scan);
					} else if (opcao == 3) {
						Advogado_teste.atualizar(scan);
					} else if (opcao == 4) {
						Advogado_teste.excluir(scan);
					} else if (opcao == 5){
						Advogado_teste.sair(scan);
					}
				}
				
			} else {
				System.out.println("Usuário ou Senha incorretos!");
			}
		}
	}
}
