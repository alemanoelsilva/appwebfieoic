package br.com.appwebic.controller;

import java.util.List;

import br.com.appwebic.dao.PessoaDao;
import br.com.appwebic.model.Pessoa;

public class PessoaController {

	public PessoaController() {}
	
	public void adicionaPessoa(Pessoa pessoa) throws Exception{
		
		PessoaDao pDao = new PessoaDao();
		pDao.save(pessoa);
	}
	
	public Pessoa logar(Pessoa pessoa) throws Exception{
		
		PessoaDao pDao = new PessoaDao();
		
		Pessoa p = pDao.logar(pessoa.getSenha(), pessoa.getEmail()); 
		
    	return p;
		
	}
	
	public List<Pessoa> getLista(){

		PessoaDao pDao = new PessoaDao();
		return pDao.getLista();	
	}
	
	public void alterarPessoa(Pessoa pessoa) throws Exception{

		PessoaDao pDao = new PessoaDao();
    	pDao.update(pessoa);
	}
	
	public void excluirPessoa(Pessoa pessoa) throws Exception{

		PessoaDao pDao = new PessoaDao();
    	pDao.delete(pessoa);
	}

}
