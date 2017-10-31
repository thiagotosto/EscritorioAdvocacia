package API;

import modelo.Secretaria;
import DAO.SecretariaDAO;

public class SecretariaAPI {
	public static Secretaria[] verTodos() {
		//instanciando SecretariaDAO e fazendo conexão com o banco
		SecretariaDAO sdao = new SecretariaDAO(); 
		sdao.conexaoBD();
		Secretaria[] secretarias = sdao.consultaTodos(); //retornando Secretarias da consulta
		
		return secretarias;
	}
	
	public static void admitirSecretaria(Secretaria secretarianova) {

		//instanciando SecretariaDAO
		SecretariaDAO sdao = new SecretariaDAO();
		sdao.conexaoBD(); // conectando com o banco
				
		//persistindo no banco
		sdao.inserir(secretarianova);
	}
}
