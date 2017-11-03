package API;

import modelo.Advogado;

import modelo.Processo;
import DAO.ClienteDAO;
import DAO.ProcessoDAO;

public class ProcessoAPI {
	public static Processo[] mostraProcessos(Advogado perfil) {
		//instanciando ProcessoDAO e conectando no banco
		ProcessoDAO pdao = new ProcessoDAO();
		pdao.conexaoBD();
		
		//carregando todos os processos
		Processo[] processos = pdao.consultaTodos(perfil);
		
		for (int i = 0; i < processos.length; i++) {
			processos[i] = pdao.consultaPorNumero(processos[i].getNumero());
		}
		
		return processos;
	}
	
	public static void cadastraProcesso(Advogado advogado, Processo processo) {
		//instanciando ProcessoDAO e conectando no banco
		ProcessoDAO pdao = new ProcessoDAO();
		pdao.conexaoBD();
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();
		
		//inserindo processo novo
		pdao.inserir(processo, advogado);
		pdao.inserirClientes(pdao.consultaPorNumero(processo.getNumero()), processo.getClientes());
	}
	
	
	public static void deletaProcesso(String numero) {
		//instanciando ProcessoDAO e conectando no banco
		ProcessoDAO pdao = new ProcessoDAO();
		pdao.conexaoBD();
		
		Processo processo = pdao.consultaPorNumero(Integer.parseInt(numero));
		
		pdao.excluir(processo);
	}
/*	
	public static void displayMenu(Advogado perfil) {
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
	}*/
}
