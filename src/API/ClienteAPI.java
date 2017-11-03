package API;

import modelo.Funcionario;
import modelo.Advogado;
import modelo.Cliente;
import DAO.AdvogadoDAO;
import DAO.ClienteDAO;

public class ClienteAPI {
	public static Cliente[] mostrarClientes() {
		//instanciando ClienteDAO e conectando no banco
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();
		
		//trazendo todos os clientes
		Cliente[] clientes = cdao.consultaTodos();
		return clientes;
		
	}
	
	public static Cliente[] consultarClientes(Advogado perfil) {
		//instanciando ClienteDAO e conectando no banco
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();		
				
		//trazendo os clientes vinculados à esse perfil
		Cliente[] clientes = cdao.consultaTodos(perfil);
		
		return clientes;
	}
	
	public static void cadastrarCliente(Cliente clientenovo) {
		//intanciando ClienteDAO e conectando no banco
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();		
		
		//persistindo no banco
		cdao.inserir(clientenovo);
	}
	
	public static void removerCliente(String cpf) {
		//instanciando ClienteDAO e conectando no banco
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();
		
		Cliente clienteremovido = cdao.consultaPorCPF(cpf);
		
		cdao.excluir(clienteremovido);
	}
	
	
}
