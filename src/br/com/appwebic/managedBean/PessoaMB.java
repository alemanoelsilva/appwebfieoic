package br.com.appwebic.managedBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.appwebic.controller.PessoaController;
import br.com.appwebic.converter.ConverterSenhaMD5;
import br.com.appwebic.model.Pessoa;
import br.com.appwebic.util.SessionUtil;
import br.com.appwebic.validador.PessoaValidador;

@SessionScoped
@ManagedBean(name="pessoaMB")
public class PessoaMB {

	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> lista;
	private Pessoa pessoaLogada;
	private boolean loggedIn;
	
	public String adicionaPessoa() throws Exception{
		
		boolean validar = PessoaValidador.ValidarNome(this.pessoa);
		if (validar == false){
			return "/AdicionarPessoa.xhtml";
		}
		
		validar = PessoaValidador.ValidarCpf(this.pessoa);
		if (validar == false){
			return "/AdicionarPessoa.xhtml";
		}	
		
		validar = PessoaValidador.ValidarTitulo(this.pessoa);
		if (validar == false){
			return "/AdicionarPessoa.xhtml";
		}	
		
		validar = PessoaValidador.ValidarEmail(this.pessoa);
		if (validar == false){
			return "/AdicionarPessoa.xhtml";
		}	
		
		validar = PessoaValidador.ValidarPais(this.pessoa);
		if (validar == false){
			return "/AdicionarPessoa.xhtml";
		}	
		
		validar = PessoaValidador.ValidarCidade(this.pessoa);
		if (validar == false){
			return "/AdicionarPessoa.xhtml";
		}
		
		String shn = ConverterSenhaMD5.convertStringToMd5(this.pessoa.getSenha());
		this.pessoa.setSenha(shn);
		this.pessoa.setSituacao(1);

		PessoaController pC = new PessoaController();
		pC.adicionaPessoa(this.pessoa);
		
		return "/login.xhtml?faces-redirect=true";
	}
	
	public String logar() throws Exception{
		
		String shn = ConverterSenhaMD5.convertStringToMd5(this.pessoa.getSenha());

		this.pessoa.setSenha(shn);
		
		PessoaController pC = new PessoaController();
		Pessoa p = pC.logar(this.pessoa);
		
		    if (p != null){ 
		    	loggedIn = true;
		    	pessoaLogada = p;
		        SessionUtil.addItem("USER", p); 
			    return "/index.xhtml?faces-redirect=true";
		    } else {
		    	FacesContext.getCurrentInstance().addMessage("idErrorEmail",new FacesMessage(FacesMessage.SEVERITY_ERROR,  
	                       "Erro!" , "E-mail ou senha invalidos")); 
		    	//FacesContext.getCurrentInstance().validationFailed();
		    	return "/login.xhtml";    
		    }
				
	}
	
	public String logout(){ 
	
		FacesContext fc = FacesContext.getCurrentInstance(); 
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
		session.invalidate(); 
		pessoaLogada = null;
        loggedIn = false;
        return "/index.xhtml?faces-redirect=true"; 
 
	} 
	
	public List<Pessoa> getLista(){

		PessoaController pC = new PessoaController();
		this.lista = pC.getLista();	
		return this.lista;	
	}
	
	public String alterar(){
		return "/adm/AlterarPessoa.xhtml?faces-redirect=true";
	}
	
	public String alterarPessoa() throws Exception{
		
		boolean validar = PessoaValidador.ValidarNome(this.pessoa);
		if (validar == false){
			return "/adm/AletrarPessoa.xhtml";
		}
		
		validar = PessoaValidador.ValidarTitulo(this.pessoa);
		if (validar == false){
			return "/adm/AletrarPessoa.xhtml";
		}	
		
		validar = PessoaValidador.ValidarPais(this.pessoa);
		if (validar == false){
			return "/adm/AletrarPessoa.xhtml";
		}	
		
		validar = PessoaValidador.ValidarCidade(this.pessoa);
		if (validar == false){
			return "/adm/AletrarPessoa.xhtml";
		}

		PessoaController pC = new PessoaController();
    	pC.alterarPessoa(this.pessoa);
		return "/adm/Manutencao.xhtml?faces-redirect=true";
	}
	
	public String excluirPessoa() throws Exception{

		PessoaController pC = new PessoaController();
		pC.excluirPessoa(this.pessoa);
		return "/adm/Manutencao.xhtml?faces-redirect=true";
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoaLogada() {
		return pessoaLogada;
	}

	public void setPessoaLogada(Pessoa pessoaLogada) {
		this.pessoaLogada = pessoaLogada;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
