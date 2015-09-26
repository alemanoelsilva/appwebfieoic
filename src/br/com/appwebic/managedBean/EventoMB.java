package br.com.appwebic.managedBean;

import java.util.List;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;

import br.com.appwebic.controller.EventoController;
import br.com.appwebic.controller.EventoInstituicaoController;
import br.com.appwebic.controller.InstituicaoController;
import br.com.appwebic.conversacao.Conversacao;
import br.com.appwebic.model.Evento;
import br.com.appwebic.model.EventoInstituicao;
import br.com.appwebic.model.Instituicao;
import br.com.appwebic.model.Pessoa;
import br.com.appwebic.util.SessionUtil;
import br.com.appwebic.validador.EventoValidador;

@CustomScoped("#{conversacao}")
@ManagedBean(name="eventoMB")
public class EventoMB {

	private Evento evento = new Evento();
	private List<Evento> lista;
	private List<Evento> listaEventoDisp;
	private Instituicao inst = new Instituicao();
	private EventoInstituicao eventoInstituicao = new EventoInstituicao();
	
	public String adicionaEvento() throws Exception{
		
		boolean validar = EventoValidador.ValidarNome(this.evento);
		if (validar == false){
			return "/system/AdicionarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarArea(this.evento);
		if (validar == false){
			return "/system/AdicionarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarTema(this.evento);
		if (validar == false){
			return "/system/AdicionarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarInicio(this.evento);
		if (validar == false){
			return "/system/AdicionarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarFim(this.evento);
		if (validar == false){
			return "/system/AdicionarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarPais(this.evento);
		if (validar == false){
			return "/system/AdicionarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarCidade(this.evento);
		if (validar == false){
			return "/system/AdicionarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarCategoria(this.evento);
		if (validar == false){
			return "/system/AdicionarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarLink(this.evento);
		if (validar == false){
			return "/system/AdicionarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarDescricao(this.evento);
		if (validar == false){
			return "/system/AdicionarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarValor(this.evento);
		if (validar == false){
			return "/system/AdicionarEvento.xhtml";
		}
		
		
		// obtem pessoa da sessão
		Pessoa p =  (Pessoa) SessionUtil.getItem("USER");
		evento.setPessoa(p);
		
		// salva evento
		EventoController eC = new EventoController();
		int id = eC.adicionaEvento(this.evento);
		
		// seta id retornado da inclusao do evento
		evento.setId(id);

		// add eventoinstituicao
		EventoInstituicao evInst = new EventoInstituicao();
		evInst.setEvento(evento);
		evInst.setInstituicao(inst);
		EventoInstituicaoController.adicionaEventoInstituicao(evInst);

		return "/index.xhtml?faces-redirect=true";
	}
	
	public List<Evento> getLista()throws Exception{

		EventoController eC = new EventoController();
		if (this.lista == null){
			this.lista = eC.getLista();
		}
		return this.lista;
	}
	
	public List<Evento> getListaEventoDisp()throws Exception{

		EventoController eC = new EventoController();
		if (this.listaEventoDisp == null){
			this.listaEventoDisp = eC.getListaEventoDisp();
		}
		return this.listaEventoDisp;
	}
	
	public void finalizaConversacao() { 
		Conversacao.instancia().finalizar(); 
	} 
	
	public String alterar(){
		finalizaConversacao();
		Conversacao.instancia().iniciar(); 
		int Idinstituicao = EventoInstituicaoMB.getInstituicao(this.evento);
		
		this.inst = InstituicaoController.getInstituicao(Idinstituicao);
		return "/adm/AlterarEvento.xhtml?faces-redirect=true";
	}
	
	public String alterarEvento() throws Exception{

		boolean validar = EventoValidador.ValidarNome(this.evento);
		if (validar == false){
			return "/adm/AlterarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarArea(this.evento);
		if (validar == false){
			return "/adm/AlterarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarTema(this.evento);
		if (validar == false){
			return "/adm/AlterarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarInicio(this.evento);
		if (validar == false){
			return "/adm/AlterarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarFim(this.evento);
		if (validar == false){
			return "/adm/AlterarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarPais(this.evento);
		if (validar == false){
			return "/adm/AlterarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarCidade(this.evento);
		if (validar == false){
			return "/adm/AlterarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarCategoria(this.evento);
		if (validar == false){
			return "/adm/AlterarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarLink(this.evento);
		if (validar == false){
			return "/adm/AlterarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarDescricao(this.evento);
		if (validar == false){
			return "/adm/AlterarEvento.xhtml";
		}
		
		validar = EventoValidador.ValidarValor(this.evento);
		if (validar == false){
			return "/adm/AlterarEvento.xhtml";
		}
		
		// obtem pessoa da sessão
		Pessoa p = (Pessoa) SessionUtil.getItem("USER");
		evento.setPessoa(p);
		
		
		EventoController eC = new EventoController();
    	eC.alterarEvento(this.evento);
    	
		// alterar eventoinstituicao
		EventoInstituicao evInst = new EventoInstituicao();
		evInst.setEvento(evento);
		evInst.setInstituicao(inst);
		EventoInstituicaoController.adicionaEventoInstituicao(evInst);
    	
		return "/adm/Manutencao.xhtml?faces-redirect=true";
	}
	
	public String excluirEvento() throws Exception{

		EventoController eC = new EventoController();
				
		eventoInstituicao.setEvento(evento);
		EventoInstituicaoController.excluirEventoInstituicao(eventoInstituicao);
		
		eC.excluirEvento(this.evento);
		
		return "/adm/Manutencao.xhtml?faces-redirect=true";
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public Instituicao getInst() {
		return inst;
	}

	public void setInst(Instituicao inst) {
		this.inst = inst;
	}

	public EventoInstituicao getEventoInstituicao() {
		return eventoInstituicao;
	}

	public void setEventoInstituicao(EventoInstituicao eventoInstituicao) {
		this.eventoInstituicao = eventoInstituicao;
	}

}
