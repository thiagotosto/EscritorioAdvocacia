package text_interface;

import modelo.Advogado;
import modelo.Processo;
import modelo.Cliente;
import modelo.Tarefa;
import DAO.AdvogadoDAO;
import DAO.ProcessoDAO;
import DAO.ClienteDAO;

import java.util.Scanner;

public class ProcessoMenu {

	public static void mostraProcessos(Scanner scan, Advogado perfil) {
		//instanciando ProcessoDAO e conectando no banco
		ProcessoDAO pdao = new ProcessoDAO();
		pdao.conexaoBD();
		
		//carregando todos os processos
		Processo[] processos = pdao.consultaTodos(perfil);
		
		//printando header
		System.out.printf("\n%-30s%-15s\n","Numero","Descrição");
		//pulando linha
		System.out.println();
		
		//printando processos formatados
		for (int i = 0; i < processos.length; i++) {
			//dados
			System.out.printf("\n%-30s%-15s\n",processos[i].getNumero(),processos[i].getDescricao());
		}
		
	}
	
	public static void cadastraProcesso(Scanner scan, Advogado perfil) {
		//instanciando ProcessoDAO e conectando no banco
		ProcessoDAO pdao = new ProcessoDAO();
		pdao.conexaoBD();
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();
		
		//instanciando Processo novo para inserir
		Processo processo = new Processo();
		
		//pulando linha
		System.out.println();
		
		//populando novo processo de teste
		System.out.print("Descrição: ");
		processo.setDescricao(scan.nextLine());
		
		System.out.print("Numero do processo: ");
		processo.setNumero(Integer.parseInt(scan.nextLine()));
		
		//parseando e persistindo clientes
		System.out.println("Digite os CPFs dos clientes do processo separados por ',': ");
		String[] clientescpf = scan.nextLine().split(",");
		Cliente[] clientes = new Cliente[clientescpf.length];
		
		for (int i = 0; i < clientescpf.length; i++) {
			clientes[i] = cdao.consultaPorCPF(clientescpf[i]);
		}
		
		//inserindo processo novo
		pdao.inserir(processo, perfil);
		pdao.inserirClientes(pdao.consultaPorNumero(processo.getNumero()), clientes);
	}
	
	
	public static void deletaProcesso(Scanner scan) {
		//instanciando ProcessoDAO e conectando no banco
		ProcessoDAO pdao = new ProcessoDAO();
		pdao.conexaoBD();
		
		System.out.print("Digite o número do processo: ");
		Processo processo = pdao.consultaPorNumero(Integer.parseInt(scan.nextLine()));
		
		pdao.excluir(processo);
	}
	
	public static void displayMenu(Scanner scan, Advogado perfil) {
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD();
		
		String[] opcoes = {"Consultar processos", "Cadastrar processo", "Deletar processo", "Voltar"};
		
		//loop principal
		while(true){
			
			//cabeçalho
			System.out.println("\n\nESCOLHA UMA OPÇÃO ABAIXO:\n");
			
			//listando opções
			for (int i = 0; i < opcoes.length; i++){
				System.out.println(Integer.toString(i+1) + ". " + opcoes[i]);
			}
			
			//entrada de opção do usuário
			System.out.print("-> ");
			int opcao = Integer.parseInt(scan.nextLine());
			
			if (opcoes[opcao - 1] == "Consultar processos") {
				ProcessoMenu.mostraProcessos(scan, perfil);
			} else if (opcoes[opcao - 1] == "Cadastrar processo") {
				ClienteMenu.mostrarClientes();
				ProcessoMenu.cadastraProcesso(scan, perfil);
			} else if (opcoes[opcao - 1] == "Deletar processo") {
				ProcessoMenu.deletaProcesso(scan);
			} else if (opcoes[opcao - 1] == "Voltar") {
				break;
			}
		}
	}
}
