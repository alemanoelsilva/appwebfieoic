package br.com.appwebic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.appwebic.model.Evento;
import br.com.appwebic.model.EventoInstituicao;
import br.com.appwebic.model.Instituicao;

public class EventoInstituicaoDao {
	
	public EventoInstituicaoDao(){}
	
	public int getInstituicao(Evento evento) throws Exception {
		String sql ="SELECT * FROM evento_instituicao evin WHERE id_evento = ?";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		
	    ps.setInt(1, evento.getId());

		ResultSet rs = ps.executeQuery();	
		
		rs.next();
			
		int idInstituicao = rs.getInt("evin.id_instituicao");
			
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return idInstituicao;
	}
	
	public List<EventoInstituicao> getLista() throws Exception {
		String sql ="SELECT * FROM evento_instituicao evin "
				           + "INNER JOIN evento e ON evin.id_evento = e.id_evento "
				           + "INNER JOIN instituicao i ON evin.id_instituicao = pu.i.id_instituicao";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		ResultSet rs = ps.executeQuery();	
		
		List <EventoInstituicao> eventoInstituicao = new ArrayList <EventoInstituicao>();
		
		while (rs.next()){
			
			EventoInstituicao evin = new EventoInstituicao();	
			evin.setDataHoraCadastro(rs.getString("evin.data_hora_cadastro"));

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
		    
		    data = (rs.getDate("e.data_inicio_inscricao_evento"));
		    date = formato.format(data);
		    e.setDataInicioInscricao(date); 
		    
		    data = (rs.getDate("e.data_fim_inscricao_evento"));
		    date = formato.format(data);
		    e.setDataFimInscricao(date); 
		    
		    data = (rs.getDate("e.data_hora_cadastro"));
		    date = formato.format(data);
		    e.setDataHoraCadastro(date); 
		    
			Instituicao i = new Instituicao();
			i.setId(rs.getInt("i.id_instituicao"));
			i.setNome(rs.getString("i.nome_instituicao"));
			i.setCnpj(rs.getString("i.cnpj_instituicao"));
			i.setPais(rs.getString("i.pais_instituicao"));
			i.setEstado(rs.getString("i.estado_instituicao"));
			i.setCidade(rs.getString("i.cidade_instituicao"));
			
		    data = (rs.getDate("i.data_hora_cadastro"));
		    date = formato.format(data);
		    i.setDataHoraCadastro(date);  
		    
			evin.setEvento(e);
			evin.setInstituicao(i);
			
			eventoInstituicao.add(evin);						
		}
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return eventoInstituicao;
	}
	
	public void save(EventoInstituicao evin) throws Exception{
		 
	    String sql = "INSERT INTO evento_instituicao (id_evento, id_instituicao, "
	    		+ "data_hora_cadastro) VALUES (?, ?, ?)";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setInt(1, evin.getEvento().getId());
	    stmt.setInt(2, evin.getInstituicao().getId());
	    Date data = new Date(System.currentTimeMillis());    
	    SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");  
	    stmt.setString(3,  formatarDate.format(data));
	    
	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void delete(EventoInstituicao evin) throws Exception{
		 
	    String sql = "DELETE FROM evento_instituicao WHERE id_evento = ? ";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setInt(1, evin.getEvento().getId());

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void update(EventoInstituicao evin) throws Exception {
		
		String sql = "UPDATE evento_instituicao SET "
				+ "id_instituicao = ?  WHERE id_evento = ?";
		
		Connection conn = ConnectionFactory.getConnection();		

		PreparedStatement stmt = conn.prepareStatement(sql);
		
	    stmt.setInt(1, evin.getInstituicao().getId());
	    stmt.setInt(4, evin.getEvento().getId());
        stmt.execute();
        stmt.close();
				
	}

}
