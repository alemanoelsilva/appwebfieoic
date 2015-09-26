package br.com.appwebic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.appwebic.model.Publicacao;


public class PublicacaoDao {

	public PublicacaoDao() {}
	
	public static Publicacao getPublicacaoById(int id) throws Exception {
		String sql ="SELECT * FROM publicacao pu WHERE id_publicacao = ? ";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();	
		
		List <Publicacao> publicacao = new ArrayList <Publicacao>();
		
		rs.next();
			
		Publicacao pu = new Publicacao();	
		pu.setId(rs.getInt("pu.id_publicacao"));
		pu.setNome(rs.getString("pu.nome_publicacao"));
		pu.setTipo(rs.getString("pu.tipo_publicacao"));
		pu.setTema(rs.getString("pu.tema_publicacao"));
		pu.setArea(rs.getString("pu.area_publicacao"));
		pu.setResumo(rs.getString("pu.resumo_publicacao"));
		pu.setLink(rs.getString("pu.link_publicacao"));
			
		Date data;
		data = (rs.getDate("pu.data_hora_cadastro"));
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
		String date = formato.format(data);
		pu.setDataHoraCadastro(date);  
			
		publicacao.add(pu);						
		
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return pu;
	}
	
	public int getIdPublicacao() throws Exception {
		String sql ="SELECT * FROM publicacao pu ORDER BY pu.id_publicacao DESC";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		ResultSet rs = ps.executeQuery();	
				
		rs.next();
			
		int id = rs.getInt("pu.id_publicacao");

		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return id;
	}
	
	public List<Publicacao> getPublicacaoName(String nome) throws Exception {
		String sql ="SELECT * FROM publicacao pu WHERE UPPER(nome_publicacao) LIKE UPPER(?) ";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, "%"+nome+"%");
		
		ResultSet rs = ps.executeQuery();	
		
		List <Publicacao> publicacao = new ArrayList <Publicacao>();
		
		while (rs.next()){
			
			Publicacao pu = new Publicacao();	
			pu.setId(rs.getInt("pu.id_publicacao"));
			pu.setNome(rs.getString("pu.nome_publicacao"));
			pu.setTipo(rs.getString("pu.tipo_publicacao"));
			pu.setTema(rs.getString("pu.tema_publicacao"));
			pu.setArea(rs.getString("pu.area_publicacao"));
			pu.setResumo(rs.getString("pu.resumo_publicacao"));
			pu.setLink(rs.getString("pu.link_publicacao"));
			
		    Date data;
		    data = (rs.getDate("pu.data_hora_cadastro"));
		    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
		    String date = formato.format(data);
		    pu.setDataHoraCadastro(date);  
			
			publicacao.add(pu);						
		}
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return publicacao;
	}
	
	public List<Publicacao> getLista() throws Exception {
		String sql ="SELECT * FROM publicacao pu";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		ResultSet rs = ps.executeQuery();	
		
		List <Publicacao> publicacao = new ArrayList <Publicacao>();
		
		while (rs.next()){
			
			Publicacao pu = new Publicacao();	
			pu.setId(rs.getInt("pu.id_publicacao"));
			pu.setNome(rs.getString("pu.nome_publicacao"));
			pu.setTipo(rs.getString("pu.tipo_publicacao"));
			pu.setTema(rs.getString("pu.tema_publicacao"));
			pu.setArea(rs.getString("pu.area_publicacao"));
			pu.setResumo(rs.getString("pu.resumo_publicacao"));
			pu.setLink(rs.getString("pu.link_publicacao"));

		    Date data;
		    data = (rs.getDate("pu.data_hora_cadastro"));
		    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
		    String date = formato.format(data);
		    pu.setDataHoraCadastro(date); 
			
			publicacao.add(pu);						
		}
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return publicacao;
	}
	
	public void save(Publicacao pu) throws Exception{
		 
	    String sql = "INSERT INTO publicacao (id_anais, nome_publicacao, tipo_publicacao, tema_publicacao, area_publicacao, "
	    		+ "resumo_publicacao, link_publicacao, data_hora_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    if (pu.getAnais() == null){
		    stmt.setString(1, null);
	    }else{
		    stmt.setInt(1, pu.getAnais().getId());
	    }
	    stmt.setString(2, pu.getNome());
	    stmt.setString(3, pu.getTipo());
	    stmt.setString(4, pu.getTema());
	    stmt.setString(5, pu.getArea());
	    stmt.setString(6, pu.getResumo());
	    stmt.setString(7, pu.getLink());

	    Date data = new Date(System.currentTimeMillis());    
	    SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");  
	    stmt.setString(8,  formatarDate.format(data));

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void delete(Publicacao pu) throws Exception{
		 
	    String sql = "DELETE FROM publicacao WHERE id_publicacao = ? ";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setInt(1, pu.getId());

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void update(Publicacao pu) throws Exception {
		
		String sql = "UPDATE publicacao SET "
				+ "id_anais = ?, "
				+ "nome_publicacao = ?, "
				+ "tipo_publicacao = ?, "
				+ "tema_publicacao = ?, "
				+ "area_publicacao = ?, "
				+ "resumo_publicacao = ?, "
				+ "link_publicacao = ? WHERE id_publicacao = ?";
		
		Connection conn = ConnectionFactory.getConnection();		

		PreparedStatement stmt = conn.prepareStatement(sql);
		
	    if (pu.getAnais() == null){
		    stmt.setString(1, null);
	    }else{
		    stmt.setInt(1, pu.getAnais().getId());
	    }	    
	    stmt.setString(2, pu.getNome());
	    stmt.setString(3, pu.getTipo());
	    stmt.setString(4, pu.getTema());
	    stmt.setString(5, pu.getArea());
	    stmt.setString(6, pu.getResumo());
	    stmt.setString(7, pu.getLink());
	    stmt.setInt(8,  pu.getId());
        stmt.execute();
        stmt.close();
				
	}
}
