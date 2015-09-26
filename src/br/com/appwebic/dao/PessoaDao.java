package br.com.appwebic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.appwebic.model.Pessoa;

public class PessoaDao {
	
	public PessoaDao() {}
	
	public int getCpf(String cpf) throws Exception {
		String sql ="SELECT * FROM pessoa p WHERE p.cpf_pessoa = ?";
		
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);

	    stmt.setString(1, cpf);

		ResultSet rs = stmt.executeQuery();	
	  		
		if (rs.next()){
    	    	
			int id = rs.getInt("p.id_pessoa");

			rs.close();
	    	stmt.close();
	    	conn.close();
			return id;
		}else{
	    	rs.close();
	    	stmt.close();
	    	conn.close();
			return -1;
		}

	}
	
	public int getEmail(String email) throws Exception {
		String sql ="SELECT * FROM pessoa p WHERE p.email_pessoa = ?";
		
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);

	    stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();	
	  		
		if (rs.next()){
    	    	
			int id = rs.getInt("p.id_pessoa");

			rs.close();
	    	stmt.close();
	    	conn.close();
			return id;
		}else{
	    	rs.close();
	    	stmt.close();
	    	conn.close();
			return -1;
		}

	}
	
	public Pessoa logar(String senha, String email) throws Exception {
		String sql ="SELECT * FROM pessoa p WHERE p.email_pessoa = ? AND p.senha_pessoa = ?";
		
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);

	    stmt.setString(1, email);
	    stmt.setString(2, senha);

		ResultSet rs = stmt.executeQuery();	
	   
    	Pessoa p = new Pessoa();
		
		if (rs.next()){
    	    	
			p.setId(rs.getInt("p.id_pessoa"));
			p.setNome(rs.getString("p.nome_pessoa"));
			p.setCpf(rs.getString("p.cpf_pessoa"));
			p.setTitulo(rs.getString("p.titulo_pessoa"));
			p.setEmail(rs.getString("p.email_pessoa"));
			p.setSenha(rs.getString("p.senha_pessoa"));
			p.setPais(rs.getString("p.pais_pessoa"));
			p.setEstado(rs.getString("p.estado_pessoa"));
			p.setCidade(rs.getString("p.cidade_pessoa"));
			p.setSituacao(rs.getInt("p.situacao_pessoa"));
			Date data = (rs.getDate("p.data_hora_cadastro"));
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
			String date = formato.format(data);
			p.setDataHoraCadastro(date); 
			rs.close();
	    	stmt.close();
	    	conn.close();
			return p;
		}else{
	    	rs.close();
	    	stmt.close();
	    	conn.close();
			return null;
		}

	}
	
	public Pessoa getById(int id) throws Exception {
		String sql ="SELECT * FROM pessoa p WHERE p.id_pessoa = ?";
		
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);

	    stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();	
	   
		Pessoa p = new Pessoa();
		
		while (rs.next()){
				
			p.setId(rs.getInt("p.id_pessoa"));
			p.setNome(rs.getString("p.nome_pessoa"));
			p.setCpf(rs.getString("p.cpf_pessoa"));
			p.setTitulo(rs.getString("p.titulo_pessoa"));
			p.setEmail(rs.getString("p.email_pessoa"));
			p.setSenha(rs.getString("p.senha_pessoa"));
			p.setPais(rs.getString("p.pais_pessoa"));
			p.setEstado(rs.getString("p.estado_pessoa"));
			p.setCidade(rs.getString("p.cidade_pessoa"));
			p.setSituacao(rs.getInt("p.situacao_pessoa"));
			Date data = (rs.getDate("p.data_hora_cadastro"));
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
			String date = formato.format(data);
			p.setDataHoraCadastro(date);  
		}
		rs.close();
		rs.close();
	    ConnectionFactory.closeConnection(conn);
	
		return p;
		
	}
			
	public List<Pessoa> getLista() {
		
		String sql ="SELECT * FROM pessoa p";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List <Pessoa> pessoa = new ArrayList <Pessoa>();

		try {
		     conn = ConnectionFactory.getConnection();		
			 ps = conn.prepareStatement(sql);		
		     rs = ps.executeQuery();
			
			 while (rs.next()){
				
			    Pessoa p = new Pessoa();
			    p.setId(rs.getInt("p.id_pessoa"));
			    p.setNome(rs.getString("p.nome_pessoa"));
			    p.setCpf(rs.getString("p.cpf_pessoa"));
			    p.setTitulo(rs.getString("p.titulo_pessoa"));
			    p.setEmail(rs.getString("p.email_pessoa"));
			    p.setPais(rs.getString("p.pais_pessoa"));
			    p.setEstado(rs.getString("p.estado_pessoa"));
			    p.setCidade(rs.getString("p.cidade_pessoa"));
				p.setSituacao(rs.getInt("p.situacao_pessoa"));
			    Date data = (rs.getDate("p.data_hora_cadastro"));
			    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
			    String date = formato.format(data);
			    p.setDataHoraCadastro(date);  
	
				pessoa.add(p);
     		}
		
		}catch(Exception e){
				e.printStackTrace();
		}finally {
			   if (conn != null){
				   try{     
				       rs.close();
				       ps.close();
				       ConnectionFactory.closeConnection(conn);
				   }catch (SQLException ex){
					   ex.printStackTrace();
				   }catch (Exception ex2){
					   ex2.printStackTrace();
				   }  
			   }
		}
		return pessoa;

	}
	
	public void save(Pessoa p) throws Exception{
		 
	    String sql = "INSERT INTO pessoa (nome_pessoa, cpf_pessoa, titulo_pessoa, email_pessoa, senha_pessoa, pais_pessoa, "
	    		+ "estado_pessoa, cidade_pessoa, situacao_pessoa, data_hora_cadastro) "
	            +    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setString(1, p.getNome());
	    stmt.setString(2, p.getCpf());
	    stmt.setString(3, p.getTitulo());
	    stmt.setString(4, p.getEmail());
	    stmt.setString(5, p.getSenha());
	    stmt.setString(6, p.getPais());
	    stmt.setString(7, p.getEstado());
	    stmt.setString(8, p.getCidade());
	    stmt.setInt(9, p.getSituacao());
	    Date data = new Date(System.currentTimeMillis());    
	    SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");  
	    stmt.setString(10,  formatarDate.format(data));

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void delete(Pessoa p) throws Exception{
		 
	    String sql = "DELETE FROM pessoa WHERE id_pessoa = ? ";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setInt(1, p.getId());

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void update(Pessoa p) throws Exception {
		
		String sql = "UPDATE pessoa SET nome_pessoa = ?, cpf_pessoa = ?, titulo_pessoa = ?, email_pessoa = ?, "
				+ "pais_pessoa = ?, estado_pessoa = ?, cidade_pessoa = ?, situacao_pessoa = ? WHERE id_pessoa = ?";
		
		Connection conn = ConnectionFactory.getConnection();		

		PreparedStatement stmt = conn.prepareStatement(sql);
		
	    stmt.setString(1, p.getNome());
	    stmt.setString(2, p.getCpf());
	    stmt.setString(3, p.getTitulo());
	    stmt.setString(4, p.getEmail());
	    stmt.setString(5, p.getPais());
	    stmt.setString(6, p.getEstado());
	    stmt.setString(7, p.getCidade());
	    stmt.setInt(8, p.getSituacao());
	    stmt.setInt(9, p.getId());
        stmt.execute();
        stmt.close();
        conn.close();
				
	}

}
