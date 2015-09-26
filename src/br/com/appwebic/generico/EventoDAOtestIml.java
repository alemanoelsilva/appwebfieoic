package br.com.appwebic.generico;

import java.sql.SQLException;
import java.util.List;

import br.com.appwebic.model.Evento;


public class EventoDAOtestIml  extends GenericDAOImp<Evento, Integer> implements EventoDAOtest {

	public EventoDAOtestIml() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Evento objeto) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Evento objeto) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Evento> getLista(Class<Evento> objeto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
