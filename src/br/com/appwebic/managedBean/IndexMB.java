package br.com.appwebic.managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.appwebic.controller.EventoController;
import br.com.appwebic.controller.PublicacaoController;
import br.com.appwebic.model.Evento;
import br.com.appwebic.model.Publicacao;

@SessionScoped
@ManagedBean(name="indexMB")
public class IndexMB {
	
	private String pesquisa = null;
	private int tipo;
	private List<Publicacao> listaPublicacao;
	private List<Evento> listaEvento;
	private Publicacao publicacao;
	private Evento evento;
	private boolean statePublicacao = true;
	private boolean stateEvento = false;

	public IndexMB() {}	

	public String index(){
		return "/index.xhtml?faces-redirect=true";
	}

	public String goAdicionarInstituicao(){
		return "/system/AdicionarInstituicao.xhtml?faces-redirect=true";
	}
	
	public String goAdicionarEvento(){
		return "/system/AdicionarEvento.xhtml?faces-redirect=true";
	}
	
	public String goAdicionarPublicacao(){
		return "/system/AdicionarPublicacao.xhtml?faces-redirect=true";
	}
	
	public String goAdicionarAnais(){
		return "/system/AdicionarAnais.xhtml?faces-redirect=true";
	}
	
	
	public String isListPublicacao(){
		this.pesquisa = null;
		this.statePublicacao = true;
		this.stateEvento = false;
		return "/index.xhtml?faces-redirect=true";
	}
	public String isListEvento(){
		this.pesquisa = null;
		this.statePublicacao = false;
		this.stateEvento = true;
		return "/index.xhtml?faces-redirect=true";
	}
	
	public List<Publicacao> getListaPublicacao() throws Exception{
		PublicacaoController pC = new PublicacaoController();

		try {
			if (this.pesquisa!=null){
				this.listaPublicacao = pC.getListName(this.pesquisa);
			}else{
				this.listaPublicacao = pC.getLista();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.listaPublicacao;
	}
	
	public List<Evento> getListaEvento() throws Exception{
		
		EventoController eC = new EventoController();
		try {
			if (this.pesquisa!=null){
				this.listaEvento = eC.getListName(this.pesquisa);
			}else{
				this.listaEvento = eC.getLista();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.listaEvento;
	}
	
	public String login(){
		return "/login.xhtml?faces-redirect=true";
	}
	
	public String cadastro(){
		return "/AdicionarPessoa.xhtml?faces-redirect=true";
	}
	
	public String manutencao(){
		return "/adm/Manutencao.xhtml?faces-redirect=true";
	}
	
	public String cancelar(){ 
		return "/index.xhtml?faces-redirect=true";
	}

	
	public void efetuarPesquisa() throws Exception{
		
		this.statePublicacao = false;
		this.stateEvento = false;
			
		if (this.tipo == 1){
			this.stateEvento = true;
			getListaEvento();
		}else{
			if (this.tipo == 2){
	    		this.statePublicacao = true;
	    		getListaPublicacao();
	    	}				
		}		
	}
	
	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public boolean isStatePublicacao() {
		return statePublicacao;
	}

	public void setStatePublicacao(boolean statePublicacao) {
		this.statePublicacao = statePublicacao;
	}

	public boolean isStateEvento() {
		return stateEvento;
	}

	public void setStateEvento(boolean stateEvento) {
		this.stateEvento = stateEvento;
	}
	
}
