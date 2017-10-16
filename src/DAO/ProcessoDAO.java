package DAO;

import java.sql.*;
import javax.swing.*;
import modelo.Processo;
/**
 * @author Orlando
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProcessoDAO {

	Statement stmt;
	Connection con;
	
	public void conexaoBD ()
	{
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    String url = "jdbc:mysql://localhost:3306/escritorio";
	    con = DriverManager.getConnection(url, "escritorio-user", "ejEvkoid3");
	    
	    con.setAutoCommit(false);
	    
	    stmt = con.createStatement();
	
	  }  catch(java.lang.ClassNotFoundException e) {
	    System.err.print("Erro na Classe: " + e.getMessage());
	  } catch (SQLException e) {
	    System.err.print("Erro no SQL: " + e.getMessage());
	  }
	}
	
	public Processo[] consultaTodos ()
	{
		int totalProcessos = 1;
		Processo[] p1 = new Processo[1];
	    try {  	  
	     	String query = "SELECT * FROM processo Order by Id";
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(Id)FROM Processo");
	       	con.commit();
	       	if (rs.next()) totalProcessos = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	       	Processo[] p = new Processo[totalProcessos];
	       	int i = 0;
	   	    while (rs.next()) {
	   	      p[i] = new Processo();	
	          p[i].setId(rs.getInt("Id"));    
	          p[i].setNome(rs.getString("nome"));
	          p[i].setDescricao(rs.getString("descricao"));
	          i++;
	        }
	        return p;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p1;   
	}
	
	/*public Processo consultaPorId (int Id)
	{
		Processo p = null;
	    try {  	  
	     	String query = "SELECT * FROM Processo WHERE Id = " + Id;
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	if (rs.next()) 
	   	    {
	   	      p = new Processo();	
	          p.setId(rs.getInt("Id"));     // Pega o primeiro campo do tipo Int
	          p.setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          p.setMatricula(rs.getString("Matricula"));          
	        }
	        return p;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p;   
	}*/
	
	public Processo consultaPorNome (String nome)
	{
		Processo p = null;
	    try {  	  
	     	String query = "SELECT * FROM processo WHERE nome = '" + nome + "'";
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	
	       	if (rs.next()) 
	   	    {
	   	      p = new Processo();	
	          p.setId(rs.getInt("Id"));
	          p.setNome(rs.getString("Nome"));
	          p.setDescricao(rs.getString("descricao"));          
	        }
	        return p;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p;   
	}

	public int excluir (Processo p)
	{
		int resultado = -1;
		try {  	  
	     	String sql = "DELETE FROM processo WHERE Id = ";
	     	sql = sql + p.getId();
	       	resultado = stmt.executeUpdate(sql);  
	       	con.commit();
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	    return resultado;
	}
	
	public void inserir(Processo Processo) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
		 // Monta a string sql
		String sql = "insert into processo (nome,descricao) values(?,?)";
	
		// Passa a string para o PreparedStatement
		 pstm = con.prepareStatement(sql);
	
		// Coloca os verdadeiros valores no lugar dos ?
		pstm.setString(1, Processo.getNome());		
		pstm.setString(2, Processo.getDescricao());
		
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
	
	public void atualizar (Processo Processo) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
			// Monta a string sql
			String sql = "update processo set nome = ?, descricao= ? where Id = ?";
		
			// Passa a string para o PreparedStatement
			pstm = con.prepareStatement(sql);
		
			// Coloca os verdadeiros valores no lugar dos ?
			pstm.setString(1, Processo.getNome());
			pstm.setString(2, Processo.getDescricao());
			pstm.setInt(3, Processo.getId());	
			
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
