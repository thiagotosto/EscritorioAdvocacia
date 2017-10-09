package Text_interface;

import DAO.AdvogadoDAO;
import modelo.Advogado;


import java.util.Scanner;

public class Advogado_teste {

	//mostra todods os advogados
	private static void mostraTodos(){
		//instanciando AdvogadoDAO e fazendo conexão com o banco
		AdvogadoDAO adao = new AdvogadoDAO(); 
		adao.conexaoBD();
		Advogado[] advogados = adao.consultaTodos(); //retornando advogados da consulta
		
		//printando tabela de saída
		System.out.printf("\n%-15s%-30s%-15s\n","Id","Nome","Matrícula"); //print header
		
		for (int i = 0; i < advogados.length; i++) {	
			System.out.printf("%-15s%-30s%-15s\n", advogados[i].getId(), advogados[i].getNome(),
							  advogados[i].getMatricula());
		}
	}
	
	private static void inserir(Scanner scan){
		//instanciando AdvogadoDAO
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD(); // conectando com o banco
		
		//instanciando e populando Advogado novo
		Advogado novoa = new Advogado();
		
		//pedindo Nome
		System.out.print("Nome: ");
		novoa.setNome(scan.next());
		
		//pedindo Matrícula
		System.out.print("Matricula: ");
		novoa.setMatricula(scan.next());
		
		//pedindo Login
		System.out.print("Login: ");
		novoa.setLogin(scan.next());
		
		//pedindo Senha
		System.out.print("Senha: ");
		novoa.setSenha(scan.next());
		
		//pedindo Oab
		System.out.print("Oab: ");
		novoa.setOab(scan.next());
		
		//persistindo no banco
		adao.inserir(novoa);
	}
	
	public static void main(String[] args) {
		//instanciando Scanner
		Scanner scan = new Scanner(System.in);
		
		//testando mostra todos
		Advogado_teste.mostraTodos();
		
		//testando inserir
		//Advogado_teste.inserir(scan);
		
		scan.close();
	}
	
}
