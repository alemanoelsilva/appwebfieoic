package br.com.appwebic.validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.appwebic.dao.InstituicaoDao;
import br.com.appwebic.model.Instituicao;

public class InstituicaoValidador {
	
	public static boolean ValidarNome(Instituicao instituicao){
	
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(instituicao.getNome());
	
	    if( matcher.find()){
	    	return true;
	    }else{
			FacesContext.getCurrentInstance().addMessage("idErrorName",new FacesMessage(FacesMessage.SEVERITY_INFO,  
	                       "Erro!" , "Nome inválido")); 
			return false;
		}	    
	}
	
	public static boolean ValidarCnpj(Instituicao instituicao){
		
		InstituicaoDao iD = new InstituicaoDao();
		
		int id = 0;
		
		Pattern pattern = Pattern.compile("[^0-9]");
	    Matcher matcher = pattern.matcher(instituicao.getCnpj());
	    
	    if( matcher.find() ){
			try {
				id = iD.getCnpj(instituicao.getCnpj());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		    if(id == -1){
		    	return true;	    
			}else{
				FacesContext.getCurrentInstance().addMessage("idErrorCnpj",new FacesMessage(FacesMessage.SEVERITY_ERROR,  
		                        "Erro!", "CNPJ já cadastrado")); 
				return false;
			}	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorCnpj",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","CNPJ inválido")); 
			return false;
		}
		

	}
	
	public static boolean ValidarPais(Instituicao instituicao){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(instituicao.getPais());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorPais",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","País inválido")); 
			return false;
		}
	}
	
	public static boolean ValidarCidade(Instituicao instituicao){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(instituicao.getCidade());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorCidade",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Cidade inválida")); 
			return false;
		}
	}	
	
	public static boolean ValidarTipo(Instituicao instituicao){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(instituicao.getTipo());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorTipo",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Tipo de Instituição inválido")); 
			return false;
		}
	}	
}
