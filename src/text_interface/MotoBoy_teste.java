package text_interface;

import modelo.Funcionario;
import modelo.MotoBoy;
import DAO.MotoBoyDAO;

public class MotoBoy_teste {

	public static void main(String[] args) {
		MotoBoyDAO mtdao = new MotoBoyDAO();
		mtdao.conexaoBD();
		
		/*
		Funcionario motoboy = new Funcionario();
		motoboy.setLogin("motoboy");
		motoboy.setMatricula("136454879797");
		motoboy.setNome("Moto Boy da Silva");
		motoboy.setSenha("motoboysenha");
		
		mtdao.inserir(motoboy);
		*/
		
		MotoBoy motoboy = mtdao.consultaPorLogin("motoboy");
		mtdao.excluir(motoboy);
	}
	
}
