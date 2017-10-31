package API;

import modelo.MotoBoy;
import DAO.MotoBoyDAO;

public class MotoBoyAPI {

	public static MotoBoy[] verTodos() {
		//instanciando MotoBoyDAO e fazendo conexão com o banco
		MotoBoyDAO mdao = new MotoBoyDAO(); 
		mdao.conexaoBD();
		MotoBoy[] motoboys = mdao.consultaTodos(); //retornando MotoBoys da consulta
		
		return motoboys;
	}
	
	public static void admitirMotoBoy(MotoBoy motoboynovo) {

		//instanciando MotoBoyDAO
		MotoBoyDAO mdao = new MotoBoyDAO();
		mdao.conexaoBD(); // conectando com o banco
				
		//persistindo no banco
		mdao.inserir(motoboynovo);
	}
}
