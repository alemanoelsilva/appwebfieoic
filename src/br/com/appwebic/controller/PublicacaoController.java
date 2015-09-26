package br.com.appwebic.controller;

import java.util.List;

import br.com.appwebic.dao.PublicacaoDao;
import br.com.appwebic.model.Publicacao;

public class PublicacaoController {

	public PublicacaoController() {}
	
	public int adicionaPublicacao(Publicacao publicacao) throws Exception{
		
		PublicacaoDao puDao = new PublicacaoDao();
		puDao.save(publicacao);
		
		// buscar o id da publicacao inserida
		int id = puDao.getIdPublicacao();
		
		return id;
		
	}
	
	public List<Publicacao> getListName(String nome) {
		
		List<Publicacao> lista = null;
		
		PublicacaoDao pDao = new PublicacaoDao();
		try {
			lista = pDao.getPublicacaoName(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
		
	}
	
	public List<Publicacao> getLista()throws Exception{

		List<Publicacao> lista = null;
		
		PublicacaoDao pDao = new PublicacaoDao();
		lista = pDao.getLista();
		
		return lista;
	}
	
	public void alterarPublicacao(Publicacao publicacao) throws Exception{

		PublicacaoDao pDao = new PublicacaoDao();
    	pDao.update(publicacao);
	}
	
	public void excluirPublicacao(Publicacao publicacao) throws Exception{
	
	    PublicacaoDao pDao = new PublicacaoDao();
		pDao.delete(publicacao);
	}
	
	public static Publicacao getPublicacaoById(int id){
		
		Publicacao p = new Publicacao();
		
		try {
			p = PublicacaoDao.getPublicacaoById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
