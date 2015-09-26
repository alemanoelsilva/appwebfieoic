package br.com.appwebic.controller;

import java.util.List;

import br.com.appwebic.dao.AnaisDao;
import br.com.appwebic.model.Anais;

public class AnaisController {
	
	private List<Anais> lista;

	public AnaisController() {}
	
	public void adicionaAnais(Anais anais) throws Exception{
		
		AnaisDao aDao = new AnaisDao();
		aDao.save(anais);
		
	}
	
	public List<Anais> getListName(String nome)throws Exception{

		AnaisDao aDao = new AnaisDao();
		this.lista = aDao.getAnaisName(nome);
		return this.lista;
	}
	
	public List<Anais> getLista()throws Exception{

		AnaisDao aDao = new AnaisDao();
		this.lista = aDao.getLista();
		return this.lista;
	}
	
	public void alterarAnais(Anais anais) throws Exception{
		
		AnaisDao aDao = new AnaisDao();
    	aDao.update(anais);
	}
	
	public void excluirAnais(Anais anais) throws Exception{
		
		AnaisDao aDao = new AnaisDao();
		aDao.delete(anais);
	}

}
