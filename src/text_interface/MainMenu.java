package text_interface;

import java.util.Scanner;

import utils.Utils;

//import modelo.Advogado;
import modelo.Funcionario;
import modelo.Tarefa;
import DAO.*;
//import modelo.*;
import text_interface.*;

public class MainMenu {
	
	private Funcionario perfil;
	
	public MainMenu(Scanner scan) {
		this.login(scan);
	}
	
	public Funcionario getPerfil() {
		return this.perfil;
	}
	
	//login
	public boolean login(Scanner scan) {
		
		//intanciando FuncionarioDAO e fazendo conexão com o banco
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); //conectando no db
		
		
		//numerod e tentativas
		int tentativas = 0;
		
		while (true) {	
			//escanenando usuario e senha
			System.out.print("Usuario: ");
			String login = scan.nextLine();
			System.out.print("Senha: ");
			String senha = scan.nextLine();
		
			this.perfil = fdao.consultaPorLogin(login); //retornando usuario desejado
			
			//testando se existe esse usuário
			if (this.perfil != null){
				//testando se a senha é a certa
				if (this.perfil.getSenha().equals(senha)){
					return true;
				} else if (tentativas == 3) {
					System.out.println("Senha ou usuario incorretos!");
					this.sair(scan);
				} else {
					System.out.println("Senha ou usuario incorretos!");
					tentativas += 1;
				}
			} else if(tentativas < 3) {
				System.out.println("Usuario inexistente");
				tentativas += 1;
			} else {
				this.sair(scan);
			}
		}
	}
	
	//descobre se os privilégios que tem
	public String[] descobrePrivilegios(String usuario){
		
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
		
		return privilegios;
	}
	
	public static void admitirFuncionario(Scanner scan) {
		//pulando linha
		System.out.println();
		
		//vetor de opções de cargos
		String[] cargos = {"Advogado", "MotoBoy", "Secretaria"};
		
		//menu de cargos
		for (int i = 0; i < cargos.length; i++) {
			System.out.println(i+1 + ". " + cargos[i]);
		}
		
		//coletando opção
		System.out.print("-> ");
		int opcao = Integer.parseInt(scan.nextLine());
		
		//executando opção
		if (cargos[opcao - 1] == "Advogado") {
			AdvogadoMenu.admitirAdvogado(scan);
		} else if (cargos[opcao - 1] == "MotoBoy") {
			MotoboyMenu.admitirMotoBoy(scan);
		} else if (cargos[opcao - 1] == "Secretaria") {
			SecretariaMenu.admitirSecretaria(scan);
		}
	}
	
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
		
		
		
	}
	
	//sair
	private void sair(Scanner scan){
		scan.close();
		System.exit(1);
	}
	
	public void displayMainMenu(String[] privilegios, Scanner scan) {
		
		//PREPARAÇÃO
		
		//instanciando funcionario DAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		AdvogadoDAO adao = new AdvogadoDAO();
		//conectando no banco
		fdao.conexaoBD();
		adao.conexaoBD();
		
		//montando opções de cada perfil
		String[] opcoes_funcionario = {"Ver perfil","Consultar tarefas", "Consumir tarefa", "Mudar senha"};
		String[] opcoes_advogado = {"Consultar processos", "Cadastrar processo", "Deletar processo",  "Consultar Cliente", "Cadastrar Cliente"};
		String[] opcoes_gerente = {"Ver funcionários","Ver gerentes", "Promover funcionário", "Rebaixar funcionário", "Admitir funcionário",
				"Demitir funcionário"};
		String[] sair = {"Sair"};
		
		//CONSTRUÇÃO
		
		//opções do perfil
		String[] opcoes_perfil;
		
		//adicionando opções básicas de funcionarios
		opcoes_perfil = opcoes_funcionario;
		
		//adicionando opções de gerente se for gerente
		if (privilegios[0] == "Gerente") {
			opcoes_perfil = Utils.joinArray(opcoes_perfil, opcoes_gerente);
		}
		
		//adicionando opçóes de advogado se for advogado
		if (privilegios[1] == "Advogado") {
			opcoes_perfil = Utils.joinArray(opcoes_perfil, opcoes_advogado);
		}
		
		//adicionando a opçao sair
		opcoes_perfil = Utils.joinArray(opcoes_perfil, sair);
		
		
		//EXECUÇÃO
		
		//instanciando menus
		//FuncionarioMenu fm = new FuncionarioMenu();
		//AdvogadoMenu am = new AdvogadoMenu();
		
		//loop principal
		while (true) {	
			//cabeçalho
			System.out.println("\n\nESCOLHA UMA OPÇÃO ABAIXO:\n");
			
			//listando opções
			for (int i = 0; i < opcoes_perfil.length; i++){
				System.out.println(Integer.toString(i+1) + ". " + opcoes_perfil[i]);
			}
			
			//entrada de opção do usuário
			System.out.print("-> ");
			int opcao = Integer.parseInt(scan.nextLine());
			
			//navegando pela opção
			if (opcoes_perfil[opcao - 1] == "Ver perfil") {
				if (privilegios[1] == "Advogado") {
					AdvogadoMenu.verPerfil(adao.consultaPorLogin(this.perfil.getLogin()));
				} else {
					FuncionarioMenu.verPerfil(this.perfil);
				}
			} else if (opcoes_perfil[opcao - 1] == "Consultar tarefas") {
				TarefaMenu.consultarTarefas(scan, this.perfil);
			} else if (opcoes_perfil[opcao - 1] == "Consumir tarefa") {
				Tarefa[] tarefas = TarefaMenu.consultarTarefas(scan, this.perfil);
				TarefaMenu.consumirTarefa(scan, tarefas);
			} else if (opcoes_perfil[opcao - 1] == "Mudar senha") {
				FuncionarioMenu.mudarSenha(scan, this.perfil);
			} else if (opcoes_perfil[opcao - 1] == "Consultar processos") {
				ProcessoMenu.mostraProcessos(scan, adao.consultaPorLogin(perfil.getLogin()));
			} else if (opcoes_perfil[opcao - 1] == "Cadastrar processo") {
				ClienteMenu.mostrarClientes();
				ProcessoMenu.cadastraProcesso(scan, adao.consultaPorLogin(perfil.getLogin()));
			} else if (opcoes_perfil[opcao - 1] == "Deletar processo") {
				ProcessoMenu.deletaProcesso(scan);
			} else if (opcoes_perfil[opcao - 1] == "Consultar Cliente") {
				ClienteMenu.consultarClientes(scan, adao.consultaPorLogin(perfil.getLogin()));
			} else if (opcoes_perfil[opcao - 1] == "Cadastrar Cliente") {
				ClienteMenu.cadastrarCliente(scan);
			} else if (opcoes_perfil[opcao - 1] == "Promover funcionário") {
				GerenteMenu.promoverGerente(scan);
			} else if (opcoes_perfil[opcao - 1] == "Ver funcionários") {
				MainMenu.mostraFuncionarios();
			} else if (opcoes_perfil[opcao - 1] == "Ver gerentes") {
				GerenteMenu.mostraTodosGerentes(scan);
			} else if (opcoes_perfil[opcao - 1] == "Rebaixar funcionário") {
				GerenteMenu.despromoverGerente(scan);
			} else if (opcoes_perfil[opcao - 1] == "Admitir funcionário") {
				MainMenu.admitirFuncionario(scan);
			} else if (opcoes_perfil[opcao - 1] == "Demitir funcionário") {
				FuncionarioMenu.demitir(scan);
			} else if (opcoes_perfil[opcao - 1] == "Sair") {
				this.sair(scan);
			}
		}
	}
}
