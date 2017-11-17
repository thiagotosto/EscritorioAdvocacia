package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.FuncionarioDAO;

public class Utils {

  public static String[] joinArray(String[]... array){
    int tamanho = 0;
    
    //calculando tamanho do array
    for (int i = 0; i < array.length; i++) {
      tamanho += array[i].length;
    }
    
    String[] result = new String[tamanho];
    
    //topo da pilha
    int top = 0;
    
    //variando pelos parametros
    for (int j = 0; j < array.length; j++) {
      
      //variando pelos vetores internos 
      for (int i = 0; i < array[j].length; i++) {
        
        if (j == 0) {
          result[i] = array[j][i];
        } else {
          result[top + i] = array[j][i];
        }
      }
      
    //atualizando topo
    top += array[j].length;
    }
    
    return result;
  }
  
  public static String geraMatricula(){
	//instanciando FuncionarioDAO e conectando no banco
	FuncionarioDAO fdao = new FuncionarioDAO();
	fdao.conexaoBD();
	
	String ultimaMatricula = Long.toString(fdao.consultaUltimaMatricula());
	System.out.println(ultimaMatricula);
	int ultimoNumero = Integer.parseInt(ultimaMatricula.substring(ultimaMatricula.length() - 3, ultimaMatricula.length()));
	
	//criando data atual
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
		
	long matricula = Long.parseLong(dateFormat.format(date).replaceAll("-", "")) * 1000 + ultimoNumero + 1;
	
	return Long.toString(matricula);
  }
  
  public static String parseDate(String date) {
      String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
      
      String[] date_splited = date.split("[ ,]");
     
      String result = null;
      
      
      for (int i = 0; i < month.length; i++) {
          if (date_splited[1].equals(month[i])) {
              result = date_splited[3] + "-" + (i+1) + "-" + date_splited[0];
          }
      }
      
      return result;
  }
}

