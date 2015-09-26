package br.com.appwebic.controller;

import java.util.List;

import br.com.appwebic.dao.InstituicaoDao;
import br.com.appwebic.model.Instituicao;

public class InstituicaoController {

	public InstituicaoController() {}
	
	public void adicionaInstituicao(Instituicao instituicao) throws Exception{
		InstituicaoDao iDao = new InstituicaoDao();
		iDao.save(instituicao);
	}
	
	public List<Instituicao> getLista(){
		
		List<Instituicao> lista = null;
		
		InstituicaoDao iDao = new InstituicaoDao();
		lista = iDao.getLista();	
		
		return lista;
	}
	
	public void alterarInstituicao(Instituicao instituicao) throws Exception{
		InstituicaoDao iDao = new InstituicaoDao();
    	iDao.update(instituicao);
	}
	
	public void excluirInstituicao(Instituicao instituicao) throws Exception{
		InstituicaoDao iDao = new InstituicaoDao();
    	iDao.delete(instituicao);
	}
	
	public static Instituicao getInstituicao(int id){
		
		Instituicao inst = new Instituicao();
		try {
			inst = InstituicaoDao.getIdInstituicao(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inst;
	}

}
