package br.com.appwebic.util;

import javax.faces.context.FacesContext;

public class SessionUtil {

	public SessionUtil() {}
	
	public static void addItem(String name, Object object){ 
	
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(name, object);
		
	} 

	public static Object getItem(String name){ 
		if (FacesContext.getCurrentInstance() == null){ 
		    return null; 
		} 
		
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(name); 
    } 	

}
