package text_interface;

import java.util.Scanner;

import modelo.Advogado;
import modelo.Funcionario;
import modelo.Gerente;
import DAO.AdvogadoDAO;
import DAO.FuncionarioDAO;
import DAO.GerenteDAO;

public class GerenteMenu {
	
	public static void adicionarFuncionario(Scanner scan) {
	
		//instanciando FuncionarioDAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); // conectando com o banco
		
		//instanciando e populando funcionario novo
		Funcionario novof = new Funcionario();
		
		//pedindo Nome
		System.out.print("Nome: ");
		novof.setNome(scan.nextLine());
		
		//pedindo Matrícula
		System.out.print("Matricula: ");
		novof.setMatricula(scan.nextLine());
		
		//pedindo Login
		System.out.print("Login: ");
		novof.setLogin(scan.nextLine());
		
		//pedindo Senha
		System.out.print("Senha: ");
		novof.setSenha(scan.nextLine());
		
		//persistindo no banco
		fdao.inserir(novof);
	}
	
	public static void editarFuncionario(Scanner scan) {
		
		//pedindo usuário a ser atualizado
		System.out.print("Qual usuário deseja atualizar: ");
		String login_escolhido = scan.nextLine();
		
		//instanciando FuncionarioDAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); //conectando com o banco
		
		//select no banco do objeto a ser atualizado
		Funcionario usuario = fdao.consultaPorLogin(login_escolhido);
		
		String[] opcoes = {"Nome", "Matrícula", "Login", "Senha"};
		
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
			System.out.print("Digite a nova login: ");
			usuario.setLogin(scan.nextLine());
		} else if (opcao == 4) {	//atualizando senha
			System.out.print("Digite a nova senha: ");
			usuario.setSenha(scan.nextLine());
		}
		
		//persistindo em banco
		fdao.atualizar(usuario);
	}

	public static void excluirFuncionario(Scanner scan) {
		//instanciando FuncionarioDAO e conectando no banco
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD();
		
		//coletando usuário a ser excluido
		System.out.print("Digite o usuário que deseja excluir: ");
		String usuario_excluido = scan.nextLine();
		Funcionario usuario = fdao.consultaPorLogin(usuario_excluido);
		
		//excluindo usuario
		fdao.excluir(usuario);
	}

	public static void promoverGerente(Scanner scan) {
		//instanciando AdvogadoDAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		GerenteDAO gdao = new GerenteDAO();
		fdao.conexaoBD(); // conectando com o banco
		gdao.conexaoBD(); // conectando com o banco
		
		//pedindo login
		System.out.println("Digite o usuario que deseja promover: ");
		String login = scan.nextLine();
		
		//instanciando e populando Advogado novo
		Funcionario novog = fdao.consultaPorLogin(login); 
		
		if (novog != null) {
			//persistindo no banco
			gdao.promove(novog);
		} else {
			System.out.println("Esse funcionário não consta na base de dados!");
		}
	}
	
	public static void despromoverGerente(Scanner scan) {
		//instanciando GerenteDAO e conectando no banco
		GerenteDAO gdao = new GerenteDAO();
		gdao.conexaoBD();
		
		//coletando usuário a ser excluido
		System.out.print("Digite o gerente que deseja despromover: ");
		String usuario_excluido = scan.nextLine();
		Gerente usuario = gdao.consultaPorLogin(usuario_excluido);
		
		if (usuario != null) {
			//excluindo usuario
			gdao.despromove(usuario);
		} else {
			System.out.println("Esse funcionário não é gerente!");
		}
	}
}
