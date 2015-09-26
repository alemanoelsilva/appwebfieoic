package br.com.appwebic.validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.appwebic.model.Anais;

public class AnaisValidador {
	
	public static boolean ValidarNome(Anais anais){
	
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(anais.getNome());
	
	    if( matcher.find()){
	    	return true;
	    }else{
			FacesContext.getCurrentInstance().addMessage("idErrorName",new FacesMessage(FacesMessage.SEVERITY_INFO,  
	                       "Erro!" , "Nome inválido")); 
			return false;
		}	    
	}
		
	public static boolean ValidarArea(Anais anais){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(anais.getArea());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorArea",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Área inválida")); 
			return false;
		}
	}

	public static boolean ValidarEditora(Anais anais){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(anais.getEditora());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorEditora",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Editora inválida")); 
			return false;
		}
	}	
}
