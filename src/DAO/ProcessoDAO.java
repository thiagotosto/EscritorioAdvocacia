package DAO;

import java.sql.*;

import javax.swing.*;

import modelo.Processo;
import modelo.Cliente;
import modelo.Advogado;

public class ProcessoDAO {

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
	
	public Processo[] consultaTodos ()
	{
		int totalProcessos = 1;
		Processo[] p1 = new Processo[1];
	    try {  	  
	     	String query = "SELECT * FROM processo Order by idprocesso";
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(idprocesso)FROM processo");
	       	con.commit();
	       	if (rs.next()) totalProcessos = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	     	con.commit();
	     	Processo[] p = new Processo[totalProcessos];
	       	int i = 0;
	   	    
	       	while (rs.next()) {
	   	      p[i] = new Processo();	
	          p[i].setId(rs.getInt("idprocesso"));    
	          p[i].setNumero(rs.getInt("numero"));
	          p[i].setDescricao(rs.getString("descricao"));
	          i++;
	        }
	        return p;
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	   return p1;   
	}
	
	public Processo[] consultaTodos (Advogado advogado)
	{
		int totalProcessos = 1;
		Processo[] p1 = new Processo[1];
	    try {  	  
	     	String query = "SELECT * FROM processo p INNER JOIN processo_advogado pa ON p.idprocesso = pa.idpeticao WHERE pa.idadvogado = "+ advogado.getIdAdvogado() +" Order by idprocesso";
	       	ResultSet rs = stmt.executeQuery("SELECT COUNT(p.idprocesso)FROM processo p INNER JOIN processo_advogado pa ON p.idprocesso = pa.idpeticao WHERE pa.idadvogado = " + advogado.getIdAdvogado());
	       	con.commit();
	       	if (rs.next()) totalProcessos = rs.getInt(1);
	     	rs = stmt.executeQuery(query);
	     	con.commit();
	     	Processo[] p = new Processo[totalProcessos];
	       	int i = 0;
	   	    
	       	while (rs.next()) {
	   	      p[i] = new Processo();	
	          p[i].setId(rs.getInt("idprocesso"));    
	          p[i].setNumero(rs.getInt("numero"));
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
	      }String
	   return p;   
	}*/
	
	public Processo consultaPorNumero (int numero) 
	{
		Processo p = null;
	    try {  	  
	     	String query = "SELECT * FROM processo WHERE numero = '" + numero + "'";
	       	ResultSet rs = stmt.executeQuery(query);
	       	con.commit();
	       	
	       	if (rs.next()) 
	   	    {
	   	      p = new Processo();	
	          p.setId(rs.getInt("idprocesso"));
	          p.setNumero(rs.getInt("numero"));
	          p.setDescricao(rs.getString("descricao"));          
	        }
	       	
	       	
	       	//TRAZENDO CAMINHOS ----------------------------------------------------------------------------------------
	       	
	       	int totalCaminhos = 1;
	       	int i = 0;
	       	
	       	//contando caminhos
	       	rs = stmt.executeQuery("SELECT COUNT(idprocesso) FROM processo_documento WHERE idprocesso = '" + p.getId() + "'");
	       	con.commit();
	       	
	       	//gurdando quantidade de caminhos
	       	if (rs.next()) totalCaminhos = rs.getInt(1);
	       	
	       	query = "SELECT idprocesso_documento, caminho, descricao FROM processo_documento WHERE  idprocesso = '" + p.getId() + "'";
	       	rs = stmt.executeQuery(query);
	       	con.commit();
	       	String[][] caminhos = new String[totalCaminhos][3];

	       	//populando vetor com caminhos
	       	while (rs.next()) 
	   	    {
	       		caminhos[i][0] = Integer.toString(rs.getInt("idprocesso_documento")); //coletando idprocesso mque é uma String e passando para int
	       		caminhos[i][1] = rs.getString("caminho");
	       		caminhos[i][2] = rs.getString("descricao");
	       		i++;
	        }
	       	
	    	p.setDocumentos(caminhos);
	    	
	        //TRAZENDO CLIENTES ------------------------------------------------------------------------------------------
	       	
	    	int totalClientes = 1;
	       	i = 0;
	       	
	       	//contando clientes
	       	rs = stmt.executeQuery("SELECT COUNT(idprocesso) FROM processo_cliente WHERE idprocesso = '" + p.getId() + "'");
	       	con.commit();
	       	
	       	//gurdando quantidade de caminhos
	       	if (rs.next()) totalClientes = rs.getInt(1);
	       	
	       	query = "SELECT pc.idcliente, nome, cpf  FROM processo_cliente pc INNER JOIN  cliente c ON c.idcliente = pc.idcliente WHERE pc.idprocesso = '" + p.getId() + "'";
	       	rs = stmt.executeQuery(query);
	       	con.commit();
	       	Cliente[] clientes = new Cliente[totalClientes];

	       	//populando vetor com clientes
	       	while (rs.next()) 
	   	    {
	       		clientes[i] = new Cliente();
	       		clientes[i].setId((rs.getInt("idcliente")));
	       		clientes[i].setNome(rs.getString("nome"));
	       		clientes[i].setCpf(rs.getString("cpf"));
	       		i++;
	        }
	       	
	       	p.setClientes(clientes);	       
	       	
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
	     	String sql = "DELETE FROM processo WHERE idprocesso = ";
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
				String sql = "insert into processo (numero,descricao) values(?,?)";
			
				// Passa a string para o PreparedStatement
				pstm = con.prepareStatement(sql);
			
				// Coloca os verdadeiros valores no lugar dos ?
				pstm.setInt(1, Processo.getNumero());		
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

	public void inserir(Processo Processo, Advogado advogado) {
		
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		try {
				// Monta a string sql
				String sql = "insert into processo (numero,descricao) values(?,?)";
			
				// Passa a string para o PreparedStatement
				pstm = con.prepareStatement(sql);
			
				// Coloca os verdadeiros valores no lugar dos ?
				pstm.setInt(1, Processo.getNumero());		
				pstm.setString(2, Processo.getDescricao());
				
				// Executa
				pstm.execute();
				con.commit();
				
				sql = "insert into processo_advogado (idadvogado, idpeticao) values (?, LAST_INSERT_ID())";
				
				pstm = con.prepareStatement(sql);
				
				pstm.setInt(1, advogado.getIdAdvogado());
				
				pstm.execute();
				con.commit();
			
			 } catch (SQLException e) {
				 // Retorna uma mensagem informando o erro
				 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
				                               + e, "Inserir", JOptionPane.ERROR_MESSAGE);
				 e.printStackTrace();
		 }
	
	}	

	
	//insere documento novo
	public void inserirDocumentos(Processo p, String[][] caminhos) {
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		try {
			for (int i = 0; i < caminhos.length; i++) {
				//monta string
				String sql = "INSERT INTO processo_documento (idprocesso, caminho, descricao) VALUES (?,?,?)";
				
				pstm = con.prepareStatement(sql);
				
				pstm.setInt(1, p.getId());
				pstm.setString(2, caminhos[i][0]);
				pstm.setString(3, caminhos[i][1]);
				
				//executa
				pstm.execute();
				con.commit();
			}
		} catch (SQLException e) {
			// Retorna uma mensagem informando o erro
			 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
			                               + e, "Inserir", JOptionPane.ERROR_MESSAGE);
			 e.printStackTrace();
		}	
	}
	
	public void inserirClientes(Processo p, Cliente[] clientes) {
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		try {
			for (int i = 0; i < clientes.length; i++) {
				//monta string
				String sql = "INSERT INTO processo_cliente (idprocesso, idcliente) VALUES (?,?)";
				
				pstm = con.prepareStatement(sql);
				
				pstm.setInt(1, p.getId());
				pstm.setInt(2, clientes[i].getId());
				
				
				//executa
				pstm.execute();
				con.commit();
			}
		} catch (SQLException e) {
			// Retorna uma mensagem informando o erro
			 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
			                               + e, "Inserir", JOptionPane.ERROR_MESSAGE);
			 e.printStackTrace();
		}			
	}
	
	public int  retirarCliente(Processo p,Cliente c) {
		int resultado = -1;
		PreparedStatement pstm = null;
		
		try {  	  
	     	String sql = "DELETE FROM processo_cliente WHERE idprocesso = ? AND idcliente = ?";
	     	
	     	pstm = con.prepareStatement(sql);
	     	
	     	pstm.setInt(1, p.getId());
	     	pstm.setInt(2, c.getId());
	     	
	       	resultado = pstm.executeUpdate();  
	       	con.commit();
	      }  catch (SQLException e) {
	        System.err.print("Erro no SQL: " + e.getMessage());
	      }
	    return resultado;
	}
	
	public void atualizar (Processo Processo) {
	
		// Cria um PreparedStatement
		PreparedStatement pstm = null;
		
		conexaoBD ();
		 try {
			// Monta a string sql
			String sql = "update processo set numero = ?, descricao= ? where idprocesso = ?";
		
			// Passa a string para o PreparedStatement
			pstm = con.prepareStatement(sql);
			
			// Coloca os verdadeiros valores no lugar dos ?
			pstm.setInt(1, Processo.getNumero());
			pstm.setString(2, Processo.getDescricao());
			pstm.setInt(3, Processo.getId());	
			
			// Executa
			pstm.execute();
			
			
			//atualizando documentos
			
			for (int i = 0; i < Processo.getDocumentos().length; i++) {
				//Monta a string sql
				sql = "UPDATE processo_documento SET caminho = ?, descricao = ? where idprocesso_documento = ?";
				
				//passa string para o PreparedStatement
				pstm = con.prepareStatement(sql);
				
				//Coloca os verdadeiros valores no lugar dos ?
				pstm.setString(1, Processo.getDocumentos()[i][1]);
				pstm.setString(2, Processo.getDocumentos()[i][2]);
				pstm.setInt(3, Integer.valueOf(Processo.getDocumentos()[i][0])); //convertendo idprocesso para int para persistir no banco

				//executando
				pstm.execute();
				
			}
			
			//atualizando clientes
			for (int i = 0; i < Processo.getClientes().length; i++) {
				//Monta a string sql
				sql = "UPDATE processo_cliente SET idcliente = ? where idprocesso = ?";
				
				//passa string para o PreparedStatement
				pstm = con.prepareStatement(sql);
				
				//Coloca os verdadeiros valores no lugar dos ?
				pstm.setInt(1, Processo.getClientes()[i].getId());
				pstm.setInt(2, Processo.getId());
				
				

				//executando
				pstm.execute();
			}
			
			//comittando trasaction
			con.commit();
			
		 } catch (SQLException e) {
		// Retorna uma mensagem informando o erro
		 JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!\nInformações sobre o erro:"
		                               + e, "Atualizarr", JOptionPane.ERROR_MESSAGE);
		 e.printStackTrace();
		 }
	}
}
