package text_interface;

import DAO.*;
import modelo.*;

public class Teste_login {
	
	public static void main(String[] args) {
		String usuario_teste = "ttosto";
		String senha = "ttostosenha";
		System.out.println("TESTE DE LOGIN COM O USUÁRIO: "+ usuario_teste);
		System.out.println(); //pulando linha
		
		//instanciando FuncionarioDAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); //conectando no db
		Funcionario usuario = fdao.consultaPorLogin(usuario_teste); //retornando 
		
		if (usuario.getSenha().equals(senha)){
			System.out.println("Senha correta!!!");
		} else {
			System.out.println("Senha incorreta!!!");
		}
		
	}
}
