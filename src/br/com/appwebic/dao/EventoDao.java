package br.com.appwebic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.appwebic.model.Evento;

public class EventoDao {

	public EventoDao() {}
	
	public Evento getIdIntEvento(int id) throws Exception{
		String sql ="SELECT * FROM evento e WHERE e.id_evento = ?";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		
	    ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();	
		
	    Evento e = new Evento();
		
		rs.next();

		e.setId(rs.getInt("e.id_evento"));
		e.setNome(rs.getString("e.nome_evento"));
		e.setArea(rs.getString("e.area_evento"));
		e.setTema(rs.getString("e.tema_evento"));
		e.setPais(rs.getString("e.pais_evento"));
		e.setEstado(rs.getString("e.estado_evento"));
		e.setCidade(rs.getString("e.cidade_evento"));
		e.setCategoria(rs.getString("e.categoria_evento"));
		e.setLink(rs.getString("e.link_evento"));
		e.setDescricao(rs.getString("e.descricao_evento"));
		e.setValor(rs.getDouble("e.valor_evento"));
		
	    Date data;
	    data = (rs.getDate("e.data_inicio_evento"));
	    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
	    String date = formato.format(data);
	    e.setDataInicio(date);  
	    
	    data = (rs.getDate("e.data_fim_evento"));
	    date = formato.format(data);
	    e.setDataFim(date); 
	    
	    if ((rs.getDate("e.data_inicio_inscricao_evento")) != null){
	    	 data = (rs.getDate("e.data_inicio_inscricao_evento"));
			 date = formato.format(data);
			 e.setDataInicioInscricao(date); 
	    }else{
	    	e.setDataInicioInscricao(null);
	    }
	   
	    if ((rs.getDate("e.data_fim_inscricao_evento")) != null){
	    	 data = (rs.getDate("e.data_fim_inscricao_evento"));
			 date = formato.format(data);
			 e.setDataFimInscricao(date);
	    }else{
	    	e.setDataFimInscricao(null);
	    }
	    
	    data = (rs.getDate("e.data_hora_cadastro"));
	    date = formato.format(data);
	    e.setDataHoraCadastro(date); 
	    
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		
		return e;
	}
	
	public List<Evento> getListaEventoDisp() throws Exception {
		String sql = "SELECT * FROM evento e WHERE e.id_evento NOT IN (SELECT id_evento_anais FROM anais);";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		ResultSet rs = ps.executeQuery();	
		
		List <Evento> evento = new ArrayList <Evento>();
		
		while (rs.next()){
						
		    Date data;
		    
			Evento e = new Evento();
			e.setId(rs.getInt("e.id_evento"));
			e.setNome(rs.getString("e.nome_evento"));
			e.setArea(rs.getString("e.area_evento"));
			e.setTema(rs.getString("e.tema_evento"));
			e.setPais(rs.getString("e.pais_evento"));
			e.setEstado(rs.getString("e.estado_evento"));
			e.setCidade(rs.getString("e.cidade_evento"));
			e.setCategoria(rs.getString("e.categoria_evento"));
			e.setLink(rs.getString("e.link_evento"));
			e.setDescricao(rs.getString("e.descricao_evento"));
			e.setValor(rs.getDouble("e.valor_evento"));
			
		    data = (rs.getDate("e.data_inicio_evento"));
		    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
		    String date = formato.format(data);
		    e.setDataInicio(date);  
		    
		    data = (rs.getDate("e.data_fim_evento"));
		    date = formato.format(data);
		    e.setDataFim(date); 
		    
		    if ((rs.getDate("e.data_inicio_inscricao_evento")) != null){
		    	 data = (rs.getDate("e.data_inicio_inscricao_evento"));
				 date = formato.format(data);
				 e.setDataInicioInscricao(date); 
		    }else{
		    	e.setDataInicioInscricao(null);
		    }
		   
		    if ((rs.getDate("e.data_fim_inscricao_evento")) != null){
		    	 data = (rs.getDate("e.data_fim_inscricao_evento"));
				 date = formato.format(data);
				 e.setDataFimInscricao(date);
		    }else{
		    	e.setDataFimInscricao(null);
		    }
		    
		    data = (rs.getDate("e.data_hora_cadastro"));
		    date = formato.format(data);
		    e.setDataHoraCadastro(date); 
		    			
		    evento.add(e);						
		}
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return evento;
	}
	
	public int getIdEvento(String nomeEvento) throws Exception{
		String sql ="SELECT * FROM evento WHERE nome_evento = ?";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		
	    ps.setString(1, nomeEvento);

		ResultSet rs = ps.executeQuery();	
		
		if (rs.next()){

			int id = rs.getInt("id_evento");
			
			rs.close();
			ps.close();
			ConnectionFactory.closeConnection(conn);
			return id;
		}
		return -1;
	}
	
	
	public List<Evento> getEventoNome(String nome) throws Exception {
		String sql ="SELECT * FROM evento e WHERE UPPER(nome_evento) LIKE UPPER(?)";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, "%"+nome+"%");

		ResultSet rs = ps.executeQuery();	
		
		List <Evento> evento = new ArrayList <Evento>();
		
		while (rs.next()){
			
			Evento e = new Evento();
			e.setId(rs.getInt("e.id_evento"));
			e.setNome(rs.getString("e.nome_evento"));
			e.setArea(rs.getString("e.area_evento"));
			e.setTema(rs.getString("e.tema_evento"));
			e.setPais(rs.getString("e.pais_evento"));
			e.setEstado(rs.getString("e.estado_evento"));
			e.setCidade(rs.getString("e.cidade_evento"));
			e.setCategoria(rs.getString("e.categoria_evento"));
			e.setLink(rs.getString("e.link_evento"));
			e.setDescricao(rs.getString("e.descricao_evento"));
			e.setValor(rs.getDouble("e.valor_evento"));
			
		    Date data;
		    data = (rs.getDate("e.data_inicio_evento"));
		    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
		    String date = formato.format(data);
		    e.setDataInicio(date);  
		    
		    data = (rs.getDate("e.data_fim_evento"));
		    date = formato.format(data);
		    e.setDataFim(date); 
		    
		    if ((rs.getDate("e.data_inicio_inscricao_evento")) != null){
		    	 data = (rs.getDate("e.data_inicio_inscricao_evento"));
				 date = formato.format(data);
				 e.setDataInicioInscricao(date); 
		    }else{
		    	e.setDataInicioInscricao(null);
		    }
		   
		    if ((rs.getDate("e.data_fim_inscricao_evento")) != null){
		    	 data = (rs.getDate("e.data_fim_inscricao_evento"));
				 date = formato.format(data);
				 e.setDataFimInscricao(date);
		    }else{
		    	e.setDataFimInscricao(null);
		    }
		    
		    
		    data = (rs.getDate("e.data_hora_cadastro"));
		    date = formato.format(data);
		    e.setDataHoraCadastro(date); 
			
			evento.add(e);						
		}
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return evento;
	}
	
	public List<Evento> getLista() throws Exception {
		String sql ="SELECT * FROM evento e";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		ResultSet rs = ps.executeQuery();	
		
		List <Evento> evento = new ArrayList <Evento>();
		
		while (rs.next()){
			
			Evento e = new Evento();
			e.setId(rs.getInt("e.id_evento"));
			e.setNome(rs.getString("e.nome_evento"));
			e.setArea(rs.getString("e.area_evento"));
			e.setTema(rs.getString("e.tema_evento"));
			e.setPais(rs.getString("e.pais_evento"));
			e.setEstado(rs.getString("e.estado_evento"));
			e.setCidade(rs.getString("e.cidade_evento"));
			e.setCategoria(rs.getString("e.categoria_evento"));
			e.setLink(rs.getString("e.link_evento"));
			e.setDescricao(rs.getString("e.descricao_evento"));
			e.setValor(rs.getDouble("e.valor_evento"));
			
		    Date data;
		    data = (rs.getDate("e.data_inicio_evento"));
		    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
		    String date = formato.format(data);
		    e.setDataInicio(date);  
		    
		    data = (rs.getDate("e.data_fim_evento"));
		    date = formato.format(data);
		    e.setDataFim(date); 
		    
		    if ((rs.getDate("e.data_inicio_inscricao_evento")) != null){
		    	 data = (rs.getDate("e.data_inicio_inscricao_evento"));
				 date = formato.format(data);
				 e.setDataInicioInscricao(date); 
		    }else{
		    	e.setDataInicioInscricao(null);
		    }
		   
		    if ((rs.getDate("e.data_fim_inscricao_evento")) != null){
		    	 data = (rs.getDate("e.data_fim_inscricao_evento"));
				 date = formato.format(data);
				 e.setDataFimInscricao(date);
		    }else{
		    	e.setDataFimInscricao(null);
		    }
		    
		    data = (rs.getDate("e.data_hora_cadastro"));
		    date = formato.format(data);
		    e.setDataHoraCadastro(date); 
			
			evento.add(e);						
		}
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return evento;
	}
	
	public void save(Evento e) throws Exception{
		 
	    String sql = "INSERT INTO evento (id_pessoa_evento, nome_evento, area_evento, tema_evento, data_inicio_evento, "
	    		+ "data_fim_evento, pais_evento, estado_evento, cidade_evento, categoria_evento, data_inicio_inscricao_evento, "
	    		+ "data_fim_inscricao_evento, link_evento, descricao_evento, valor_evento, data_hora_cadastro) "
	            +    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    
	    Date data = new Date(System.currentTimeMillis());
	    SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");  
	
	    stmt.setInt(1, e.getPessoa().getId());
	    stmt.setString(2, e.getNome());
	    stmt.setString(3, e.getArea());
	    stmt.setString(4, e.getTema());
	    stmt.setString(5, e.getDataInicio());
	    stmt.setString(6, e.getDataFim());
	    stmt.setString(7, e.getPais());
	    stmt.setString(8, e.getEstado());
	    stmt.setString(9, e.getCidade());
	    stmt.setString(10, e.getCategoria());
		stmt.setString(11, e.getDataInicioInscricao());
		stmt.setString(12, e.getDataFimInscricao());
	    stmt.setString(13, e.getLink());
	    stmt.setString(14, e.getDescricao());
	    stmt.setDouble(15, e.getValor());
	        
	    stmt.setString(16,  formatarDate.format(data));

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void delete(Evento  e) throws Exception{
		 
	    String sql = "DELETE FROM evento WHERE id_evento = ? ";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setInt(1, e.getId());

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void update(Evento e) throws Exception {
		
		String sql = "UPDATE evento SET "
				+ "id_pessoa_evento = ?, "
				+ "nome_evento = ?, "
				+ "area_evento = ?, "
				+ "tema_evento = ?, "
				+ "data_inicio_evento = ?, "
				+ "data_fim_evento = ?, "
				+ "pais_evento = ?, "
				+ "estado_evento = ?, "
				+ "cidade_evento = ?, "
				+ "categoria_evento = ?, "
				+ "data_inicio_inscricao_evento = ?, "
				+ "data_fim_inscricao_evento = ?, "
				+ "link_evento = ?, "
				+ "descricao_evento = ?, "
				+ "valor_evento = ? WHERE id_evento = ?";

		Connection conn = ConnectionFactory.getConnection();		

		PreparedStatement stmt = conn.prepareStatement(sql);
		
	    stmt.setInt(1, e.getPessoa().getId());
	    stmt.setString(2, e.getNome());
	    stmt.setString(3, e.getArea());
	    stmt.setString(4, e.getTema());
	    stmt.setString(5, e.getDataInicio());
	    stmt.setString(6, e.getDataFim());
	    stmt.setString(7, e.getPais());
	    stmt.setString(8, e.getEstado());
	    stmt.setString(9, e.getCidade());
	    stmt.setString(10, e.getCategoria());
	    stmt.setString(11, e.getDataInicioInscricao());
	    stmt.setString(12, e.getDataFimInscricao());
	    stmt.setString(13, e.getLink());
	    stmt.setString(14, e.getDescricao());
	    stmt.setDouble(15, e.getValor());
	    stmt.setInt(16, e.getId());
        stmt.execute();
        stmt.close();
				
	}
}
