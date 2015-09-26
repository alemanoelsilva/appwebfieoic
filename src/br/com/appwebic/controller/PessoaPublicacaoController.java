package br.com.appwebic.controller;

import java.util.List;

import br.com.appwebic.dao.PessoaPublicacaoDao;
import br.com.appwebic.model.PessoaPublicacao;

public class PessoaPublicacaoController {

	public PessoaPublicacaoController() {}
	
	public List<PessoaPublicacao> getLista() throws Exception{

		List<PessoaPublicacao> lista = null;

		PessoaPublicacaoDao pePuDao = new PessoaPublicacaoDao();
		lista = pePuDao.getLista();
		return lista;
			
	}
	
	public void excluirPessoaPublicacao(PessoaPublicacao pessoaPublicacao) throws Exception{
		
		PessoaPublicacaoDao pPDao = new PessoaPublicacaoDao();
		pPDao.delete(pessoaPublicacao);
	}
	
	public void adicionaPessoaPublicacao(PessoaPublicacao pessoaPublicacao) throws Exception{
		
		PessoaPublicacaoDao pPDao = new PessoaPublicacaoDao();
		pPDao.save(pessoaPublicacao);
	
	}
	
	public void alterarPessoaPublicacao(PessoaPublicacao pessoaPublicacao){
		
		try {
			PessoaPublicacaoDao.update(pessoaPublicacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static PessoaPublicacao getPessoaPublicacao(int idPublicacao){
		
		PessoaPublicacao pepu = new PessoaPublicacao();
		try {
			pepu = PessoaPublicacaoDao.getPessoaPublicacao(idPublicacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pepu;
	}

}
