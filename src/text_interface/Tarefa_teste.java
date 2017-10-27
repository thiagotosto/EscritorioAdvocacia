package text_interface;

import DAO.TarefaDAO;
import DAO.FuncionarioDAO;
import modelo.Funcionario;
import modelo.Tarefa;

public class Tarefa_teste {

	public static void main(String[] args){
		//instanciando TarefaDAO e conectando no banco
		TarefaDAO tdao = new TarefaDAO();
		tdao.conexaoBD();
		
		//instanciando FuncionarioDAO e conectando no banco
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD();
		
		//TESTANDO O CONSULTA TODOS --------------------------------------------------------------------------
		
		//buscando funcionario que queremos saber as tarefas no banco
		Funcionario thiagotosto = fdao.consultaPorLogin("ttosto");

		//bucando tarefas no banco
		Tarefa[] tarefas = tdao.consultaTodas(thiagotosto);
		
		
		//printando tarefas trazidas
		for (int i = 0; i < tarefas.length; i++) {
			System.out.println(tarefas[i].getDescricao()+ ": " + tarefas[i].getExprData());
		}
		
		//----------------------------------------------------------------------------------------------------
		
		//TESTANDO O CONSULTA POR ID -------------------------------------------------------------------------
		
		System.out.println(tdao.consultaPorId(6).getDescricao());
		
		//----------------------------------------------------------------------------------------------------
		
		//TESTANDO O EXCLUIR ---------------------------------------------------------------------------------
		
		//tdao.excluir(tarefas[2]);
		
		//----------------------------------------------------------------------------------------------------
		
		//TESTANDO O INSERIR ---------------------------------------------------------------------------------
		
		/*
		//criando nova tarefa e populando
		Tarefa tarefanova = new Tarefa();
		tarefanova.setOwner(thiagotosto);
		tarefanova.setDescricao("tarefa teste 5");
		tarefanova.setExpdData("2017-10-26");
		tarefanova.setExprData("2017-11-29");
		*/
		
		//comitando insert no banco
		//tdao.inserir(tarefanova);
		
		//----------------------------------------------------------------------------------------------------
		
		//TESTANDO O ATUALIZAR -------------------------------------------------------------------------------
		
		//intanciando tarefa a ser atualizada
		Tarefa atualizada = tdao.consultaPorId(8);
		
		//----------------------------------------------------------------------------------------------------
		//setando nova data de expiração
		atualizada.setExprData("2017-12-29");
		
		//comitando atualização
		tdao.atualizar(atualizada);
	}
}
