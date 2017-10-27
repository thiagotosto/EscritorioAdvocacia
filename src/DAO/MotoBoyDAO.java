package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import modelo.*;

public class MotoBoyDAO extends FuncionarioDAO {
	
	public MotoBoy[] consultaTodos ()
	{
		int totalMotoBoys = 1;
		MotoBoy[] p1 = new MotoBoy[1];
	    try {  	  
	     	String query = "SELECT * FROM MotoBoy g INNER JOIN funcionario f ON g.idfuncionario = f.id Order by idmotoboy";
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(idmotoboy)FROM motoboy");
	       	if (rs.next()) totalMotoBoys = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	     	con.commit();
	     	
	       	MotoBoy[] g = new MotoBoy[totalMotoBoys];
	       	int i = 0;
	   	    while (rs.next()) {
	   	      g[i] = new MotoBoy();	
	   	      g[i].setNome(rs.getString("nome"));
	   	      g[i].setMatricula(rs.getString("matricula"));
	   	      g[i].setLogin(rs.getString("login"));
	   	      g[i].setSenha(rs.getString("senha"));
	          g[i].setIdMotoBoy(rs.getInt("idmotoboy"));     // Pega o primeiro campo do tipo Int
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
	public MotoBoy consultaPorId (int Id)
	{
		MotoBoy p = null;
	    try {  	  
	     	String query = "SELECT * FROM MotoBoy WHERE Id = " + Id;
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	if (rs.next()) 
	   	    {
	   	      p = new MotoBoy();	
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
	
	public MotoBoy consultaPorLogin (String login) {
		MotoBoy g = null;
	    try {  	  
	     	String query = "SELECT * FROM motoboy g INNER JOIN funcionario f ON g.idfuncionario = f.id WHERE login = '" + login + "'";

	       	ResultSet rs = stmt.executeQuery(query);
	       	
	       	con.commit();
	       	
	       	if (rs.next()) 
	   	    {
	   	      g = new MotoBoy();	
	          g.setId(rs.getInt("id"));     // Pega o primeiro campo do tipo Int
	          g.setIdMotoBoy(rs.getInt("idMotoBoy"));// Pega o segundo campo do tipo Int
	          g.setIdFuncionario(rs.getInt("idfuncionario"));
	        }
	        return g;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return g;   
	}

	public int excluir(MotoBoy mb) {
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
	
	public void inserir(Funcionario motoboy) {
	
		super.inserir(motoboy);
		
		//trazendo o funcionario do banco de novo com o id setado
		motoboy = super.consultaPorLogin(motoboy.getLogin());
		
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD();
		try {
			 // Monta a string sql
			String sql = "insert into motoboy (idfuncionario) values(?)";
			
			// Passa a string para o PreparedStatement
			pstm = con.prepareStatement(sql);
		
			// Coloca os verdadeiros valores no lugar dos ?
			pstm.setInt(1, motoboy.getId());		
			
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
	
	public void atualizar (MotoBoy MotoBoy) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
		 // Monta a string sql
		String sql = "update MotoBoy set Nome = ?, Matricula = ?, Login = ?, Senha = ? where Id = ?";
	
		// Passa a string para o PreparedStatement
		 pstm = con.prepareStatement(sql);
	
		// Coloca os verdadeiros valores no lugar dos ?
		pstm.setString(1, MotoBoy.getNome());
		pstm.setString(2, MotoBoy.getMatricula());
		pstm.setString(3, MotoBoy.getLogin());
		pstm.setString(4, MotoBoy.getSenha());
		pstm.setInt(5, MotoBoy.getId());	
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
