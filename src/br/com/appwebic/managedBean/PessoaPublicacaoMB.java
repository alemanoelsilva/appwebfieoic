package br.com.appwebic.managedBean;

import java.util.List;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;

import br.com.appwebic.controller.PessoaPublicacaoController;
import br.com.appwebic.model.PessoaPublicacao;

@CustomScoped("#{conversacao}")
@ManagedBean(name="pessoaPublicacaoMB")
public class PessoaPublicacaoMB {
	
	PessoaPublicacao pessoaPublicacao;
	private List<PessoaPublicacao> lista;
	
	public List<PessoaPublicacao> getLista() throws Exception{

		PessoaPublicacaoController pePuC = new PessoaPublicacaoController();
		this.lista = pePuC.getLista();	
		return this.lista;
			
	}

	public PessoaPublicacao getPessoaPublicacao() {
		return pessoaPublicacao;
	}

	public void setPessoaPublicacao(PessoaPublicacao pessoaPublicacao) {
		this.pessoaPublicacao = pessoaPublicacao;
	}
	


	
}
