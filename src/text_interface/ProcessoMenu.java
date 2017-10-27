package text_interface;

import modelo.Advogado;
import modelo.Processo;
import DAO.ProcessoDAO;

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
		
		//instanciando Processo novo para inserir
		Processo processo = new Processo();
		
		//populando novo processo de teste
		System.out.print("Descrição: ");
		processo.setDescricao(scan.nextLine());
		
		System.out.print("Numero do processo: ");
		processo.setNumero(Integer.parseInt(scan.nextLine()));
		
		//inserindo processo novo
		pdao.inserir(processo, perfil);
	}
	
	public static void deletaProcesso(Scanner scan) {
		//instanciando ProcessoDAO e conectando no banco
		ProcessoDAO pdao = new ProcessoDAO();
		pdao.conexaoBD();
		
		System.out.print("Digite o número do processo: ");
		Processo processo = pdao.consultaPorNumero(Integer.parseInt(scan.nextLine()));
		
		pdao.excluir(processo);
	}
}
