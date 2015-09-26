package br.com.appwebic.validador;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.appwebic.dao.EventoDao;
import br.com.appwebic.model.Evento;

public class EventoValidador {
	
	public static boolean ValidarNome(Evento evento){
	
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(evento.getNome());
	
	    if( matcher.find()){
	    	EventoDao eD = new EventoDao();
	 	    try {
				int id = eD.getIdEvento(evento.getNome());
				if (id == -1){
					return true;
				}else{
					FacesContext.getCurrentInstance().addMessage("idErrorName",new FacesMessage(FacesMessage.SEVERITY_ERROR,  
		                       "Erro!" , "Evento já existe")); 
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
	    }else{
			FacesContext.getCurrentInstance().addMessage("idErrorName",new FacesMessage(FacesMessage.SEVERITY_INFO,  
	                       "Erro!" , "Nome do Evento inválido")); 
			return false;
		}	    
	}
	
	public static boolean ValidarArea(Evento evento){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(evento.getArea());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorArea",new FacesMessage(FacesMessage.SEVERITY_INFO,  
	                        "Erro!", "Área do Evento inválida")); 
			return false;
		}
	}
	
	public static boolean ValidarTema(Evento evento){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(evento.getTema());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorTema",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Tema do Evento inválido")); 
			return false;
		}
	}

	public static boolean ValidarInicio(Evento evento){
		
	    Date data = new Date(System.currentTimeMillis());    
	    SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");  
	    String dateSystem =  formatarDate.format(data);
		
	    String dataDigitada =  evento.getDataInicio();
	  	    
		int result = Collator.getInstance().compare(dataDigitada, dateSystem); 
		
	    if(result > 0 || result == 0 ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorInicio", new FacesMessage(FacesMessage.SEVERITY_INFO,  
	                        "Erro!", "Data de início do Evento inválida")); 
			return false;
		}
	}
	
	public static boolean ValidarFim(Evento evento){
			
	    String dataInicio =  evento.getDataInicio();
	    String dataFim = evento.getDataFim();
	  	    
		int result = Collator.getInstance().compare(dataFim, dataInicio); 
		
	    if(result > 0 || result == 0 ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorFim", new FacesMessage(FacesMessage.SEVERITY_INFO,  
	                        "Erro!", "Data de fim do Evento inválida")); 
			return false;
		}
	}
	
	public static boolean ValidarPais(Evento evento){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(evento.getPais());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorPais",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","País do Evento inválido")); 
			return false;
		}
	}
	
	public static boolean ValidarCidade(Evento evento){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(evento.getCidade());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorCidade",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Cidade do Evento inválida")); 
			return false;
		}
	}
	
	public static boolean ValidarCategoria(Evento evento){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(evento.getCategoria());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorPais",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Categoria do Evento inválida")); 
			return false;
		}
	}
	
	public static boolean ValidarLink(Evento evento){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(evento.getLink());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorPais",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Link do Evento inválido")); 
			return false;
		}
	}
	
	public static boolean ValidarDescricao(Evento evento){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(evento.getDescricao());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorPais",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Descrição do Evento inválido")); 
			return false;
		}
	}
	
	public static boolean ValidarValor(Evento evento){
		
		Pattern pattern = Pattern.compile("[^0-9]");
		String valor =   String.valueOf(evento.getValor());
	    Matcher matcher = pattern.matcher(valor);
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorPais",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Descrição do Evento inválido")); 
			return false;
		}
	}
	
}
