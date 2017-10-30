package text_interface;

import modelo.MotoBoy;
import modelo.MotoBoy;
import DAO.MotoBoyDAO;
import DAO.MotoBoyDAO;

import java.util.Scanner;

public class MotoboyMenu {
	
	public static void verTodos() {
		//instanciando MotoBoyDAO e fazendo conexão com o banco
		MotoBoyDAO mdao = new MotoBoyDAO(); 
		mdao.conexaoBD();
		MotoBoy[] MotoBoys = mdao.consultaTodos(); //retornando MotoBoys da consulta
		
		//printando tabela de saída
		System.out.printf("\n%-30s%-15s\n", "Nome", "Login"); //print header
		
		for (int i = 0; i < MotoBoys.length; i++) {	
			System.out.printf("%-30s%-15s\n", MotoBoys[i].getNome(),
							  MotoBoys[i].getLogin());
		}
	}
	
	public static void admitirMotoBoy(Scanner scan) {

		//instanciando MotoBoyDAO
		MotoBoyDAO mdao = new MotoBoyDAO();
		mdao.conexaoBD(); // conectando com o banco
		
		//instanciando e populando MotoBoy novo
		MotoBoy novom = new MotoBoy();
		
		//pedindo Nome
		System.out.print("Nome: ");
		novom.setNome(scan.nextLine());
		
		//pedindo Matrícula
		System.out.print("Matricula: ");
		novom.setMatricula(scan.nextLine());
		
		//pedindo Login
		System.out.print("Login: ");
		novom.setLogin(scan.nextLine());
		
		//pedindo Senha
		System.out.print("Senha: ");
		novom.setSenha(scan.nextLine());
		
		//persistindo no banco
		mdao.inserir(novom);
	}
}
