package text_interface;

import modelo.Advogado;
import modelo.Cliente;
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
}
