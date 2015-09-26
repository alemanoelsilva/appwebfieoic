package br.com.appwebic.validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.appwebic.model.PessoaPublicacao;
import br.com.appwebic.model.Publicacao;

public class PublicacaoValidador {
	
	public static boolean ValidarNome(Publicacao publicacao){
	
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(publicacao.getNome());
	
	    if( matcher.find()){
	    	return true;
	    }else{
			FacesContext.getCurrentInstance().addMessage("idErrorName",new FacesMessage(FacesMessage.SEVERITY_INFO,  
	                       "Erro!" , "Nome inv�lido")); 
			return false;
		}	    
	}
	
	public static boolean ValidarTipo(Publicacao publicacao){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(publicacao.getTipo());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorTipo",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Tipo de publica��o inv�lido")); 
			return false;
		}
	}
	
	public static boolean ValidarTema(Publicacao publicacao){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(publicacao.getTema());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorTema",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Tema inv�lido")); 
			return false;
		}
	}	
	
	public static boolean ValidarArea(Publicacao publicacao){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(publicacao.getArea());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorArea",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","�rea inv�lida")); 
			return false;
		}
	}	
	
	public static boolean ValidarCentroPesquisa(PessoaPublicacao pessoaPublicacao){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(pessoaPublicacao.getNomeCentroPesquisa());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorCentroPesquisa",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Centro de Pesquisa inv�lido")); 
			return false;
		}
	}
	
	public static boolean ValidarOrientador(PessoaPublicacao pessoaPublicacao){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(pessoaPublicacao.getNomeOrientador());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorOrientador",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Nome do orientador inv�lido")); 
			return false;
		}
	}
	
	public static boolean ValidarLink(Publicacao publicacao){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(publicacao.getLink());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorLink",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Link inv�lido")); 
			return false;
		}
	}
	
	public static boolean ValidarDescricao(Publicacao publicacao){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(publicacao.getResumo());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorResumo",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Descri��o inv�lida")); 
			return false;
		}
	}
}
