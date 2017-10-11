package Text_interface;

import java.util.Scanner;
import DAO.AdvogadoDAO;
//import DAO.AdvogadoDAO;
import modelo.Advogado;


//import java.util.Scanner;

public class AdvogadoMenu {
	
	//public Peticao[] consultarPeticao() {}
	
	Advogado perfil;
	
	public AdvogadoMenu(Advogado perfil){
		
		this.perfil = perfil;
		
	}
	
	public void editarPerfil(Scanner scan){
		
		//pedindo usuário a ser atualizado
		System.out.print("Qual usuário deseja atualizar: ");
		String login_escolhido = scan.nextLine();
		
		//instanciando AdvogadoDAO
		AdvogadoDAO adao = new AdvogadoDAO();
		adao.conexaoBD(); //conectando com o banco
		
		//select no banco do objeto a ser atualizado
		Advogado usuario = adao.consultaPorLogin(login_escolhido);
		System.out.println(usuario.getLogin()+", "+usuario.getId()); 
		
		String[] opcoes = {"Senha"};
		
		//menu de opções
		for (int i = 0; i < opcoes.length; i++){
			System.out.println(Integer.toString(i+1) + ". " + opcoes[i]);
		}
		
		System.out.print("Escolha o número da opção que deseja atualizar: ");
		int opcao = Integer.parseInt(scan.nextLine());
		
		//ação da escolha
		if (opcao == 1) {	//atualizando Nome
			System.out.print("Digite a novs Senha ");
			usuario.setSenha(scan.nextLine());
		}
		
		//persistindo em banco
		adao.atualizar(usuario);
	}

		
}

