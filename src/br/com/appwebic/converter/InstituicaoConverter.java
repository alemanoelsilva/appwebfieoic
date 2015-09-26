package br.com.appwebic.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.appwebic.dao.InstituicaoDao;
import br.com.appwebic.model.Instituicao;

@FacesConverter(value = "instituicaoConverter")
public class InstituicaoConverter implements Converter{
	
    InstituicaoDao iDao = new InstituicaoDao();
	
	public InstituicaoConverter(){}

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String str){
	   
		if (str == null || str.isEmpty()){
			return null;
		}
		
		int id = Integer.valueOf(str);
		
		Instituicao i = new Instituicao();
		
		try {
			i = InstituicaoDao.getIdInstituicao(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    return i;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object obj){
	    
		Instituicao i = (Instituicao) obj;
		
		if (i == null || i.getId() == null){
			return null;
		}

	    return String.valueOf(i.getId());

	}

}
