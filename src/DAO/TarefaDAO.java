package DAO;

import java.sql.*;

import javax.swing.*;

import modelo.Tarefa;
import modelo.Funcionario;

public class TarefaDAO {

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
	
	//consulta todas as tarefas de um Funcionario
	public Tarefa[] consultaTodas (Funcionario f)
	{
		int totalTarefas = 1;
		Tarefa[] t1 = new Tarefa[1];
	    try {  	  
	     	String query = "SELECT * FROM tarefa WHERE idfuncionario = '"+ f.getId() +"' Order by dataExpr ASC";
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(idtarefa)FROM tarefa WHERE idfuncionario = '"+ f.getId() +"'");
	       	con.commit();
	       	if (rs.next()) totalTarefas = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	       	Tarefa[] t = new Tarefa[totalTarefas];
	       	int i = 0;
	   	    while (rs.next()) {
	   	      t[i] = new Tarefa();	
	          t[i].setId(rs.getInt("idtarefa"));
	          t[i].setNome(rs.getString("nome"));
	          t[i].setOwner(f);
	          t[i].setCriador(f);
	          t[i].setDescricao(rs.getString("descricao"));
	          t[i].setExpdData(rs.getString("dataExpd"));
	          t[i].setExprData(rs.getString("dataExpr"));
	          i++;
	        }
	        return t;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return t1;   
	}
	
	public Tarefa consultaPorId (int Id)
	{
		Tarefa t = null;
		FuncionarioDAO fdao = new FuncionarioDAO();
		fdao.conexaoBD();
		
	    try {  	  
	     	String query = "SELECT * FROM tarefa WHERE idtarefa = " + Id;
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	if (rs.next()) 
	   	    {
	   	      t = new Tarefa();	
	          t.setId(rs.getInt("idtarefa"));     // Pega o primeiro campo do tipo Int
	          t.setNome(rs.getString("nome"));
	          t.setDescricao(rs.getString("descricao"));// Pega o segundo campo do tipo String
	          t.setOwner(fdao.consultaPorId(rs.getInt("idfuncionario")));
	          t.setCriador(fdao.consultaPorId(rs.getInt("idcriador")));
	          t.setExpdData(rs.getString("dataExpd"));
	          t.setExprData(rs.getString("dataExpr"));
	        }
	        return t;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return t;   
	}
	
	/*
	public Tarefa consultaPorLogin (String login)
	{
		Tarefa p = null;
	    try {  	  
	     	String query = "SELECT * FROM Tarefa WHERE login = '" + login + "'";
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	
	       	if (rs.next()) 
	   	    {
	   	      p = new Tarefa();	
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
	*/
	
	public int excluir (Tarefa t)
	{
		int resultado = -1;
		try {  	  
	     	String sql = "DELETE FROM tarefa WHERE idtarefa = ";
	     	sql = sql + t.getId();
	       	resultado = stmt.executeUpdate(sql);  
	       	con.commit();
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	    return resultado;
	}
	
	public void inserir(Tarefa tarefa) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
			// Monta a string sql
			String sql = "insert into tarefa (idfuncionario,descricao,dataExpr,dataExpd,idcriador,nome) values(?,?,?,?,?,?)";
		
			// Passa a string para o PreparedStatement
			pstm = con.prepareStatement(sql);
		
			// Coloca os verdadeiros valores no lugar dos ?
			pstm.setInt(1, tarefa.getOwner().getId());		
			pstm.setString(2, tarefa.getDescricao());
			pstm.setString(3, tarefa.getExprData());
			pstm.setString(4, tarefa.getExpdData());
			pstm.setInt(5, tarefa.getCriador().getId());
			pstm.setString(6, tarefa.getNome());
			
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
	
	public void inserir(Tarefa tarefa, String nocommit) {

		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
			// Monta a string sql
			String sql = "insert into tarefa (idfuncionario,descricao,dataExpr,dataExpd,idcriador,nome) values(?,?,?,?,?,?)";
		
			// Passa a string para o PreparedStatement
			pstm = con.prepareStatement(sql);
		
			// Coloca os verdadeiros valores no lugar dos ?
			pstm.setInt(1, tarefa.getOwner().getId());		
			pstm.setString(2, tarefa.getDescricao());
			pstm.setString(3, tarefa.getExprData());
			pstm.setString(4, tarefa.getExpdData());
			pstm.setInt(5, tarefa.getCriador().getId());
			pstm.setString(6, tarefa.getNome());
			
			// Executa
			pstm.execute();
			if (nocommit != "NoCommit") con.commit();
		} catch (SQLException e) {
			// Retorna uma mensagem informando o erro
			JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
			                               + e, "Inserir", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		 }
	
	}	
		
	public void atualizar (Tarefa tarefa) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		
		try {
			// Monta a string sql
			String sql = "update tarefa set descricao = ?, dataExpr = ?, nome = ? where idtarefa = ?";
		
			// Passa a string para o PreparedStatement
			pstm = con.prepareStatement(sql);
		
			// Coloca os verdadeiros valores no lugar dos ?
			pstm.setString(1, tarefa.getDescricao());
			pstm.setString(2, tarefa.getExprData());
			pstm.setString(3, tarefa.getNome());
			pstm.setInt(4, tarefa.getId());	
			
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
