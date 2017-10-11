package Text_interface;

import modelo.Funcionario;
import DAO.FuncionarioDAO;

import java.util.Scanner; 


public class Funcionario_teste {
	 
	//valida login do usuario
	private static boolean login(Scanner scan) {
		
		//scanenando usuario e senha
		System.out.print("Usuario: ");
		String login = scan.next();
		System.out.print("Senha: ");
		String senha = scan.next();		
	
		//intanciando FuncionarioDAO e fazendo conexão com o banco
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); //conectando no db
		Funcionario usuario = fdao.consultaPorLogin(login); //retornando usuario desejado
		
		if (usuario.getSenha().equals(senha)){
			return true;
		} else {
			return false;
		}
	}
	
	//mostra todos os funcionarios
	private static void mostrarTodos() {
		//instanciando FuncionarioDAO e fazendo conexão com o banco
		FuncionarioDAO fdao = new FuncionarioDAO(); 
		fdao.conexaoBD();
		Funcionario[] funcionarios = fdao.consultaTodos(); //retornando funcionarios da consulta
		
		//printando tabela de saída
		System.out.printf("\n%-15s%-30s%-15s\n","Id","Nome","Matrícula"); //print header
		
		for (int i = 0; i < funcionarios.length; i++) {	
			System.out.printf("%-15s%-30s%-15s\n", funcionarios[i].getId(), funcionarios[i].getNome(),
							  funcionarios[i].getMatricula());
		}
	}
	
	//insere um novo funcionario
	private static void inserir(Scanner scan) {
		//instanciando FuncionarioDAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); // conectando com o banco
		
		//instanciando e populando funcionario novo
		Funcionario novof = new Funcionario();
		
		//pedindo Nome
		System.out.print("Nome: ");
		novof.setNome(scan.next());
		
		//pedindo Matrícula
		System.out.print("Matricula: ");
		novof.setMatricula(scan.next());
		
		//pedindo Login
		System.out.print("Login: ");
		novof.setLogin(scan.next());
		
		//pedindo Senha
		System.out.print("Senha: ");
		novof.setSenha(scan.next());
		
		//persistindo no banco
		fdao.inserir(novof);
	}
	
	//atualiza um funcionario em um atributo escolhido
	private static void atualizar(Scanner scan) {
		//pedindo usuário a ser atualizado
		System.out.print("Qual usuário deseja atualizar: ");
		String login_escolhido = scan.next();
		
		//instanciando FuncionarioDAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); //conectando com o banco
		
		//select no banco do objeto a ser atualizado
		Funcionario usuario = fdao.consultaPorLogin(login_escolhido);
		
		String[] opcoes = {"Nome", "Matrícula", "Senha"};
		
		//menu de opções
		for (int i = 0; i < opcoes.length; i++){
			System.out.println(Integer.toString(i+1) + ". " + opcoes[i]);
		}
		
		System.out.print("Escolha o número da opção que deseja atualizar: ");
		int opcao = Integer.parseInt(scan.next());
		
		//ação da escolha
		if (opcao == 1) {	//atualizando Nome
			System.out.print("Digite o novo nome: ");
			usuario.setNome(scan.next());
		} else if (opcao == 2) {	//atualizando matrícula
			System.out.print("Digite a nova matrícula: ");
			usuario.setMatricula(scan.next());
		} else if (opcao == 3) {	//atualizando senha
			System.out.print("Digite a nova senha: ");
			usuario.setSenha(scan.next());
		}
		
		//persistindo em banco
		fdao.atualizar(usuario);
	}
	
	//exclui um funcionario
	private static void excluir(Scanner scan) {
		//instanciando FuncionarioDAO e conectando no banco
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD();
		
		//coletando usuário a ser excluido
		System.out.print("Digite o usuário que deseja excluir: ");
		String usuario_excluido = scan.next();
		Funcionario usuario = fdao.consultaPorLogin(usuario_excluido);
		
		//excluindo usuario
		fdao.excluir(usuario);
	}
	
	private static void sair(Scanner scan){
		scan.close();
		System.exit(1);
	}
	
	//principal
	public static void main(String[] args){
		//instanciando scanner global
		Scanner scan = new Scanner(System.in);
		
		//populando lista de opçoes do menu principal
		String[] opcoes = {"Mostrar funcionários", "Adicionar funcionário", "Editar funcionários", "Excluir funcionários", "Sair"};
		
		//criando teste para login
		boolean login_tentativa = false;
		
		//loop da validação da senha
		while(!login_tentativa){
			
			//validando login
			if(login_tentativa = Funcionario_teste.login(scan)) {
				
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
					int opcao = Integer.parseInt(scan.next());
					
					//navegando de acordo com a opção
					if (opcao == 1) {
						Funcionario_teste.mostrarTodos();
					} else if (opcao == 2) {
						Funcionario_teste.inserir(scan);
					} else if (opcao == 3) {
						Funcionario_teste.atualizar(scan);
					} else if (opcao == 4) {
						Funcionario_teste.excluir(scan);
					} else if (opcao == 5){
						Funcionario_teste.sair(scan);
					}
				}
				
			} else {
				System.out.println("Senha incorreta!");
			}
		}
	}
}
