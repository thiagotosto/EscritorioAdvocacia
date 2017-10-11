package Text_interface;

import DAO.*;
import modelo.*;
import Text_interface.*;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		
		GerenteMenu gm = new GerenteMenu();
		
		gm.despromoverGerente(scan);
	}	
}
