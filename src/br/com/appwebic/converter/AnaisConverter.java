package br.com.appwebic.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.appwebic.dao.AnaisDao;
import br.com.appwebic.model.Anais;

@FacesConverter(value = "anaisConverter")
public class AnaisConverter implements Converter{
	
    AnaisDao aDao = new AnaisDao();
	
	public AnaisConverter(){}

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String str){
	   
		if (str == null || str.isEmpty()){
			return null;
		}
		
		int id = Integer.valueOf(str);
		
		Anais a = new Anais();
		
		try {
			a = aDao.getIdIntAnais(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	    return a;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object obj){
	    
		Anais a = (Anais) obj;
		
		if (a == null || a.getId() == null){
			return null;
		}

	    return String.valueOf(a.getId());

	}

}
