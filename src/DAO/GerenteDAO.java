package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.*;

public class GerenteDAO extends FuncionarioDAO {
	
	public Gerente[] consultaTodos ()
	{
		int totalGerentes = 1;
		Gerente[] p1 = new Gerente[1];
	    try {  	  
	     	String query = "SELECT * FROM gerente g INNER JOIN funcionario f ON g.idfuncionario = f.id Order by idgerente";
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(idgerente)FROM gerente");
	       	if (rs.next()) totalGerentes = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	     	con.commit();
	     	
	       	Gerente[] g = new Gerente[totalGerentes];
	       	int i = 0;
	   	    while (rs.next()) {
	   	      g[i] = new Gerente();	
	   	      g[i].setNome(rs.getString("nome"));
	   	      g[i].setMatricula(rs.getString("matricula"));
	   	      g[i].setLogin(rs.getString("login"));
	   	      g[i].setSenha(rs.getString("senha"));
	          g[i].setIdGerente(rs.getInt("idgerente"));     // Pega o primeiro campo do tipo Int
	          g[i].setId(rs.getInt("idfuncionario"));// Pega o segundo campo do tipo String
	          i++;
	        }
	        return g;
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
	       	con.commit();
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
	          g.setIdFuncionario(rs.getInt("idfuncionario"));
	        }
	        return g;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return g;   
	}

	public int despromove(Gerente g)
	{
		int resultado = -1;
		try {  	  
	     	String sql = "DELETE FROM gerente WHERE idfuncionario = ";
	     	sql = sql + g.getIdFuncionario();
	       	resultado = stmt.executeUpdate(sql);
	       	con.commit();
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	    return resultado;
	}
	
	public void promove(Funcionario funcionario) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		
		conexaoBD ();
		try {
			 // Monta a string sql
			String sql = "insert into gerente (idfuncionario) values(?)";
		
			
			
			// Passa a string para o PreparedStatement
			pstm = con.prepareStatement(sql);
		
			// Coloca os verdadeiros valores no lugar dos ?
			pstm.setInt(1, funcionario.getId());		
			
			System.out.println(pstm);
			
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
	
	public void atualizar (Gerente Gerente) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
		 // Monta a string sql
		String sql = "update gerente set Nome = ?, Matricula = ?, Login = ?, Senha = ? where Id = ?";
	
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
