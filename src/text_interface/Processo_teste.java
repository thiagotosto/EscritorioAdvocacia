package text_interface;

import DAO.ProcessoDAO;
import modelo.Processo;

public class Processo_teste {
	
	public static void main(String[] args) {
		//instanciando ProcessoDAO e conectando no banco
		ProcessoDAO pdao = new ProcessoDAO();
		pdao.conexaoBD();		
		
		/*
		//fazendo consulta por numero
		Processo testeProcesso = pdao.consultaPorNumero(126);
		
		//imprimindo caminho trazidos
		for (int i = 0; i < testeProcesso.getDocumentos().length; i++) {
			System.out.println(testeProcesso.getDocumentos()[i]);
		}
		
		//instanciando Processo novo para inserir
		Processo processo = new Processo();
		
		//populando novo processo de teste
		processo.setDescricao("processo4 teste");
		processo.setNumero(126);
		
		//inserindo processo novo
		pdao.inserir(processo);
		
		//resgatando o mesmo usuario do banco devido ao Id
		processo = pdao.consultaPorNumero(126);
		
		//cadastrando caminhos novos
		String[][] caminhos_teste = {{"/teste/caminhos6","pdf6"}, {"/teste/caminhos7", "doc7"}};
		
		pdao.inserirDocumentos(processo, caminhos_teste);*/
		
		/*
		Processo processo = pdao.consultaPorNumero(125);
		
		processo.setDescricao("processo3 teste2");
		
		String[][] teste = processo.getDocumentos();
		teste[0][2] = "pdf4";
		
		processo.setDocumentos(teste);
		
		System.out.println(processo.getId());
		System.out.println(processo.getNumero());
		for (int i = 0; i < processo.getDocumentos().length; i++) {
			System.out.println(processo.getDocumentos()[i][1] + " : " + processo.getDocumentos()[i][2]);
		}
		pdao.atualizar(processo);*/
	}
}
