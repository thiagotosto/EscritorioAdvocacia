package text_interface;

import modelo.Tarefa;
import modelo.Funcionario;
import DAO.FuncionarioDAO;
import DAO.TarefaDAO;

import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TarefaMenu {
	public static Tarefa[] consultarTarefas(Scanner scan, Funcionario perfil) {
		//instanciando TarefaDAO e conectando no banco
		TarefaDAO tdao = new TarefaDAO();
		tdao.conexaoBD();
		
		//tras as tarefas do funcionario em questão		
		Tarefa[] tarefas = tdao.consultaTodas(perfil);
		
		//Printando header
		System.out.printf("\n#\t%-30s%-30s%-30s\n", "Descrição", "Data de Expedição", "Data de Expiração");
		
		//printando tarefas
		for (int i = 0; i < tarefas.length; i++) {
			try {
				Date dateExpdMeta = new SimpleDateFormat("yyyy-MM-dd").parse(tarefas[i].getExpdData());
				Date dateExprMeta = new SimpleDateFormat("yyyy-MM-dd").parse(tarefas[i].getExprData());
				String dateExpd = new SimpleDateFormat("dd-MM-yyyy").format(dateExpdMeta);
				String dateExpr = new SimpleDateFormat("dd-MM-yyyy").format(dateExprMeta);
				System.out.printf("\n%d\t%-30s%-30s%-30s\n", i+1, tarefas[i].getDescricao(), dateExpd, dateExpr);
			} catch (ParseException e) {
				System.out.println("Parse erro: "+ e);
			}
		}
		return tarefas;
	}
	
	public static void consumirTarefa(Scanner scan, Tarefa[] tarefas) {
		//instanciando TarefaDAO e conectando no banco
		TarefaDAO tdao = new TarefaDAO();
		tdao.conexaoBD();
		
		System.out.println("\nQual tarefa deseja consumir: ");
		Tarefa tarefa = tdao.consultaPorId(tarefas[Integer.parseInt(scan.nextLine()) - 1].getId());
		
		tdao.excluir(tarefa);
	}
	
	public static void cadastrarTarefa(Scanner scan, Funcionario perfil) {
		//instanciando TarefaDAO e conectando no banco
		TarefaDAO tdao = new TarefaDAO();
		tdao.conexaoBD();
		
		//instanciando tarefa nova
		Tarefa tarefanova = new Tarefa();
		
		//definindo dono da tarefa
		tarefanova.setOwner(perfil);
		
		//colhendo descrição
		System.out.println("Qual a descrição da tarefa: ");
		tarefanova.setDescricao(scan.nextLine());
		
		//colhendo data de expiração
		System.out.println("Qual a data de expiração(yyyy-MM-dd): ");
		tarefanova.setExprData(scan.nextLine());
		
		//criando objeto data com data atual
		DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		Date date = new Date();
		
		//setando no objeto a data de expedição
		tarefanova.setExpdData(dateFormat.format(date));
		
		//persistindo no banco
		tdao.inserir(tarefanova);
	}
}
