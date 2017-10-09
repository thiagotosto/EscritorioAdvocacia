package DAO;

import java.sql.*;
import javax.swing.*;
import modelo.Advogado;
/**
 * @author Orlando
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdvogadoDAO {

	Statement stmt;
	Connection con;
	
	public void conexaoBD ()
	{
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    String url = "jdbc:mysql://localhost:3306/escritorio";
	    con = DriverManager.getConnection(url, "escritorio-user", "ejEvkoid3");
	    
	    
	    stmt = con.createStatement();
	
	  }  catch(java.lang.ClassNotFoundException e) {
	    System.err.print("Erro na Classe: " + e.getMessage());
	  } catch (SQLException e) {
	    System.err.print("Erro no SQL: " + e.getMessage());
	  }
	}
	
	//consulta todos 
	public Advogado[] consultaTodos ()
	{
		int totalAdvogados = 1;
		Advogado[] p1 = new Advogado[1];
	    try {  	  
	     	String query = "SELECT * FROM advogado INNER JOIN funcionario ON advogado.idfuncionario = funcionario.id Order by idadvogado";
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(idadvogado)FROM advogado");
	       	if (rs.next()) totalAdvogados = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	       	Advogado[] p = new Advogado[totalAdvogados];
	       	int i = 0;
	   	    while (rs.next()) {
	   	      p[i] = new Advogado();	
	          p[i].setId(rs.getInt("idadvogado"));     // Pega o primeiro campo do tipo Int
	          p[i].setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          p[i].setMatricula(rs.getString("Matricula")); //Pega o terciro campo do tipo String
	          p[i].setOab(rs.getString("oab"));
	          i++;
	        }
	        return p;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p1;   
	}
	
	public Advogado consultaPorId (int Id)
	{
		Advogado p = null;
	    try {  	  
	     	String query = "SELECT * FROM advogado WHERE Id = " + Id;
	       	ResultSet rs = stmt.executeQuery(query);
	       	if (rs.next()) 
	   	    {
	   	      p = new Advogado();	
	          p.setId(rs.getInt("idadvogado"));     // Pega o primeiro campo do tipo Int
	          p.setNome(rs.getString("Nome"));// Pega o segundo campo do tipo String
	          p.setMatricula(rs.getString("Matricula"));          
	        }
	        return p;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p;   
	}
	
	//consulta advogado por login
	public Advogado consultaPorLogin (String login)
	{
		Advogado p = null;
	    try {  	  
	     	String query = "SELECT * FROM advogado AS a INNER JOIN funcionario AS f ON a.idfuncionario = f.id WHERE login = '" + login + "'";
	     	System.out.println(query);
	       	ResultSet rs = stmt.executeQuery(query);
	       	if (rs.next()) 
	   	    {
	   	      p = new Advogado();	
	          p.setId(rs.getInt("idadvogado"));     // Pega o primeiro campo do tipo Int
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

	public int excluir (Advogado p)
	{
		int resultado = -1;
		try {  	  
	     	String sql = "DELETE FROM Advogado WHERE Id = ";
	     	sql = sql + p.getId();
	       	resultado = stmt.executeUpdate(sql);      
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	    return resultado;
	}
	
	public void inserir(Advogado Advogado) {
	
		// Cria um PreparedStatement para funcionario e advogado
		PreparedStatement pstmf = null;
		PreparedStatement pstma = null;
		
		conexaoBD ();
		try {
			 // Monta a string sql
			String sqlf = "insert into funcionario (Nome,Matricula,Login, Senha) values(?,?,?,?)";
			String sqla = "insert into advogado (oab, idfuncionario) values(?, LAST_INSERT_ID())";
			
			// Passa a string para o PreparedStatement de funcionario e advogado
			pstmf = con.prepareStatement(sqlf);
			pstma = con.prepareStatement(sqla);
			
			// Coloca os verdadeiros valores no lugar dos ? funcionario
			pstmf.setString(1, Advogado.getNome());		
			pstmf.setString(2, Advogado.getMatricula());
			pstmf.setString(3, Advogado.getLogin());
			pstmf.setString(4, Advogado.getSenha());
	
			// Coloca os verdadeiros valores no lugar dos ? em advogado
			pstma.setString(1, Advogado.getOab());
			
			// Executa
			pstmf.execute();
			pstma.execute();
		 } catch (SQLException e) {
			// Retorna uma mensagem informando o erro
			 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
			                               + e, "Inserir", JOptionPane.ERROR_MESSAGE);
			 e.printStackTrace();
		 }
	
	}	
	
	public void atualizar (Advogado Advogado) {
	
		// Cria um PreparedStatement para funcionario e para advogado
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
		 // Monta a string sql
		String sql = "update Advogado set Nome = ?, Matricula = ?, Login = ?, Senha = ? where Id = ?";
	
		// Passa a string para o PreparedStatement
		 pstm = con.prepareStatement(sql);
	
		// Coloca os verdadeiros valores no lugar dos ?
		pstm.setString(1, Advogado.getNome());
		pstm.setString(2, Advogado.getMatricula());
		pstm.setString(3, Advogado.getLogin());
		pstm.setString(4, Advogado.getSenha());
		pstm.setInt(5, Advogado.getId());	
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
