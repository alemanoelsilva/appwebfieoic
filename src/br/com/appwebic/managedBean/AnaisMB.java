package br.com.appwebic.managedBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.appwebic.controller.AnaisController;
import br.com.appwebic.conversacao.Conversacao;
import br.com.appwebic.model.Anais;
import br.com.appwebic.model.Evento;
import br.com.appwebic.validador.AnaisValidador;

@CustomScoped("#{conversacao}")
@ManagedBean(name="anaisMB")
public class AnaisMB {

	private Anais anais = new Anais();
	private List<Anais> lista;
	private Evento evento = new Evento();
	
	public String adicionaAnais() throws Exception{
		
		boolean validar = AnaisValidador.ValidarNome(this.anais);
		if (validar == false){
			return "/system/AdicionarAnais.xhtml";
		}
		
		validar = AnaisValidador.ValidarArea(this.anais);
		if (validar == false){
			return "/system/AdicionarAnais.xhtml";
		}
		
		validar = AnaisValidador.ValidarEditora(this.anais);
		if (validar == false){
			return "/system/AdicionarAnais.xhtml";
		}
		
		if (this.evento == null){
			FacesContext.getCurrentInstance().addMessage("idErrorNome",new FacesMessage(FacesMessage.SEVERITY_ERROR,  
					"Erro!","Não existem Eventos disponíveis para cadastro de Anais"));
			return "/system/AdicionarAnais.xhtml";
		}
			
		this.anais.setEvento(this.evento);
		AnaisController aC = new AnaisController();
		aC.adicionaAnais(this.anais);

		return "/index.xhtml?faces-redirect=true";
	}
	
	public List<Anais> getLista()throws Exception{

		AnaisController aC = new AnaisController();
		if (this.lista == null){
			this.lista = aC.getLista();
		}
		return this.lista;
	}
	
	public void finalizaConversacao() { 
		Conversacao.instancia().finalizar(); 
	} 
	
	public String alterar(){
		finalizaConversacao();
		Conversacao.instancia().iniciar(); 
		return "/adm/AlterarAnais.xhtml?faces-redirect=true";
	}
	
	public String alterarAnais() throws Exception{
		
		boolean validar = AnaisValidador.ValidarNome(this.anais);
		if (validar == false){
			return "/adm/AlterarAnais.xhtml";
		}
		
		validar = AnaisValidador.ValidarArea(this.anais);
		if (validar == false){
			return "/adm/AlterarAnais.xhtml";
		}
		
		validar = AnaisValidador.ValidarEditora(this.anais);
		if (validar == false){
			return "/adm/AlterarAnais.xhtml";
		}
		
		AnaisController aC = new AnaisController();
		aC.alterarAnais(this.anais);
		return "/adm/Manutencao.xhtml?faces-redirect=true";
	}
	
	public String excluirAnais() throws Exception{
		
		AnaisController aC = new AnaisController();
		aC.excluirAnais(this.anais);
		return "/adm/Manutencao.xhtml?faces-redirect=true";
	}
	
	public Anais getAnais() {
		return anais;
	}

	public void setAnais(Anais anais) {
		this.anais = anais;
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
