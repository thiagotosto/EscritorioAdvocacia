package text_interface;

import java.util.Scanner;

import modelo.Secretaria;
import modelo.Secretaria;
import DAO.SecretariaDAO;
import DAO.SecretariaDAO;

public class SecretariaMenu {
	
	public static void verTodos() {
		//instanciando SecretariaDAO e fazendo conexão com o banco
		SecretariaDAO sdao = new SecretariaDAO(); 
		sdao.conexaoBD();
		Secretaria[] secretarias = sdao.consultaTodos(); //retornando Secretarias da consulta
		
		//printando tabela de saída
		System.out.printf("\n%-30s%-15s\n", "Nome", "Login"); //print header
		
		for (int i = 0; i < secretarias.length; i++) {	
			System.out.printf("%-30s%-15s\n", secretarias[i].getNome(),
							  secretarias[i].getLogin());
		}
	}
	
	public static void admitirSecretaria(Scanner scan) {

		//instanciando SecretariaDAO
		SecretariaDAO sdao = new SecretariaDAO();
		sdao.conexaoBD(); // conectando com o banco
		
		//instanciando e populando Secretaria novo
		Secretaria novos = new Secretaria();
		
		//pedindo Nome
		System.out.print("Nome: ");
		novos.setNome(scan.nextLine());
		
		//pedindo Matrícula
		System.out.print("Matricula: ");
		novos.setMatricula(scan.nextLine());
		
		//pedindo Login
		System.out.print("Login: ");
		novos.setLogin(scan.nextLine());
		
		//pedindo Senha
		System.out.print("Senha: ");
		novos.setSenha(scan.nextLine());
		
		//persistindo no banco
		sdao.inserir(novos);
	}
}
