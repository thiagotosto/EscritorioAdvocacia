package text_interface;

import modelo.Advogado;
import modelo.Cliente;
import modelo.Funcionario;
import DAO.AdvogadoDAO;
import DAO.ClienteDAO;

import java.util.Scanner;


public class ClienteMenu {
	
	public static void mostrarClientes() {
		//instanciando ClienteDAO e conectando no banco
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();
		
		System.out.println("CLIENTES");
		
		//printando header
		System.out.printf("\n%-30s%-15s\n","Nome","Cpf");
		
		//trazendo todos os clientes
		Cliente[] clientes = cdao.consultaTodos();
		
		//printando clientes
		for (int i = 0; i < clientes.length; i++) {
			System.out.printf("\n%-30s%-15s\n",clientes[i].getNome(),clientes[i].getCpf());
		}
		
	}
	
	public static void consultarClientes(Scanner scan, Advogado perfil) {
		//instanciando ClienteDAO e conectando no banco
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();
		
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD();		
		
		//printando header
		System.out.printf("\n%-30s%-15s\n","Nome","Cpf");
		
		//trazendo os clientes vinculados à esse perfil
		Cliente[] clientes = cdao.consultaTodos(adao.consultaPorLogin(perfil.getLogin()));
		
		//printando clientes
		for (int i = 0; i < clientes.length; i++) {
			System.out.printf("\n%-30s%-15s\n",clientes[i].getNome(),clientes[i].getCpf());
		}
	}
	
	public static void cadastrarCliente(Scanner scan) {
		//intanciando ClienteDAO e conectando no banco
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();		
		
		Cliente cnovo = new Cliente();
		
		//Coletando nome de cliente
		System.out.println("Digite o nome do cliente: ");
		cnovo.setNome(scan.nextLine());
		
		//Coletando CPF de cliente
		System.out.println("Digite o CPF do cliente: ");
		cnovo.setCpf(scan.nextLine());
		
		//persistindo no banco
		cdao.inserir(cnovo);
	}
	
	public static void removerCliente(Scanner scan) {
		//instanciando ClienteDAO e conectando no banco
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();
		
		//pulando linha
		System.out.println();
		
		System.out.print("Digite o CPF do cliente que deseja excluir: ");
		Cliente clienteremovido = cdao.consultaPorCPF(scan.nextLine());
		
		cdao.excluir(clienteremovido);
	}
	
	public static void displayMenu(Scanner scan, Funcionario perfil) {
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD();
		
		String[] opcoes = {"Consultar Cliente", "Cadastrar Cliente", "Voltar"};
		
		//loop principal
		while(true) {
		
			//cabeçalho
			System.out.println("\n\nESCOLHA UMA OPÇÃO ABAIXO:\n");
			
			//listando opções
			for (int i = 0; i < opcoes.length; i++){
				System.out.println(Integer.toString(i+1) + ". " + opcoes[i]);
			}
			
			//entrada de opção do usuário
			System.out.print("-> ");
			int opcao = Integer.parseInt(scan.nextLine());
			
			if (opcoes[opcao - 1] == "Consultar Cliente") {
				ClienteMenu.consultarClientes(scan, adao.consultaPorLogin(perfil.getLogin()));
			} else if (opcoes[opcao - 1] == "Cadastrar Cliente") {
				ClienteMenu.cadastrarCliente(scan);
			} else if (opcoes[opcao - 1] == "Voltar") {
				break;
			}
		}
	}
}
