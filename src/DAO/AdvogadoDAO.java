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
	    //String url = "jdbc:mysql://localhost:3306/escritorio?useUnicode=true&characterEncoding=utf-8";
	    con = DriverManager.getConnection(url, "escritorio-user", "ejEvkoid3");
	    
	    con.setAutoCommit(false);
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
	       	con.commit();
	       	if (rs.next()) totalAdvogados = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	       	Advogado[] p = new Advogado[totalAdvogados];
	       	int i = 0;
	   	    while (rs.next()) {
	   	      p[i] = new Advogado();	
	          p[i].setId(rs.getInt("idfuncionario"));
	          p[i].setIdAdvogado(rs.getInt("id"));
	          p[i].setNome(rs.getString("Nome"));
	          p[i].setLogin(rs.getString("login"));
	          p[i].setMatricula(rs.getString("Matricula"));
	          p[i].setOab(rs.getString("oab"));
	          i++;
	        }
	        return p;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p1;   
	}
	
	//inutilizado...
	public Advogado consultaPorId (int Id)
	{
		Advogado p = null;
	    try {  	  
	     	String query = "SELECT * FROM advogado WHERE idadvogado = " + Id;
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
		Advogado a = null;
	    try {  	  
	     	String query = "SELECT * FROM advogado AS a INNER JOIN funcionario AS f ON a.idfuncionario = f.id WHERE login = '" + login + "'";
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	
	       	if (rs.next()) 
	   	    {
	   	      a = new Advogado();
	   	      a.setId(rs.getInt("id"));
	          a.setIdAdvogado(rs.getInt("idadvogado"));     
	          a.setNome(rs.getString("Nome"));
	          a.setMatricula(rs.getString("Matricula"));          
	          a.setLogin(login);
	          a.setSenha(rs.getString("senha"));
	          a.setOab(rs.getString("oab"));
	        }
	        return a;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return a;   
	}

	//exclui advogado recebendo o objeto advogado como parametro
	public int excluir (Advogado p)
	{
		int resultado = -1;
		try {
	     	String sql = "DELETE FROM funcionario WHERE id = ";
	     	sql = sql + p.getId();
	       	resultado = stmt.executeUpdate(sql);    
	       	con.commit();
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	    return resultado;
	}
	
	//insere no banco um advogado passando um objeto advogado como parametro
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
			
			// Executa e comita
			pstmf.execute();
			pstma.execute();
			con.commit();
		 } catch (SQLException e) {
			// Retorna uma mensagem informando o erro
			 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
			                               + e, "Inserir", JOptionPane.ERROR_MESSAGE);
			 e.printStackTrace();
		 }
	
	}	
	
	//atualiza um advogado passando um objeto advogado como parametro ja atualizado 
	public void atualizar (Advogado Advogado) {
		
		// Cria um PreparedStatement para funcionario e para advogado
		PreparedStatement pstmf = null;
		PreparedStatement pstma = null;
		
		conexaoBD ();
		
		try {
		    // Monta a string sql
		    String sqlf = 		"UPDATE funcionario SET Nome = ?, Matricula = ?, Login = ?, Senha = ? where id = ?";
			String sqla = 		"UPDATE advogado SET oab = ? WHERE idfuncionario = ?";
			
			// Passa a string para o PreparedStatement de funcionario e advogado
			pstmf = con.prepareStatement(sqlf);
			pstma = con.prepareStatement(sqla);
			
			// Coloca os verdadeiros valores no lugar dos ?
			pstmf.setString(1, Advogado.getNome());
			pstmf.setString(2, Advogado.getMatricula());
			pstmf.setString(3, Advogado.getLogin());
			pstmf.setString(4, Advogado.getSenha());
			pstmf.setInt(5, Advogado.getId());
			pstma.setString(1, Advogado.getOab());
			pstma.setInt(2, Advogado.getId());
			
			// Executa e comita
			pstmf.execute();
			pstma.execute();
			con.commit();
		 } catch (SQLException e) {
			 //Retorna uma mensagem informando o erro
			 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
			                               + e, "Atualizarr", JOptionPane.ERROR_MESSAGE);
			 e.printStackTrace();
		 }
	}
}
