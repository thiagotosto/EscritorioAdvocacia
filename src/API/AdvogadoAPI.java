package API;

import DAO.AdvogadoDAO;
import modelo.Advogado;

public class AdvogadoAPI {
	
	//mostra todos os advogados
	public static Advogado[] verTodos(){
		//instanciando AdvogadoDAO e fazendo conexão com o banco
		AdvogadoDAO adao = new AdvogadoDAO(); 
		adao.conexaoBD();
		Advogado[] advogados = adao.consultaTodos(); //retornando advogados da consulta
		
		return advogados;
	}
	
	//admite um advogado
	public static void admitirAdvogado(Advogado advogadonovo) {
		
		//instanciando AdvogadoDAO
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD(); // conectando com o banco
		
		//persistindo no banco
		adao.inserir(advogadonovo);
	}	
	
	
	
}
