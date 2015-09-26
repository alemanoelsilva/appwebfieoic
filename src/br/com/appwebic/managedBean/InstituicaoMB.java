package br.com.appwebic.managedBean;

import java.util.List;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;

import br.com.appwebic.controller.InstituicaoController;
import br.com.appwebic.conversacao.Conversacao;
import br.com.appwebic.model.Instituicao;
import br.com.appwebic.validador.InstituicaoValidador;

@CustomScoped("#{conversacao}")
@ManagedBean(name="instituicaoMB")
public class InstituicaoMB {
	 
	private Instituicao instituicao = new Instituicao();
	private List<Instituicao> lista = null;
	
	public String adicionaInstituicao() throws Exception{
		
		boolean validar = InstituicaoValidador.ValidarNome(this.instituicao);
		if (validar == false){
			return "/system/AdicionarInstituicao.xhtml";
		}
		
		validar = InstituicaoValidador.ValidarPais(this.instituicao);
		if (validar == false){
			return "/system/AdicionarInstituicao.xhtml";
		}
		
		validar = InstituicaoValidador.ValidarCidade(this.instituicao);
		if (validar == false){
			return "/system/AdicionarInstituicao.xhtml";
		}
		
		validar = InstituicaoValidador.ValidarCnpj(this.instituicao);
		if (validar == false){
			return "/system/AdicionarInstituicao.xhtml";
		}
		
		validar = InstituicaoValidador.ValidarTipo(this.instituicao);
		if (validar == false){
			return "/system/AdicionarInstituicao.xhtml";
		}
		
		InstituicaoController iC = new InstituicaoController();
		iC.adicionaInstituicao(this.instituicao);
		return "/index.xhtml?faces-redirect=true";
	}
	
	public List<Instituicao> getLista(){
		InstituicaoController iC = new InstituicaoController();

		if (this.lista == null){
			this.lista = iC.getLista();	
			
		}
		return this.lista;
	}
	
	public void finalizaConversacao() { 
		Conversacao.instancia().finalizar(); 
	} 
	
	public String alterar(){
		finalizaConversacao();
		Conversacao.instancia().iniciar(); 
		return "/adm/AlterarInstituicao.xhtml?faces-redirect=true";
	}
	
	public String alterarInstituicao() throws Exception{
		
		boolean validar = InstituicaoValidador.ValidarNome(this.instituicao);
		if (validar == false){
			return "/adm/AlterarInstituicao.xhtml";
		}
		
		validar = InstituicaoValidador.ValidarPais(this.instituicao);
		if (validar == false){
			return "/adm/AlterarInstituicao.xhtml";
		}
		
		validar = InstituicaoValidador.ValidarCidade(this.instituicao);
		if (validar == false){
			return "/adm/AlterarInstituicao.xhtml";
		}
		
		validar = InstituicaoValidador.ValidarCnpj(this.instituicao);
		if (validar == false){
			return "/adm/AlterarInstituicao.xhtml";
		}
		
		validar = InstituicaoValidador.ValidarTipo(this.instituicao);
		if (validar == false){
			return "/adm/AlterarInstituicao.xhtml";
		}
		
		InstituicaoController iC = new InstituicaoController();
		iC.alterarInstituicao(this.instituicao);
		return "/adm/Manutencao.xhtml?faces-redirect=true";
	}
	
	public String excluirInstituicao() throws Exception{
		InstituicaoController iC = new InstituicaoController();
		iC.excluirInstituicao(this.instituicao);
		return "/adm/Manutencao.xhtml?faces-redirect=true";
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
}
