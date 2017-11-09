package API;

import modelo.Funcionario;
import modelo.Gerente;
import DAO.FuncionarioDAO;
import DAO.GerenteDAO;

public class GerenteAPI {
	public static boolean adicionarFuncionario(Funcionario funcNovo) {
		//instanciando FuncionarioDAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); // conectando com o banco
		
		try {
			//persistindo no banco
			fdao.inserir(funcNovo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean editarFuncionario(String login_escolhido, String opcao, String valor) {
		
		//instanciando FuncionarioDAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD(); //conectando com o banco
		
		//select no banco do objeto a ser atualizado
		Funcionario usuario = fdao.consultaPorLogin(login_escolhido);
		
		//ação da escolha
		if (opcao == "Nome") {	//atualizando Nome
			usuario.setNome(valor);
		} else if (opcao == "Matrícula") {	//atualizando matrícula
			usuario.setMatricula(valor);
		} else if (opcao == "Login") {	//atualizando senha
			usuario.setLogin(valor);
		}
		
		try {
			//persistindo em banco
			fdao.atualizar(usuario);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean demitir(String login) {
		//instanciando FuncionarioDAO e conectando no banco
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD();
		
		//coletando usuário a ser excluido
		Funcionario usuario = fdao.consultaPorLogin(login);
		
		if (usuario != null) {
			//excluindo usuario
			fdao.excluir(usuario);
			return(true);
		} else {
			System.out.println("Este funcionário não consta no registro");
			return(false);
		}
	}
	/*
	public static void excluirFuncionario(Scanner scan) {
		//instanciando FuncionarioDAO e conectando no banco
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD();
		
		//coletando usuário a ser excluido
		System.out.print("Digite o usuário que deseja excluir: ");
		String usuario_excluido = scan.nextLine();
		Funcionario usuario = fdao.consultaPorLogin(usuario_excluido);
		
		//excluindo usuario
		fdao.excluir(usuario);
	}*/

	public static String promoverGerente(String login) {
		//instanciando AdvogadoDAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		GerenteDAO gdao = new GerenteDAO();
		fdao.conexaoBD(); // conectando com o banco
		gdao.conexaoBD(); // conectando com o banco
		
		//instanciando e populando Advogado novo
		Funcionario novog = fdao.consultaPorLogin(login); 
		
		/*
		if (novog != null) {
			//persistindo no banco
			gdao.promove(novog);
		} else {
			System.out.println("Esse funcionário não consta na base de dados!");
		}*/
		
		try {
			//persistindo no banco
			gdao.promove(novog);
			return "Ok";
		} catch (NullPointerException ne) {
			return "NullPointer";
		} catch (Exception e) {
			return "Erro";
		}
	}
	
	public static String despromoverGerente(String usuario_excluido) {
		//instanciando GerenteDAO e conectando no banco
		GerenteDAO gdao = new GerenteDAO();
		gdao.conexaoBD();
		
		//buscando usuario no banco
		Gerente usuario = gdao.consultaPorLogin(usuario_excluido);
		
		try {
			//excluindo usuario
			gdao.despromove(usuario);
			return "Ok";
		} catch (NullPointerException ne) {
			return "NullPointer";
		} catch (Exception e) {
			return "Erro";
		}
	}
	
	//printa todos os gerentes
	public static Gerente[] mostraTodosGerentes() {
		//instanciando GerenteDAO e conectando no banco
		GerenteDAO gdao = new GerenteDAO();
		gdao.conexaoBD();
		
		Gerente[] gerentes = gdao.consultaTodos();
		
		return gerentes;
	}
}
