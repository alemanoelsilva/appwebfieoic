package br.com.appwebic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.appwebic.model.Anais;
import br.com.appwebic.model.Evento;


public class AnaisDao {

	public AnaisDao() {}
	
	public Anais getIdIntAnais(int id) throws Exception {
		String sql ="SELECT * FROM anais a WHERE a.id_anais = ?";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);	
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();	
		
		
		rs.next();
			
		Anais a = new Anais();			
		a.setId(rs.getInt("a.id_anais"));
		a.setNome(rs.getString("a.nome_anais"));
		a.setArea(rs.getString("a.area_anais"));
		a.setEditora(rs.getString("a.editora_anais"));
			
		Date data;

		data = (rs.getDate("a.data_hora_cadastro"));
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
		String date = formato.format(data);
		a.setDataHoraCadastro(date);  
		    
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		
		return a;
	}
	
	public List<Anais> getAnaisName(String nome) throws Exception {
		String sql ="SELECT * FROM anais      a  WHERE UPPER(nome_anais)      LIKE UPPER(?) ";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, "%"+nome+"%");
		
		ResultSet rs = ps.executeQuery();	
		
		List <Anais> anais = new ArrayList <Anais>();
		
		while (rs.next()){
			
			Anais a = new Anais();			
			a.setId(rs.getInt("a.id_anais"));
			a.setNome(rs.getString("a.nome_anais"));
			a.setArea(rs.getString("a.area_anais"));
			a.setEditora(rs.getString("a.editora_anais"));

		    Date data;

		    data = (rs.getDate("a.data_hora_cadastro"));
		    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
		    String date = formato.format(data);
		    a.setDataHoraCadastro(date);  
		   			
			anais.add(a);						
		}
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return anais;
	}

	public List<Anais> getAnaisArea(String area) throws Exception {
		String sql ="SELECT * FROM anais a INNER JOIN evento e ON a.id_evento_anais = e.id_evento WHERE UPPER(area_anais) LIKE UPPER(%?%) ";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);	
		
		ps.setString(1, area);
		
		ResultSet rs = ps.executeQuery();	
		
		List <Anais> anais = new ArrayList <Anais>();
		
		while (rs.next()){
			
			Anais a = new Anais();			
			a.setId(rs.getInt("a.id_anais"));
			a.setNome(rs.getString("a.nome_anais"));
			a.setArea(rs.getString("a.area_anais"));
			a.setEditora(rs.getString("a.editora_anais"));

		    Date data;

		    data = (rs.getDate("a.data_hora_cadastro"));
		    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
		    String date = formato.format(data);
		    a.setDataHoraCadastro(date);  
		    
			Evento e = new Evento();
			e.setId(rs.getInt("e.id_evento"));
			e.setNome(rs.getString("e.nome_evento"));
			e.setArea(rs.getString("e.area_evento"));
			e.setTema(rs.getString("e.tema_evento"));
			e.setPais(rs.getString("e.pais_evento"));
			e.setEstado(rs.getString("e.estado_evento"));
			e.setCidade(rs.getString("e.cidade_evento"));
			e.setCategoria(rs.getString("e.categoria_evento"));
			
		    data = (rs.getDate("e.data_inicio_evento"));
		    formato = new SimpleDateFormat("dd-MM-yyyy");  
		    date = formato.format(data);
		    e.setDataInicio(date);  
		    
		    data = (rs.getDate("e.data_fim_evento"));
		    date = formato.format(data);
		    e.setDataFim(date); 
		    
		    data = (rs.getDate("e.data_inicio_inscricao_evento"));
		    date = formato.format(data);
		    e.setDataInicioInscricao(date); 
		    
		    data = (rs.getDate("e.data_fim_inscricao_evento"));
		    date = formato.format(data);
		    e.setDataFimInscricao(date); 
		    
		    data = (rs.getDate("e.data_hora_cadastro"));
		    date = formato.format(data);
		    e.setDataHoraCadastro(date); 
		    
			a.setEvento(e);	
			
			anais.add(a);						
		}
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return anais;
	}
	
	public List<Anais> getLista() throws Exception {
		String sql ="SELECT * FROM anais a";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		ResultSet rs = ps.executeQuery();	
		
		List <Anais> anais = new ArrayList <Anais>();
		
		while (rs.next()){
			
			Anais a = new Anais();			
			a.setId(rs.getInt("a.id_anais"));
			a.setNome(rs.getString("a.nome_anais"));
			a.setArea(rs.getString("a.area_anais"));
			a.setEditora(rs.getString("a.editora_anais"));

		    Date data;

		    data = (rs.getDate("a.data_hora_cadastro"));
		    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
		    String date = formato.format(data);
		    a.setDataHoraCadastro(date);  
			
			anais.add(a);						
		}
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return anais;
	}
	
	public void save(Anais a) throws Exception{
		 
	    String sql = "INSERT INTO anais (id_evento_anais, nome_anais, area_anais, editora_anais, data_hora_cadastro) "
	    +    "VALUES (?, ?, ?, ?, ?)";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setInt(1, a.getEvento().getId());
	    stmt.setString(2, a.getNome());
	    stmt.setString(3, a.getArea());
	    stmt.setString(4, a.getEditora());
	    Date data = new Date(System.currentTimeMillis());    
	    SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");  
	    stmt.setString(5,  formatarDate.format(data));

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void delete(Anais  a) throws Exception{
		 
	    String sql = "DELETE FROM anais WHERE id_anais = ? ";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setInt(1, a.getId());

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void update(Anais  a) throws Exception {
		
		String sql = "UPDATE anais SET "
				+ "nome_anais = ?, "
				+ "area_anais = ?, "
				+ "editora_anais = ?  WHERE id_anais = ?";
		
		Connection conn = ConnectionFactory.getConnection();		

		PreparedStatement stmt = conn.prepareStatement(sql);
		
        stmt.setString(1, a.getNome());
        stmt.setString(2, a.getArea());
        stmt.setString(3, a.getEditora());
        stmt.setInt(4, a.getId());
        stmt.execute();
        stmt.close();
				
	}
}
