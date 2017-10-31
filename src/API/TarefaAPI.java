package API;

//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;


import modelo.Funcionario;
import modelo.Tarefa;
import DAO.TarefaDAO;

public class TarefaAPI {
	public static Tarefa[] consultarTarefas(Funcionario perfil) {
		//instanciando TarefaDAO e conectando no banco
		TarefaDAO tdao = new TarefaDAO();
		tdao.conexaoBD();
		
		//tras as tarefas do funcionario em questão		
		Tarefa[] tarefas = tdao.consultaTodas(perfil);
		
		/*
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
		}*/
		return tarefas;
	}
	
	public static void consumirTarefa(Tarefa tarefa) {
		//instanciando TarefaDAO e conectando no banco
		TarefaDAO tdao = new TarefaDAO();
		tdao.conexaoBD();
		
		tdao.excluir(tarefa);
	}
	
	public static void cadastrarTarefa(Tarefa tarefanova) {
		//instanciando TarefaDAO e conectando no banco
		TarefaDAO tdao = new TarefaDAO();
		tdao.conexaoBD();
			
		//persistindo no banco
		tdao.inserir(tarefanova);
	}
}
