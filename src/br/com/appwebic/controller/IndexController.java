package br.com.appwebic.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.appwebic.model.Anais;
import br.com.appwebic.model.Evento;
import br.com.appwebic.model.Publicacao;

public class IndexController {
	
	List<Publicacao> publicacao = new ArrayList<Publicacao>();
	List<Anais> anais = new ArrayList<Anais>();
	List<Evento> evento = new ArrayList<Evento>();
	List<Object> object = new ArrayList<Object>();


	public IndexController() {}
	
	

	public List<Publicacao> getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(List<Publicacao> publicacao) {
		this.publicacao = publicacao;
	}

	public List<Anais> getAnais() {
		return anais;
	}

	public void setAnais(List<Anais> anais) {
		this.anais = anais;
	}

	public List<Evento> getEvento() {
		return evento;
	}

	public void setEvento(List<Evento> evento) {
		this.evento = evento;
	}

	public List<Object> getObject() {
		return object;
	}

	public void setObject(List<Object> object) {
		this.object = object;
	}
	
	

}
