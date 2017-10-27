package text_interface;

import DAO.ClienteDAO;
import DAO.AdvogadoDAO;
import modelo.Cliente;
import modelo.Advogado;


public class Cliente_teste {

	public static void main(String[] args){
		//instanciando ClienteDAO e conectando no banco
		ClienteDAO cdao = new ClienteDAO();
		cdao.conexaoBD();
		
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD();		
		
		
		Cliente[] clientes = cdao.consultaTodos(adao.consultaPorLogin("rossi"));
		
		for (int i = 0; i < clientes.length; i++) {
			System.out.println(clientes[i].getNome());
		}
		/*
		//inserindo cliente novo
		Cliente cnovo = new Cliente();
		cnovo.setNome("Pariz");
		cnovo.setCpf("78954632145");
		cdao.inserir(cnovo);
		*/
		/*
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
		*/
	}
	
}
