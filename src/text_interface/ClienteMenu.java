package text_interface;

import modelo.Advogado;
import modelo.Cliente;
import DAO.AdvogadoDAO;
import DAO.ClienteDAO;

import java.util.Scanner;


public class ClienteMenu {
	public static void consultarClientes(Scanner scan, Advogado perfil) {
		//instanciando ClienteDAO e conectando no banco
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();
		
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD();		
		
		//printando header
		System.out.printf("\n%-30s%-15s\n","Nome","Cpf");
		
		Cliente[] clientes = cdao.consultaTodos(adao.consultaPorLogin(perfil.getLogin()));
		
		//printando clientes
		for (int i = 0; i < clientes.length; i++) {
			System.out.printf("\n%-30s%-15s\n",clientes[i].getNome(),clientes[i].getCpf());
		}
	}
}
