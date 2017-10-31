package API;

import modelo.Funcionario;
import DAO.FuncionarioDAO;

public class FuncionarioAPI {
	
	public static boolean mudarSenha(Funcionario perfil, String senhaNova) {
		//instanciando funciionario DAO
		FuncionarioDAO fdao = new FuncionarioDAO();
		
		//mudando senha
		perfil.setSenha(senhaNova);
		
		try {
			//persistindo no banco
			fdao.atualizar(perfil);
			return true;
		} catch (Exception e){
			return false;
		}
		//inicializando tentativas
		//int tentativas = 0;
		
		/* ISSO DEVE SER FEITO NO FRONT END!!!
		//loop de tentativas
		while (tentativas < 3) {
			
			
			//coletando senha nova do usuário
			System.out.print("Digite a nova senha: ");
			String senha1 = scan.nextLine();
			System.out.print("Digite a senha novamente: ");
			String senha2 = scan.nextLine();
			
			//testando se a senha bate
			if (senha1.equals(senha2)) {
				perfil.setSenha(senha1);
				
				fdao.atualizar(perfil);
				break;
			} else {
				System.out.println("Senhas não batem!");
				tentativas += 1;
			}
		
		
		
			
		}*/ //FRONTEND!!!
	}
}
