package br.com.appwebic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.appwebic.model.Instituicao;

public class InstituicaoDao {
	
	public InstituicaoDao(){}
	
	public int getCnpj(String cnpj ) throws Exception{
		String sql ="SELECT * FROM instituicao i WHERE cnpj_instituicao = ?";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		
	    ps.setString(1, cnpj);

		ResultSet rs = ps.executeQuery();	
		
		if (rs.next()){
	    	
			int id = rs.getInt("i.id_instituicao");

			rs.close();
	    	ps.close();
	    	conn.close();
			return id;
		}else{
	    	rs.close();
	    	ps.close();
	    	conn.close();
			return -1;
		}
	}
	
	public static Instituicao getIdInstituicao(int id) throws Exception{
		String sql ="SELECT * FROM instituicao i WHERE id_instituicao = ?";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		
	    ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();	
		
	    Instituicao i = new Instituicao();
		
		rs.next();

		i.setId(rs.getInt("i.id_instituicao"));
		i.setNome(rs.getString("i.nome_instituicao"));
		i.setCnpj(rs.getString("i.cnpj_instituicao"));
		i.setPais(rs.getString("i.pais_instituicao"));
		i.setEstado(rs.getString("i.estado_instituicao"));
		i.setCidade(rs.getString("i.cidade_instituicao"));
		i.setTipo(rs.getString("i.tipo_instituicao"));
		Date data;
	    data = (rs.getDate("i.data_hora_cadastro"));
	    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
	    String date = formato.format(data);
	    i.setDataHoraCadastro(date); 
	    
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		
		return i;
	}
	
	public List<Instituicao> getLista(){
		String sql ="SELECT * FROM instituicao i";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List <Instituicao> instituicao = new ArrayList <Instituicao>();

		try {
			conn = ConnectionFactory.getConnection();		
			ps = conn.prepareStatement(sql);		
			rs = ps.executeQuery();	
		
		
			while (rs.next()){
			
				Instituicao i = new Instituicao();
				i.setId(rs.getInt("i.id_instituicao"));
				i.setNome(rs.getString("i.nome_instituicao"));
				i.setCnpj(rs.getString("i.cnpj_instituicao"));
				i.setPais(rs.getString("i.pais_instituicao"));
				i.setEstado(rs.getString("i.estado_instituicao"));
				i.setCidade(rs.getString("i.cidade_instituicao"));
				i.setTipo(rs.getString("i.tipo_instituicao"));
				Date data;
			    data = (rs.getDate("i.data_hora_cadastro"));
			    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
			    String date = formato.format(data);
			    i.setDataHoraCadastro(date);  
				
				instituicao.add(i);						
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (conn != null){
				try {
					rs.close();
					ps.close();
					ConnectionFactory.closeConnection(conn);
				}catch(SQLException e){
					e.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return instituicao;
	}
	
	public void save(Instituicao i) throws Exception{
 
	    String sql = "INSERT INTO instituicao "
	    + "(nome_instituicao, pais_instituicao, estado_instituicao, cidade_instituicao, "
	    +   "tipo_instituicao, cnpj_instituicao, data_hora_cadastro) "
	    +    "VALUES (?, ?, ?, ?, ?, ?, ?)";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setString(1, i.getNome());
	    stmt.setString(2, i.getPais());
	    stmt.setString(3, i.getEstado());
	    stmt.setString(4, i.getCidade());
	    stmt.setString(5, i.getTipo());
	    stmt.setString(6, i.getCnpj());
	    Date data = new Date(System.currentTimeMillis());    
	    SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");  
	    stmt.setString(7,  formatarDate.format(data));

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void delete(Instituicao i) throws Exception{
		 
	    String sql = "DELETE FROM instituicao WHERE id_instituicao = ? ";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setInt(1, i.getId());

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void update(Instituicao i) throws Exception {
		
		String sql = "UPDATE instituicao SET nome_instituicao = ?, pais_instituicao = ?, estado_instituicao = ?, cidade_instituicao = ?, "
				+ "tipo_instituicao = ?, cnpj_instituicao = ? WHERE id_instituicao = ?";
		
		Connection conn = ConnectionFactory.getConnection();		

		PreparedStatement stmt = conn.prepareStatement(sql);
		
        stmt.setString(1, i.getNome());
        stmt.setString(2, i.getPais());
        stmt.setString(3, i.getEstado());
        stmt.setString(4, i.getCidade());
        stmt.setString(5, i.getTipo());
        stmt.setString(6, i.getCnpj());
        stmt.setInt(7, i.getId());
        stmt.execute();
        stmt.close();
				
	}
	
}
