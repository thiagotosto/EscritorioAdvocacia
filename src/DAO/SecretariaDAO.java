package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import modelo.*;

public class SecretariaDAO extends FuncionarioDAO {
	
	public Secretaria[] consultaTodos ()
	{
		int totalSecretarias = 1;
		Secretaria[] p1 = new Secretaria[1];
	    try {  	  
	     	String query = "SELECT * FROM secretaria g INNER JOIN funcionario f ON g.idfuncionario = f.id Order by idsecretaria";
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(idSecretaria)FROM secretaria");
	       	if (rs.next()) totalSecretarias = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	     	con.commit();
	     	
	       	Secretaria[] s = new Secretaria[totalSecretarias];
	       	int i = 0;
	   	    while (rs.next()) {
	   	      s[i] = new Secretaria();	
	   	      s[i].setNome(rs.getString("nome"));
	   	      s[i].setMatricula(rs.getString("matricula"));
	   	      s[i].setLogin(rs.getString("login"));
	   	      s[i].setSenha(rs.getString("senha"));
	          s[i].setIdSecretaria(rs.getInt("idsecretaria"));     // Pega o primeiro campo do tipo Int
	          s[i].setId(rs.getInt("idfuncionario"));// Pega o segundo campo do tipo String
	          i++;
	        }
	        return s;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p1;   
	}
	
	//inutilizado ainda...
	public Secretaria consultaPorId (int Id)
	{
		Secretaria s = null;
	    try {  	  
	     	String query = "SELECT * FROM secretaria WHERE Id = " + Id;
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	if (rs.next()) 
	   	    {
	   	      s = new Secretaria();	
	          s.setId(rs.getInt("Id"));     // Pega o primeiro campo do tipo Int
	          s.setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          s.setMatricula(rs.getString("Matricula"));          
	        }
	        return s;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return s;   
	}
	
	public Secretaria consultaPorLogin (String login) {
		Secretaria s = null;
	    try {  	  
	     	String query = "SELECT * FROM secretaria g INNER JOIN funcionario f ON g.idfuncionario = f.id WHERE login = '" + login + "'";

	       	ResultSet rs = stmt.executeQuery(query);
	       	
	       	con.commit();
	       	
	       	if (rs.next()) 
	   	    {
	   	      s = new Secretaria();	
	          s.setId(rs.getInt("id"));     // Pega o primeiro campo do tipo Int
	          s.setIdSecretaria(rs.getInt("idSecretaria"));// Pega o segundo campo do tipo Int
	          s.setIdFuncionario(rs.getInt("idfuncionario"));
	        }
	        return s;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return s;   
	}

	public int excluir(Secretaria mb) {
		int resultado = -1;
		try {  	  
	     	String sql = "DELETE FROM funcionario WHERE id = ";
	     	sql = sql + mb.getIdFuncionario();
	       	resultado = stmt.executeUpdate(sql);
	       	con.commit();
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	    return resultado;
	}
	
	public void inserir(Funcionario Secretaria) {
	
		super.inserir(Secretaria);
		
		//trazendo o funcionario do banco de novo com o id setado
		Secretaria = super.consultaPorLogin(Secretaria.getLogin());
		
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD();
			try {
				// Monta a string sql
				String sql = "insert into secretaria (idfuncionario) values(?)";
				
				// Passa a string para o PreparedStatement
				pstm = con.prepareStatement(sql);
			
				// Coloca os verdadeiros valores no lugar dos ?
				pstm.setInt(1, Secretaria.getId());		
								
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
	
	public void atualizar (Secretaria Secretaria) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
			// Monta a string sql
			String sql = "update secretaria set Nome = ?, Matricula = ?, Login = ?, Senha = ? where Id = ?";
		
			// Passa a string para o PreparedStatement
			pstm = con.prepareStatement(sql);
		
			// Coloca os verdadeiros valores no lugar dos ?
			pstm.setString(1, Secretaria.getNome());
			pstm.setString(2, Secretaria.getMatricula());
			pstm.setString(3, Secretaria.getLogin());
			pstm.setString(4, Secretaria.getSenha());
			pstm.setInt(5, Secretaria.getId());	
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
