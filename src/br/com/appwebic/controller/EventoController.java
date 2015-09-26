package br.com.appwebic.controller;

import java.util.List;

import br.com.appwebic.dao.EventoDao;
import br.com.appwebic.model.Evento;

public class EventoController {
	
	private Evento evento = new Evento();
	private List<Evento> lista;
	private List<Evento> listaEventoDisp;

	public EventoController() {}
	
    public int adicionaEvento(Evento evento) throws Exception{
	
		EventoDao eDao = new EventoDao();
		
		// converter datas
		
		String dataAux[] = evento.getDataInicio().split("/");
		String data = "";
		
		int i = 2;
		
		for (i = 2; i > -1; i--){
			data += dataAux[i];
			if (i == 2 || i == 1){
				data += "/";
			}
		}

		evento.setDataInicio(data); 
		
		String dataAux1[] = evento.getDataFim().split("/");
		
		data = "";
		
		for (i = 2; i > -1; i--){
			data += dataAux1[i];
			if (i == 2 || i == 1){
				data += "/";
			}
		}
		
		evento.setDataFim(data); 
		
		if (evento.getDataInicioInscricao() != ""){
			String dataAux2[] = evento.getDataInicioInscricao().split("/");
			
			data = "";
			
			for (i = 2; i > -1; i--){
				data += dataAux2[i];
				if (i == 2 || i == 1){
					data += "/";
				}
			}
			
			evento.setDataInicioInscricao(data);
			
		}else{
			evento.setDataInicioInscricao(null);
		}

		if (evento.getDataFimInscricao() != ""){
			String dataAux3[] = evento.getDataFimInscricao().split("/");
			
			data = "";
			
			for (i = 2; i > -1; i--){
				data += dataAux3[i];
				if (i == 2 || i == 1){
					data += "/";
				}
			}
			
			evento.setDataFimInscricao(data);
			
		}else{
			evento.setDataFimInscricao(null);

		}
		
		// grava evento
		eDao.save(evento);
		
		// obter id do evento cadastrado
		int id = eDao.getIdEvento(evento.getNome());

		return id;
	}  
    
	public List<Evento> getListName(String nome)throws Exception{

		if (this.lista == null){
			EventoDao eDao = new EventoDao();
			this.lista = eDao.getEventoNome(nome);
		}
		return this.lista;
	}
    
	public List<Evento> getLista()throws Exception{

		if (this.lista == null){
			EventoDao eDao = new EventoDao();
			this.lista = eDao.getLista();
		}
		return this.lista;
	}
	
	public List<Evento> getListaEventoDisp()throws Exception{

		if (this.listaEventoDisp == null){
			EventoDao eDao = new EventoDao();
			this.listaEventoDisp = eDao.getListaEventoDisp();
		}
		return this.listaEventoDisp;
	}
	
	public void alterarEvento(Evento evento) throws Exception{
		
		EventoDao eDao = new EventoDao();
		
		// converter datas
		String dataAux[] = evento.getDataInicio().split("/");
		String data = "";
		
		int i = 2;
		
		for (i = 2; i > -1; i--){
			data += dataAux[i];
			if (i == 2 || i == 1){
				data += "/";
			}
		}

		evento.setDataInicio(data); 
		
		String dataAux1[] = evento.getDataFim().split("/");
		
		data = "";
		
		for (i = 2; i > -1; i--){
			data += dataAux1[i];
			if (i == 2 || i == 1){
				data += "/";
			}
		}
		
		evento.setDataFim(data); 
		
		if (evento.getDataInicioInscricao() != ""){
			String dataAux2[] = evento.getDataInicioInscricao().split("/");
			
			data = "";
			
			for (i = 2; i > -1; i--){
				data += dataAux2[i];
				if (i == 2 || i == 1){
					data += "/";
				}
			}
			
			evento.setDataInicioInscricao(data);
			
		}else{
			evento.setDataInicioInscricao(null);
		}

		if (evento.getDataFimInscricao() != ""){
			String dataAux3[] = evento.getDataFimInscricao().split("/");
			
			data = "";
			
			for (i = 2; i > -1; i--){
				data += dataAux3[i];
				if (i == 2 || i == 1){
					data += "/";
				}
			}
			
			evento.setDataFimInscricao(data);
			
		}else{
			evento.setDataFimInscricao(null);

		}
		
    	eDao.update(evento);
	}
	
	public void excluirEvento(Evento evento) throws Exception{
		
		EventoDao eDao = new EventoDao();
		eDao.delete(this.evento);
	}
    
}
