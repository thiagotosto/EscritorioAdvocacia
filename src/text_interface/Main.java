package text_interface;

//import DAO.*;
import modelo.*;
import text_interface.*;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		
		MainMenu mm = new MainMenu(scan);
		
		//instanciando perfil
		Funcionario perfil = mm.getPerfil();
		
		//descobrindo privilegios do perfil
		String[] privilegio = mm.descobrePrivilegios(perfil.getLogin());
		
		//abrindo menu principal
		mm.displayMainMenu(privilegio, scan);		
				
	}	
}
