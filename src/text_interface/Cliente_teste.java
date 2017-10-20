package text_interface;

import DAO.ClienteDAO;
import modelo.Cliente;


public class Cliente_teste {

	public static void main(String[] args){
		//instanciando ClienteDAO e conectando no banco
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();
		
		/*
		//inserindo cliente novo
		Cliente cnovo = new Cliente();
		cnovo.setNome("Pariz");
		cnovo.setCpf("78954632145");
		cdao.inserir(cnovo);
		*/
		
		//resgatando cliente inserido
		Cliente cresgate = cdao.consultaPorNome("Pariz");
		System.out.println("Nome: " + cresgate.getNome());
		System.out.println("Cpf: " + cresgate.getCpf());
		
		//mudando nome do usuario
		cresgate.setCpf("12345678912");
		cdao.atualizar(cresgate);
		
		//coletando usuario de novo
		cresgate = cdao.consultaPorNome("Pariz");
		System.out.println("Nome: " + cresgate.getNome());
		System.out.println("Cpf: " + cresgate.getCpf());
		
		//deletando usuario
		cdao.excluir(cresgate);
		
	}
	
}
