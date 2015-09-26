package br.com.appwebic.validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.appwebic.dao.PessoaDao;
import br.com.appwebic.model.Pessoa;

public class PessoaValidador {
	
	public static boolean ValidarNome(Pessoa pessoa){
	
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(pessoa.getNome());
	
	    if( matcher.find()){
	    	return true;
	    }else{
			FacesContext.getCurrentInstance().addMessage("idErrorName",new FacesMessage(FacesMessage.SEVERITY_INFO,  
	                       "Erro!" , "Nome inválido")); 
			return false;
		}	    
	}
	
	public static boolean ValidarCpf(Pessoa pessoa){
		
		PessoaDao pD = new PessoaDao();
		
		int id = 0;
		
		try {
			id = pD.getCpf(pessoa.getCpf());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    if(id == -1){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorCpf",new FacesMessage(FacesMessage.SEVERITY_ERROR,  
	                        "Erro!", "CPF já cadastrado")); 
			return false;
		}
	}
	
	public static boolean ValidarTitulo(Pessoa pessoa){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(pessoa.getTitulo());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorTitulo",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Titulo inválido")); 
			return false;
		}
	}

	public static boolean ValidarEmail(Pessoa pessoa){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(pessoa.getEmail());
	
	    if( matcher.find() ){
	    	PessoaDao pD = new PessoaDao();
			
			int id = 0;
			
			try {
				id = pD.getEmail(pessoa.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		    if(id == -1){
		    	return true;	    
			}else{
				FacesContext.getCurrentInstance().addMessage("idErrorEmail",new FacesMessage(FacesMessage.SEVERITY_ERROR,  
		                        "Erro!", "E-mail já cadastrado")); 
				return false;
			}	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorEmail",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","E-mail inválido")); 
			return false;
		}
		
	}
		
	public static boolean ValidarPais(Pessoa pessoa){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(pessoa.getPais());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorPais",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","País inválido")); 
			return false;
		}
	}
	
	public static boolean ValidarCidade(Pessoa pessoa){
		
		Pattern pattern = Pattern.compile("[^0-9][a-zA-Z]");
	    Matcher matcher = pattern.matcher(pessoa.getCidade());
	
	    if( matcher.find() ){
	    	return true;	    
		}else{
			FacesContext.getCurrentInstance().addMessage("idErrorCidade",new FacesMessage(FacesMessage.SEVERITY_INFO,  
					"Erro!","Cidade inválida")); 
			return false;
		}
	}	
}
