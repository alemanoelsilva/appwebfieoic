package br.com.appwebic.model;

import java.util.List;

public class Pessoa {
	
    private Integer id;
	private String nome;	
	private String cpf;
	private String titulo;
	private String email; 
	private String senha;
	private String pais;
	private String estado;
	private String cidade;
	private int situacao;
	private String dataHoraCadastro;
	private List<Evento> evento;
    private List<PessoaPublicacao> pessoaPublicacao;
	
    // construtor
    public Pessoa(){}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	
	public String getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(String dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public List<Evento> getEvento() {
		return evento;
	}

	public void setEvento(List<Evento> evento) {
		this.evento = evento;
	}

	public List<PessoaPublicacao> getPessoaPublicacao() {
		return pessoaPublicacao;
	}

	public void setPessoaPublicacao(List<PessoaPublicacao> pessoaPublicacao) {
		this.pessoaPublicacao = pessoaPublicacao;
	}
	
	@Override
	public String toString() {
		return "Pessoa";
	}

}
