package br.com.appwebic.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.appwebic.model.Pessoa;
import br.com.appwebic.model.PessoaPublicacao;
import br.com.appwebic.model.Publicacao;

public class PessoaPublicacaoDao {
	
	public PessoaPublicacaoDao(){}
	
	public static PessoaPublicacao getPessoaPublicacao(int id) throws Exception {
		String sql ="SELECT * FROM pessoa_publicacao pepu WHERE pepu.id_publicacao = ?";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();	
				
		rs.next();
			
		PessoaPublicacao pepu = new PessoaPublicacao();	
		pepu.setNomeOrientador(rs.getString("pepu.nome_orientador"));
		pepu.setNomeCentroPesquisa(rs.getString("pepu.nome_centro_pesquisa"));

		    						
		
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return pepu;
	}
	
	public List<PessoaPublicacao> getLista() throws Exception {
		String sql ="SELECT * FROM pessoa_publicacao pepu "
				           + "INNER JOIN pessoa p ON pepu.id_pessoa = p.id_pessoa "
				           + "INNER JOIN publicacao pu ON pepu.id_publicacao = pu.publicacao";
		
		Connection conn = ConnectionFactory.getConnection();		
		PreparedStatement ps = conn.prepareStatement(sql);		
		ResultSet rs = ps.executeQuery();	
		
		List <PessoaPublicacao> pessoaPublicacao = new ArrayList <PessoaPublicacao>();
		
		while (rs.next()){
			
			PessoaPublicacao pepu = new PessoaPublicacao();	
			pepu.setNomeOrientador(rs.getString("pepu.nome_orientador"));
			pepu.setNomeCentroPesquisa(rs.getString("pepu.nome_centro_pesquisa"));

		    Pessoa p = new Pessoa();
		    p.setId(rs.getInt("p.id_pessoa"));
		    p.setNome(rs.getString("p.nome_pessoa"));
		    p.setCpf(rs.getString("p.cpf_pessoa"));
		    p.setTitulo(rs.getString("p.titulo_pessoa"));
		    p.setEmail(rs.getString("p.email_pessoa"));
		    p.setPais(rs.getString("p.pais_pessoa"));
		    p.setEstado(rs.getString("p.estado_pessoa"));
		    p.setCidade(rs.getString("p.cidade_pessoa"));
		    
		    Date data;
		    data = (rs.getDate("p.data_hora_cadastro"));
		    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");  
		    String date = formato.format(data);
		    p.setDataHoraCadastro(date);

		    
			Publicacao pu = new Publicacao();	
			pu.setId(rs.getInt("pu.publicacao"));
			pu.setNome(rs.getString("pu.nome_publicacao"));
			pu.setTipo(rs.getString("pu.tipo_publicacao"));
			pu.setTema(rs.getString("pu.tema_publicacao"));
			pu.setArea(rs.getString("pu.area_publicacao"));
		    data = (rs.getDate("pu.data_hora_cadastro"));
		    formato = new SimpleDateFormat("dd-MM-yyyy"); 
		    date = formato.format(data);
		    pu.setDataHoraCadastro(date); 
		    
			pepu.setPessoa(p);
			pepu.setPublicacao(pu);
			
			pessoaPublicacao.add(pepu);						
		}
		rs.close();
		ps.close();
		ConnectionFactory.closeConnection(conn);
		return pessoaPublicacao;
	}
	
	public void save(PessoaPublicacao pepu) throws Exception{
		 
	    String sql = "INSERT INTO pessoa_publicacao (id_publicacao, id_pessoa, nome_orientador, nome_centro_pesquisa, "
	    		+ "data_hora_cadastro) VALUES (?, ?, ?, ?, ?)";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setInt(1, pepu.getPublicacao().getId());
	    stmt.setInt(2, pepu.getPessoa().getId());
	    stmt.setString(3, pepu.getNomeOrientador());
	    stmt.setString(4, pepu.getNomeCentroPesquisa());
	    Date data = new Date(System.currentTimeMillis());    
	    SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");  
	    stmt.setString(5,  formatarDate.format(data));
	    
	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public void delete(PessoaPublicacao pepu) throws Exception{
		 
	    String sql = "DELETE FROM pessoa_publicacao WHERE id_publicacao = ? ";
	    
		Connection conn = ConnectionFactory.getConnection();		
	
	    PreparedStatement stmt = conn.prepareStatement(sql);
	
	    stmt.setInt(1, pepu.getPublicacao().getId());

	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	}
	
	public static void update(PessoaPublicacao pepu) throws Exception {
		
		String sql = "UPDATE pessoa_publicacao SET "
				+ "nome_orientador = ?, "
				+ "nome_centro_pesquisa = ? WHERE id_publicacao = ?";
		
		Connection conn = ConnectionFactory.getConnection();		

		PreparedStatement stmt = conn.prepareStatement(sql);
		
	    stmt.setString(1, pepu.getNomeOrientador());
	    stmt.setString(2, pepu.getNomeCentroPesquisa());
	    stmt.setInt(3, pepu.getPublicacao().getId());
        stmt.execute();
        stmt.close();
				
	}

}
