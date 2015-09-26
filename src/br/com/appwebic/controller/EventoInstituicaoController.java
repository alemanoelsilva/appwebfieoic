package br.com.appwebic.controller;

import java.util.List;

import br.com.appwebic.dao.EventoInstituicaoDao;
import br.com.appwebic.model.Evento;
import br.com.appwebic.model.EventoInstituicao;

public class EventoInstituicaoController {

	public EventoInstituicaoController() {}
	
	public List<EventoInstituicao> getLista() throws Exception{

		List<EventoInstituicao> lista = null;

		EventoInstituicaoDao eiDao = new EventoInstituicaoDao();
		lista = eiDao.getLista();
		return lista;
			
	}

	public static void excluirEventoInstituicao(EventoInstituicao eventoInstituicao) throws Exception{

		EventoInstituicaoDao evInst = new EventoInstituicaoDao();
		evInst.delete(eventoInstituicao);

	}
	
    public static void adicionaEventoInstituicao(EventoInstituicao eventoInstituicao) throws Exception{

		EventoInstituicaoDao evInstDao = new EventoInstituicaoDao();
		evInstDao.save(eventoInstituicao);

	}
	
	public int getInstituicao(Evento evento){
		
		EventoInstituicaoDao evInstD = new EventoInstituicaoDao();
		int idInstituicao = 0;
		try {
			idInstituicao = evInstD.getInstituicao(evento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idInstituicao;
		
	}
	
}
