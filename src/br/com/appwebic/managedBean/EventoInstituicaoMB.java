package br.com.appwebic.managedBean;

import java.util.List;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;

import br.com.appwebic.controller.EventoInstituicaoController;
import br.com.appwebic.model.Evento;
import br.com.appwebic.model.EventoInstituicao;

@CustomScoped("#{conversacao}")
@ManagedBean(name="eventoInstituicaoMB")
public class EventoInstituicaoMB {
	
	EventoInstituicao eventoInstituicao;
	private List<EventoInstituicao> lista;
	
	public List<EventoInstituicao> getLista() throws Exception{

		EventoInstituicaoController evInstC = new EventoInstituicaoController();
		this.lista = evInstC.getLista();	
		return this.lista;
			
	}
	
	public static int getInstituicao(Evento evento){
		
		EventoInstituicaoController evInstC = new EventoInstituicaoController();
		int idInstituicao = evInstC.getInstituicao(evento);
		return idInstituicao;
		
	}

	public EventoInstituicao getEventoInstituicao() {
		return eventoInstituicao;
	}

	public void setEventoInstituicao(EventoInstituicao eventoInstituicao) {
		this.eventoInstituicao = eventoInstituicao;
	}
	


	
}
