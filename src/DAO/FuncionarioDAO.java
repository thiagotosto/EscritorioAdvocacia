package DAO;

import java.sql.*;
import javax.swing.*;
import modelo.Funcionario;
/**
 * @author Orlando
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FuncionarioDAO {

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
	
	public Funcionario[] consultaTodos ()
	{
		int totalFuncionarios = 1;
		Funcionario[] p1 = new Funcionario[1];
	    try {  	  
	     	String query = "SELECT * FROM funcionario Order by Id";
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(Id)FROM funcionario");
	       	con.commit();
	       	if (rs.next()) totalFuncionarios = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	       	Funcionario[] p = new Funcionario[totalFuncionarios];
	       	int i = 0;
	   	    while (rs.next()) {
	   	      p[i] = new Funcionario();	
	          p[i].setId(rs.getInt("Id"));     // Pega o primeiro campo do tipo Int
	          p[i].setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          p[i].setMatricula(rs.getString("Matricula")); //Pega o terciro campo do tipo String
	          i++;
	        }
	        return p;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p1;   
	}
	
	public Funcionario consultaPorId (int Id)
	{
		Funcionario f = null;
	    try {  	  
	     	String query = "SELECT * FROM funcionario WHERE Id = " + Id;
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	if (rs.next()) 
	   	    {
	   	      f = new Funcionario();	
	          f.setId(rs.getInt("Id"));     // Pega o primeiro campo do tipo Int
	          f.setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          f.setMatricula(rs.getString("Matricula")); 
	          f.setLogin(rs.getString("login"));
	          f.setSenha(rs.getString("senha"));
	        }
	        return f;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return f;   
	}
	
	public Funcionario consultaPorLogin (String login)
	{		
		Funcionario p = null;
	    try {  	  
	     	String query = "SELECT * FROM funcionario WHERE login = '" + login + "'";
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	
	       	if (rs.next()) 
	   	    {
	   	      p = new Funcionario();	
	          p.setId(rs.getInt("Id"));     // Pega o primeiro campo do tipo Int
	          p.setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          p.setMatricula(rs.getString("Matricula"));          
	          p.setSenha(rs.getString("senha"));
	          p.setLogin(login);
	        }
	        return p;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p;   
	}

	public int excluir (Funcionario p)
	{
		int resultado = -1;
		try {  	  
	     	String sql = "DELETE FROM funcionario WHERE Id = ";
	     	sql = sql + p.getId();
	       	resultado = stmt.executeUpdate(sql);  
	       	con.commit();
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	    return resultado;
	}
	
	public void inserir(Funcionario Funcionario) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
		 // Monta a string sql
		String sql = "insert into funcionario (Nome,Matricula,Login, Senha) values(?,?,?,?)";
	
		// Passa a string para o PreparedStatement
		 pstm = con.prepareStatement(sql);
	
		// Coloca os verdadeiros valores no lugar dos ?
		pstm.setString(1, Funcionario.getNome());		
		pstm.setString(2, Funcionario.getMatricula());
		pstm.setString(3, Funcionario.getLogin());
		pstm.setString(4, Funcionario.getSenha());
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
	
	public void inserir(Funcionario Funcionario, String nocommit) {
		
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
		 // Monta a string sql
		String sql = "insert into funcionario (Nome,Matricula,Login, Senha) values(?,?,?,?)";
	
		// Passa a string para o PreparedStatement
		 pstm = con.prepareStatement(sql);
	
		// Coloca os verdadeiros valores no lugar dos ?
		pstm.setString(1, Funcionario.getNome());		
		pstm.setString(2, Funcionario.getMatricula());
		pstm.setString(3, Funcionario.getLogin());
		pstm.setString(4, Funcionario.getSenha());
		// Executa
		 pstm.execute();
		 
		 //nao comitar transição se for requisitado por um filho
		 if(nocommit != "NoCommit") con.commit();
		 } catch (SQLException e) {
		// Retorna uma mensagem informando o erro
		 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
		                               + e, "Inserir", JOptionPane.ERROR_MESSAGE);
		 e.printStackTrace();
		 }
	
	}	
	
	public void atualizar (Funcionario Funcionario) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
		 // Monta a string sql
		String sql = "update funcionario set Nome = ?, Matricula = ?, Login = ?, Senha = ? where Id = ?";
	
		// Passa a string para o PreparedStatement
		 pstm = con.prepareStatement(sql);
	
		// Coloca os verdadeiros valores no lugar dos ?
		pstm.setString(1, Funcionario.getNome());
		pstm.setString(2, Funcionario.getMatricula());
		pstm.setString(3, Funcionario.getLogin());
		pstm.setString(4, Funcionario.getSenha());
		pstm.setInt(5, Funcionario.getId());	
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
