package br.com.appwebic.managedBean;

import java.util.List;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;

import br.com.appwebic.controller.PessoaPublicacaoController;
import br.com.appwebic.controller.PublicacaoController;
import br.com.appwebic.conversacao.Conversacao;
import br.com.appwebic.model.Anais;
import br.com.appwebic.model.Pessoa;
import br.com.appwebic.model.PessoaPublicacao;
import br.com.appwebic.model.Publicacao;
import br.com.appwebic.util.SessionUtil;
import br.com.appwebic.validador.PublicacaoValidador;

@CustomScoped("#{conversacao}")
@ManagedBean(name="publicacaoMB")
public class PublicacaoMB {

	private Publicacao publicacao = new Publicacao();
	private List<Publicacao> lista;
	private Anais anais = new Anais();
	private PessoaPublicacao pessoaPublicacao = new PessoaPublicacao();
	private Integer contador;
	
	public String iniciaConversacao() { 
		finalizaConversacao();
		Conversacao.instancia().iniciar(); 
		this.publicacao.setAnais(this.anais);
		this.pessoaPublicacao = PessoaPublicacaoController.getPessoaPublicacao(this.publicacao.getId());

		return "/adm/AlterarPublicacao.xhtml?faces-redirect=true";
	} 
	
	public void finalizaConversacao() { 
		Conversacao.instancia().finalizar(); 
	} 
	
	public String adicionaPublicacao() throws Exception{
		
		boolean validar = PublicacaoValidador.ValidarNome(this.publicacao);
		if (validar == false){
			return "/system/AdicionarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarTipo(this.publicacao);
		if (validar == false){
			return "/system/AdicionarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarTema(this.publicacao);
		if (validar == false){
			return "/system/AdicionarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarArea(this.publicacao);
		if (validar == false){
			return "/system/AdicionarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarCentroPesquisa(this.pessoaPublicacao);
		if (validar == false){
			return "/system/AdicionarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarOrientador(this.pessoaPublicacao);
		if (validar == false){
			return "/system/AdicionarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarLink(this.publicacao);
		if (validar == false){
			return "/system/AdicionarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarDescricao(this.publicacao);
		if (validar == false){
			return "/system/AdicionarPublicacao.xhtml";
		}
		
		if (this.anais != null){
			this.publicacao.setAnais(this.anais);
		}else{
			this.publicacao.setAnais(null);
		}
		PublicacaoController puC = new PublicacaoController();
		int id = puC.adicionaPublicacao(this.publicacao);
		
		// buscar o id da publicacao inserida
		this.publicacao.setId(id);
		
		PessoaPublicacao pP = new PessoaPublicacao();
		
		// obtem pessoa da sessão
		Pessoa p = (Pessoa) SessionUtil.getItem("USER");
		pP.setPessoa(p);
		
		// gravar pessoa x publicacao
		pP.setPublicacao(this.publicacao);
		pP.setNomeOrientador(this.pessoaPublicacao.getNomeOrientador());
		pP.setNomeCentroPesquisa(this.pessoaPublicacao.getNomeCentroPesquisa());

		PessoaPublicacaoController pePuC = new PessoaPublicacaoController();
		pePuC.adicionaPessoaPublicacao(pP);

		return "/index.xhtml?faces-redirect=true";
	}
	
	public List<Publicacao> getLista()throws Exception{

		PublicacaoController puC = new PublicacaoController();

		if (this.lista == null){
			this.lista = puC.getLista();
		}
		return this.lista;
	}
	
	public String alterarPublicacao() throws Exception{
		
		boolean validar = PublicacaoValidador.ValidarNome(this.publicacao);
		if (validar == false){
			return "/adm/AlterarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarTipo(this.publicacao);
		if (validar == false){
			return "/adm/AlterarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarTema(this.publicacao);
		if (validar == false){
			return "/adm/AlterarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarArea(this.publicacao);
		if (validar == false){
			return "/adm/AlterarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarCentroPesquisa(this.pessoaPublicacao);
		if (validar == false){
			return "/adm/AlterarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarOrientador(this.pessoaPublicacao);
		if (validar == false){
			return "/adm/AlterarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarLink(this.publicacao);
		if (validar == false){
			return "/adm/AlterarPublicacao.xhtml";
		}
		
		validar = PublicacaoValidador.ValidarDescricao(this.publicacao);
		if (validar == false){
			return "/adm/AlterarPublicacao.xhtml";
		}
		
		PessoaPublicacaoController pepuC = new PessoaPublicacaoController();
		this.pessoaPublicacao.setPublicacao(this.publicacao);
		pepuC.alterarPessoaPublicacao(this.pessoaPublicacao);

		this.publicacao.setAnais(this.anais);
		PublicacaoController puC = new PublicacaoController();
		puC.alterarPublicacao(this.publicacao);
		
		// finaliza scoped custom
		finalizaConversacao();
		
		return "/adm/Manutencao.xhtml?faces-redirect=true";
	}
	
	public String excluirPublicacao() throws Exception{

		PublicacaoController puC = new PublicacaoController();
		PessoaPublicacaoController pePuC = new PessoaPublicacaoController();

		this.pessoaPublicacao.setPublicacao(this.publicacao);
		pePuC.excluirPessoaPublicacao(this.pessoaPublicacao);
		
		puC.excluirPublicacao(this.publicacao);
		return "/adm/Manutencao.xhtml?faces-redirect=true";
	}
	
	public Publicacao getPublicacao() {
		return this.publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}
	
	public Anais getAnais() {
		return this.anais;
	}

	public void setAnais(Anais anais) {
		this.anais = anais;
	}

	public PessoaPublicacao getPessoaPublicacao() {
		return this.pessoaPublicacao;
	}

	public void setPessoaPublicacao(PessoaPublicacao pessoaPublicacao) {
		this.pessoaPublicacao = pessoaPublicacao;
	}
	public Integer getContador() {
		return contador; 
	} 
	public void setContador(Integer contador) { 
		this.contador = contador; 
	}
}
