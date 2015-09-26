package br.com.appwebic.model;

import java.util.List;

public class Publicacao {

	private Integer id;
	private String nome;
	private String tipo;
	private String tema;
	private String area;	
	private String resumo;
	private String link;
	private Anais anais;
	private String dataHoraCadastro;
    private List<PessoaPublicacao> pessoaPublicacao;	
	
    public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	// construtor
	public Publicacao(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(String dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public Anais getAnais() {
		return anais;
	}

	public void setAnais(Anais anais) {
		this.anais = anais;
	}

	public List<PessoaPublicacao> getPessoaPublicacao() {
		return pessoaPublicacao;
	}

	public void setPessoaPublicacao(List<PessoaPublicacao> pessoaPublicacao) {
		this.pessoaPublicacao = pessoaPublicacao;
	}
	
}
