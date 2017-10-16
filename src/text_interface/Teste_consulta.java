package text_interface;

import DAO.*;
import modelo.*;

public class Teste_consulta {

	public static void main(String[] args){
		FuncionarioDAO fdao = new FuncionarioDAO(); //instanciando FuncionarioDAO
		fdao.conexaoBD();
		Funcionario[] funcionarios = fdao.consultaTodos(); //retornando funcionarios da consulta
		
		//printando tabela de saída
		System.out.println("Id\tNome\tMatrícula"); //print header
		
		for (int i = 0; i < funcionarios.length; i++) {	
			System.out.println(funcionarios[i].getId() + "\t" + funcionarios[i].getNome() +
								"\t" + funcionarios[i].getMatricula()); 
		}
	}
}
