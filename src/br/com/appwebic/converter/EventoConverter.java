package br.com.appwebic.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.appwebic.dao.EventoDao;
import br.com.appwebic.model.Evento;

@FacesConverter(value = "eventoConverter")
public class EventoConverter implements Converter{
	
    EventoDao eDao = new EventoDao();
	
	public EventoConverter(){}

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String str){
	   
		if (str == null || str.isEmpty()){
			return null;
		}
		
		int id = Integer.valueOf(str);
		
		Evento e = new Evento();
		
		try {
			e = eDao.getIdIntEvento(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	    return e;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object obj){
	    
		Evento e = (Evento) obj;
		
		if (e == null || e.getId() == null){
			return null;
		}

	    return String.valueOf(e.getId());

	}

}
