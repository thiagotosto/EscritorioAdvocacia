package text_interface;

import DAO.*;
import modelo.*;

public class Teste_atualizar {

	public static void main(String[] args) {
		String login_escolhido = "ttosto";
		
		//instanciando FuncionarioDAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); //conectando com o banco
		
		//select no banco do objeto a ser atualizado
		Funcionario usuario = fdao.consultaPorLogin(login_escolhido);
		//atualizando senha
		usuario.setSenha("ttostosenha");
		//persistindo em banco
		fdao.atualizar(usuario);
	}
	
}
