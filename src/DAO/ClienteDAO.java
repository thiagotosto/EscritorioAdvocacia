package DAO;

import java.sql.*;

import javax.swing.*;

import modelo.Advogado;
import modelo.Cliente;

public class ClienteDAO {

	Statement stmt;
	Connection con;
	
	public void conexaoBD ()
	{
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    String url = "jdbc:mysql://localhost:3306/escritorio";
	    con = DriverManager.getConnection(url, "escritorio-user", "fak3TwokGoi");
	    
	    con.setAutoCommit(false);
	    
	    stmt = con.createStatement();
	
	  }  catch(java.lang.ClassNotFoundException e) {
	    System.err.print("Erro na Classe: " + e.getMessage());
	  } catch (SQLException e) {
	    System.err.print("Erro no SQL: " + e.getMessage());
	  }
	}
	
	public Cliente[] consultaTodos ()
	{
		int totalClientes = 1;
		Cliente[] p1 = new Cliente[1];
	    try {  	  
	     	String query = "SELECT * FROM cliente Order by idcliente";
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(idcliente)FROM cliente");
	       	con.commit();
	       	if (rs.next()) totalClientes = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	       	Cliente[] c = new Cliente[totalClientes];
	       	int i = 0;
	   	    while (rs.next()) {
	   	      c[i] = new Cliente();	
	          c[i].setId(rs.getInt("idcliente"));     // Pega o primeiro campo do tipo Int
	          c[i].setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          c[i].setCpf(rs.getString("cpf")); //Pega o terciro campo do tipo String
	          i++;
	        }
	        return c;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p1;   
	}
	
	public Cliente[] consultaTodos (Advogado advogado)
	{
		int totalClientes = 1;
		Cliente[] p1 = new Cliente[1];
	    try {  	  
	    	//buscando clientes que tem relação com algum processo que um certo advogado trabalha
	     	String query = "SELECT * " 
					 +	   "FROM "
					 +			"cliente c INNER JOIN  ( "
					 +				"SELECT DISTINCT idcliente, idadvogado "
					 +		        "FROM " 
					 + 					"processo_cliente pc INNER JOIN processo_advogado pa "
					 +		        "ON pc.idprocesso = pa.idpeticao "
					 +		    ") x "
					 +		"ON "
					 +	 		"x.idcliente = c.idcliente "
					 + 		"WHERE "
					 + 			"idadvogado = " + advogado.getIdAdvogado();
	     		     	
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(c.idcliente) " 
					 +	   "FROM "
					 +			"cliente c INNER JOIN  ( "
					 +				"SELECT DISTINCT idcliente, idadvogado "
					 +		        "FROM " 
					 + 					"processo_cliente pc INNER JOIN processo_advogado pa "
					 +		        "ON pc.idprocesso = pa.idpeticao "
					 +		    ") x "
					 +		"ON "
					 +	 		"x.idcliente = c.idcliente "
					 + 		"WHERE "
					 + 			"idadvogado = " + advogado.getIdAdvogado());
	       	
	       	
	       	con.commit();
	       	if (rs.next()) totalClientes = rs.getInt(1);
	       		     	
	       	rs = stmt.executeQuery(query);
	     	con.commit();
	       	Cliente[] c = new Cliente[totalClientes];
	       	int i = 0;
	   	    while (rs.next()) {
	   	      c[i] = new Cliente();	
	          c[i].setId(rs.getInt("idcliente"));     // Pega o primeiro campo do tipo Int
	          c[i].setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          c[i].setCpf(rs.getString("cpf")); //Pega o terciro campo do tipo String
	          i++;
	        }
	        return c;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p1;   
	}
	
	public Cliente consultaPorId (int id)
	{
		Cliente c = null;
	    try {  	  
	     	String query = "SELECT * FROM cliente WHERE idcliente = " + id;
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	if (rs.next()) 
	   	    {
	   	      c = new Cliente();	
	          c.setId(rs.getInt("idcliente"));     // Pega o primeiro campo do tipo Int
	          c.setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          c.setCpf(rs.getString("cpf"));          
	        }
	        return c;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return c;   
	}
	
	public Cliente consultaPorCPF (String cpf)
	{
		Cliente c = null;
	    try {  	  
	     	String query = "SELECT * FROM cliente WHERE cpf = '" + cpf + "'";
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	
	       	if (rs.next()) 
	   	    {
	   	      c = new Cliente();	
	          c.setId(rs.getInt("idcliente"));     // Pega o primeiro campo do tipo Int
	          c.setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          c.setCpf(rs.getString("cpf"));          
	        
	        }
	        return c;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return c;   
	}

	public int excluir (Cliente p)
	{
		int resultado = -1;
		try {  	  
	     	String sql = "DELETE FROM cliente WHERE idcliente = ";
	     	sql = sql + p.getId();
	       	resultado = stmt.executeUpdate(sql);  
	       	con.commit();
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	    return resultado;
	}
	
	public void inserir(Cliente Cliente) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
		 // Monta a string sql
		String sql = "insert into cliente (Nome,cpf) values(?,?)";
	
		// Passa a string para o PreparedStatement
		 pstm = con.prepareStatement(sql);
	
		// Coloca os verdadeiros valores no lugar dos ?
		pstm.setString(1, Cliente.getNome());		
		pstm.setString(2, Cliente.getCpf());
		
		// Executa
		 pstm.execute();
		 con.commit();
		 } catch (SQLException e) {
		// Retorna uma mensagem informando o erro
		 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
		                               + e, "Inserir", JOptionPane.ERROR_MESSAGE);
		 e.printStackTrace();
		 }
	
	}	
	
	public void atualizar (Cliente Cliente) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
			// Monta a string sql
			String sql = "update cliente set Nome = ?, cpf = ? where idcliente = ?";
		
			// Passa a string para o PreparedStatement
			 pstm = con.prepareStatement(sql);
		
			// Coloca os verdadeiros valores no lugar dos ?
			pstm.setString(1, Cliente.getNome());
			pstm.setString(2, Cliente.getCpf());
			pstm.setInt(3, Cliente.getId());	
			
			// Executa
			pstm.execute();
			
			con.commit();
		 } catch (SQLException e) {
			 // Retorna uma mensagem informando o erro
			 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
			                               + e, "Atualizarr", JOptionPane.ERROR_MESSAGE);
			 e.printStackTrace();
		 }
	}
}
