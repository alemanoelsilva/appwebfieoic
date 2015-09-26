package br.com.appwebic.model;



public class EventoInstituicao{
	
	private Evento evento;
	private Instituicao instituicao;	
	private String dataHoraCadastro;
	
	public EventoInstituicao(){}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public String getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(String dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}
		
}
