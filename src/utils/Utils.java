package utils;

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
}

