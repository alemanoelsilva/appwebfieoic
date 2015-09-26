package br.com.appwebic.model;

 
public class PessoaPublicacao {

    private Pessoa pessoa;
    private Publicacao publicacao;
	private String nomeOrientador;	
	private String nomeCentroPesquisa;
	private String dataHoraCadastro;
	
	// construtor
	public PessoaPublicacao(){}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}

	public String getNomeOrientador() {
		return nomeOrientador;
	}

	public void setNomeOrientador(String nomeOrientador) {
		this.nomeOrientador = nomeOrientador;
	}

	public String getNomeCentroPesquisa() {
		return nomeCentroPesquisa;
	}

	public void setNomeCentroPesquisa(String nomeCentroPesquisa) {
		this.nomeCentroPesquisa = nomeCentroPesquisa;
	}

	public String getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(String dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}
	
}
