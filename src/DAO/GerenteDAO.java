package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.Gerente;

public class GerenteDAO extends FuncionarioDAO {
	
	public Gerente[] consultaTodos ()
	{
		int totalGerentes = 1;
		Gerente[] p1 = new Gerente[1];
	    try {  	  
	     	String query = "SELECT * FROM gerente Order by idgerente";
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(Id)FROM gerente");
	       	if (rs.next()) totalGerentes = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	     	con.commit();
	     	
	       	Gerente[] p = new Gerente[totalGerentes];
	       	int i = 0;
	   	    while (rs.next()) {
	   	      p[i] = new Gerente();	
	          p[i].setIdGerente(rs.getInt("idgerente"));     // Pega o primeiro campo do tipo Int
	          p[i].setId(rs.getInt("idfuncionario"));// Pega o segundo campo do tipo String
	          i++;
	        }
	        return p;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p1;   
	}
	
	//inutilizado ainda...
	public Gerente consultaPorId (int Id)
	{
		Gerente p = null;
	    try {  	  
	     	String query = "SELECT * FROM Gerente WHERE Id = " + Id;
	       	ResultSet rs = stmt.executeQuery(query);
	       	if (rs.next()) 
	   	    {
	   	      p = new Gerente();	
	          p.setId(rs.getInt("Id"));     // Pega o primeiro campo do tipo Int
	          p.setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          p.setMatricula(rs.getString("Matricula"));          
	        }
	        return p;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p;   
	}
	
	public Gerente consultaPorLogin (String login)
	{
		Gerente g = null;
	    try {  	  
	     	String query = "SELECT * FROM gerente g INNER JOIN funcionario f ON g.idfuncionario = f.id WHERE login = '" + login + "'";
	        
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	
	       	if (rs.next()) 
	   	    {
	   	      g = new Gerente();	
	          g.setId(rs.getInt("id"));     // Pega o primeiro campo do tipo Int
	          g.setIdGerente(rs.getInt("idgerente"));// Pega o segundo campo do tipo Int
	        }
	        return g;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return g;   
	}

	public int excluir (Gerente p)
	{
		int resultado = -1;
		try {  	  
	     	String sql = "DELETE FROM Gerente WHERE Id = ";
	     	sql = sql + p.getId();
	       	resultado = stmt.executeUpdate(sql);      
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	    return resultado;
	}
	
	public void inserir(Gerente Gerente) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
		 // Monta a string sql
		String sql = "insert into Gerente (Nome,Matricula,Login, Senha) values(?,?,?,?)";
	
		// Passa a string para o PreparedStatement
		 pstm = con.prepareStatement(sql);
	
		// Coloca os verdadeiros valores no lugar dos ?
		pstm.setString(1, Gerente.getNome());		
		pstm.setString(2, Gerente.getMatricula());
		pstm.setString(3, Gerente.getLogin());
		pstm.setString(4, Gerente.getSenha());
		// Executa
		 pstm.execute();
		 } catch (SQLException e) {
		// Retorna uma mensagem informando o erro
		 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
		                               + e, "Inserir", JOptionPane.ERROR_MESSAGE);
		 e.printStackTrace();
		 }
	
	}	
	
	public void atualizar (Gerente Gerente) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
		 // Monta a string sql
		String sql = "update Gerente set Nome = ?, Matricula = ?, Login = ?, Senha = ? where Id = ?";
	
		// Passa a string para o PreparedStatement
		 pstm = con.prepareStatement(sql);
	
		// Coloca os verdadeiros valores no lugar dos ?
		pstm.setString(1, Gerente.getNome());
		pstm.setString(2, Gerente.getMatricula());
		pstm.setString(3, Gerente.getLogin());
		pstm.setString(4, Gerente.getSenha());
		pstm.setInt(5, Gerente.getId());	
		// Executa
		 pstm.execute();
		 } catch (SQLException e) {
		// Retorna uma mensagem informando o erro
		 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
		                               + e, "Atualizarr", JOptionPane.ERROR_MESSAGE);
		 e.printStackTrace();
		 }
	}
}
