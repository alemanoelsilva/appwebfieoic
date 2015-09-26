package br.com.appwebic.teste;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import br.com.appwebic.controller.PessoaController;
import br.com.appwebic.controller.PublicacaoController;
import br.com.appwebic.converter.ConverterSenhaMD5;
import br.com.appwebic.dao.AnaisDao;
import br.com.appwebic.dao.EventoDao;
import br.com.appwebic.dao.InstituicaoDao;
import br.com.appwebic.dao.PessoaDao;
import br.com.appwebic.dao.PessoaPublicacaoDao;
import br.com.appwebic.generico.EventoDAOtest;
import br.com.appwebic.generico.EventoDAOtestIml;
import br.com.appwebic.generico.PessoaDAOtest;
import br.com.appwebic.generico.PessoaDAOtestIml;
import br.com.appwebic.model.Anais;
import br.com.appwebic.model.Evento;
import br.com.appwebic.model.Instituicao;
import br.com.appwebic.model.Pessoa;
import br.com.appwebic.model.PessoaPublicacao;
import br.com.appwebic.model.Publicacao;
import br.com.appwebic.validador.EventoValidador;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class Simulador {

	public static void main(String[] args) throws Exception{
		
		Scanner s = new Scanner (System.in);
		int op=0;
		
		do {
			System.out.println("1 - Exbibir instituição");
			System.out.println("2 - Verificar professor e disciplina");
			System.out.println("3 - Inserir uma instituição");
			System.out.println("4 - Deletar uma instituição");
			System.out.println("5 - Inserir um Anais");
			System.out.println("6 - Deletar um Anais");
			System.out.println("7 - Inserir um evento");
			System.out.println("8 - Testar senha usuario");
			System.out.println("9 - Adicionar Pessoa");
			System.out.println("10 - Listar Pessoa");
			System.out.println("11 - Alterar Pessoa");
			System.out.println("12 - Inserir Pessoa com dao generico");
			System.out.println("13 - obter lista d epublicação por nome");
			System.out.println("14 - logar");
			System.out.println("15 - validar data");
			System.out.println("16 - selecionar evento por nome");
			op = s.nextInt();
			switch (op){
				case 1: 
					InstituicaoDao instituicaoDao = new InstituicaoDao();
					List <Instituicao> professores = instituicaoDao.getLista();
					
					System.out.println(professores);
					break;
				case 2:
					PessoaPublicacaoDao pepuDaoO = new PessoaPublicacaoDao();
					List <PessoaPublicacao> pepu = pepuDaoO.getLista();
					
					System.out.println(pepu);
					break;
				case 3:
					InstituicaoDao instDao = new InstituicaoDao();
					Instituicao inst = new Instituicao();
					inst.setNome("USP");
					inst.setPais("Brasil");
					inst.setEstado("SP");
					inst.setCidade("São Paulo");
					inst.setTipo("Universidade");
					inst.setCnpj("123.123.123/0001-01");
					instDao.save(inst);
					break;
				case 4: 
					InstituicaoDao instDao1 = new InstituicaoDao();
					Instituicao inst1 = new Instituicao();
					inst1.setId(4);
					instDao1.delete(inst1);	
					break;
				case 5:
				    AnaisDao anaisDao = new AnaisDao();
				    Anais anais = new Anais();
				    anais.setNome("Anais teste 1");
				    anais.setArea("Area Teste 1");
				    anais.setEditora("editora Teste 1");
				    Evento evento = new Evento();
				    evento.setId(2);
					anais.setEvento(evento);
					anaisDao.save(anais);
					break;
				case 6: 
					AnaisDao anaisDao1 = new AnaisDao();
					Anais anais1 = new Anais();
					anais1.setId(2);
					anaisDao1.delete(anais1);	
					break;
				case 7:
					Evento ee2 = new Evento();
					
				    Date data = new Date(System.currentTimeMillis());    
				    SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");  
				    String date = formatarDate.format(data);
					
					ee2.setNome("Teste");
					ee2.setArea("area teste");
					ee2.setTema("tema teste");
					ee2.setDataInicio(date);
					ee2.setDataFim(date);
					ee2.setPais("Teste");
					ee2.setEstado("Te");
					ee2.setCidade("Teste");
					ee2.setDataInicioInscricao(date);
					ee2.setDataFimInscricao(date);
					ee2.setCategoria("teste");

				    ee2.setDataHoraCadastro(date);
				    
				    Pessoa pTest = new Pessoa();
				    pTest.setId(1);
				    
				    ee2.setPessoa(pTest);
					EventoDAOtest eee2 = new EventoDAOtestIml();
					eee2.save(ee2);
				case 8:	
					PessoaDao pDao = new PessoaDao();
					String senha, email;
					email = "alemanoelsilva@gmail.com";
					senha = "1a";
					Pessoa teste;
					
					
					String senhaCript = ConverterSenhaMD5.convertStringToMd5(senha);
					System.out.println("Senha: "+senhaCript);

					teste = pDao.logar(senhaCript, email);
					System.out.println("Login: "+teste.getNome());
					break;
				case 9:
					PessoaDao pDao1 = new PessoaDao();
					Pessoa pp1 = new Pessoa();
					
					pp1.setNome("Teste");
					pp1.setCpf("922.123.123-12");
					pp1.setTitulo("Teste1");
					pp1.setEmail("alemanoelsil1va@gmail.com12333455");
					String snh = "1A";
					String senhaCriptf = ConverterSenhaMD5.convertStringToMd5(snh);
					pp1.setSenha(senhaCriptf);
					pp1.setPais("Teste");
					pp1.setEstado("Te");
					pp1.setCidade("Teste");
					pp1.setSituacao(1);
					//pDao1.setCodigo(pp1);
					pDao1.save(pp1);
					break;
				case 10:
					PessoaDao pdao1 = new PessoaDao();
					List <Pessoa> pessoa = pdao1.getLista();
					
					System.out.println(pessoa);
					break;
				case 11:
					PessoaDao pdao2 = new PessoaDao();
					Pessoa p123 = new Pessoa();
					p123 = pdao2.getById(15);
					p123.setTitulo("Mestre");
					try {
						pdao2.update(p123);
						System.out.println("alterou hehe");

					}catch(MySQLSyntaxErrorException exx){
						System.out.println("Deu erro "+ exx);
					}
					break;
				case 12:
						Pessoa pp2 = new Pessoa();
						
						pp2.setNome("Teste");
						pp2.setCpf("922.099.013-12");
						pp2.setTitulo("Teste1");
						pp2.setEmail("alemjka1va@gmail.com12333455");
						String sn1h = "1A";
						String senha1Criptf = ConverterSenhaMD5.convertStringToMd5(sn1h);
						pp2.setSenha(senha1Criptf);
						pp2.setPais("Teste");
						pp2.setEstado("Te");
						pp2.setCidade("Teste");
						pp2.setSituacao(1);
					    Date data1 = new Date(System.currentTimeMillis());    
					    SimpleDateFormat formatarDate1 = new SimpleDateFormat("yyyy-MM-dd");  
					    String date1 = formatarDate1.format(data1);
					    pp2.setDataHoraCadastro(date1);
						PessoaDAOtest ppp2 = new PessoaDAOtestIml();
						ppp2.save(pp2);
						break;
					case 13:
						PublicacaoController pC = new PublicacaoController();
						List<Publicacao> pLista = pC.getListName("como");
						System.out.println(pLista);
						break;
					case 14:	
						Pessoa p = new Pessoa();
						PessoaController pC1 = new PessoaController();
						p.setEmail("ale@ale");
						String senha111 = ConverterSenhaMD5.convertStringToMd5("1");
						p.setSenha(senha111);
						Pessoa p1 = null;
						p1 = pC1.logar(p);

						System.out.println("Nome: "+p1.getNome());
						
						break;
					case 15:
						Evento e123 = new Evento();
						e123.setDataInicio("11/07/2014");
						boolean test = EventoValidador.ValidarInicio(e123);
						System.out.println("resultado é "+ test);
						break;
					case 16:
						EventoDao eDd = new EventoDao();
						int a = eDd.getIdEvento("Aprendendo Cobol");
						System.out.println("id: "+a);
			}
			
		}while (op !=0);
		s.close();
	
	}

}
