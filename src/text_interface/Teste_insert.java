package text_interface;

import DAO.*;
import modelo.*;

public class Teste_insert {

	public static void main(String[] args){
		//instanciando FuncionarioDAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); // conectando com o banco
		
		//instanciando e populando funcionario novo
		Funcionario novof = new Funcionario();
		novof.setNome("Tosto");
		novof.setMatricula("2345687");
		novof.setLogin("tosto");
		novof.setSenha("tostosenha");
		
		//persistindo no banco
		fdao.inserir(novof);
	}	
}
