package br.com.appwebic.generico;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.appwebic.dao.ConnectionFactory;
import br.com.appwebic.model.Evento;

public abstract class GenericDAOImp<T, I extends Serializable> implements GenericDAO<T, I> {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	
	public GenericDAOImp() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String setCodigo(T objeto) throws Exception{
        String sql = "insert into ";
        @SuppressWarnings("rawtypes")
		Class classe = objeto.getClass();
        String nomeClasse = classe.getName();
        String[] array = nomeClasse.split("\\.");
        String nome = array[4];
        
        sql += nome;
        sql += "(";
 
        Field[] atributos = classe.getDeclaredFields();
        //String valores = "(";
        int contador=0;
        for(contador=0; atributos[contador].getName() != "dataHoraCadastro"; contador++) {
        	if (atributos[contador].getName() != "id"){
	            sql += atributos[contador].getName() +"_"+nome+ ", ";
	            //valores += "? ,";
        	}
        }
        
        sql += "data_hora_cadastro) ";
        //valores += "?)";
        //sql += "values " + valores;
        sql += "VALUES (";
        
        int i=0;
        Field valorAtrib;
        for (i = 0; atributos[i].getName() != "dataHoraCadastro"; i++) {  
            valorAtrib = atributos[i];  
            valorAtrib.setAccessible(true);  
            if (atributos[i].getName() != "id"){
	            if (i != atributos.length - 1) { 
	                sql += verificaValor(valorAtrib, objeto) + ",";  
	            } else {  
	                sql += verificaValor(valorAtrib, objeto) + ")";  
	            }  
            }    
        } 
        
        valorAtrib = atributos[i];
        valorAtrib.setAccessible(true); 
        sql += verificaValor(valorAtrib, objeto) + ")";
		
		return sql;
	}
	
	private String verificaValor(Field valorAtrib, T objeto) throws InstantiationException, ClassNotFoundException {  
	        //Recebe o atributo da vez e um objeto contendo o bean que contém os  
	        //valores a serem gravados no banco, então verifica o tipo do atributo  
	        //para montar a string de maneira correta.  
	        String valor = "";  
	        try {  
	            if (valorAtrib.getType().getName().equals("java.lang.String"))  {  
	                valor = "'" + valorAtrib.get(objeto) + "'";  
	                if (valor.equals("'null'")) {  
	                    valor = "NULL";  
	                }  
	            } else {  
		            if (valorAtrib.getType().getName().equals("br.com.appwebic.model.Pessoa"))  {  
		            	System.out.println("valorAtrib: "+valorAtrib);
		            	Evento evento = new Evento();
		            	evento = (Evento) objeto;
		            	valor = String.valueOf(valorAtrib.get(evento.getPessoa().getId()));  
			            
			       } else { 	            	
		            	if (valorAtrib.getType().getName().equals("java.lang.Float"))  {  
		                    valor = String.valueOf(valorAtrib.get(objeto));  
		                } else {  
		                    if (valorAtrib.getType().getName().equals("java.lang.Double")) {  
		                        valor = String.valueOf(valorAtrib.get(objeto));  
		                    } else {  
		                        if (valorAtrib.getType().getName().equals("int")) {  
		                            valor = String.valueOf(valorAtrib.get(objeto));  
		                        } else {  
		                            if (valorAtrib.getType().getName().equals("java.math.BigDecimal")) {  
		                                valor = String.valueOf(valorAtrib.get(objeto));  
		                            } else {  
		                                if (valorAtrib.getType().getName().equals("java.sql.Timestamp")) {  
		                                    valor = "'" + String.valueOf(valorAtrib.get(objeto)) + "'";  
		                                    if (valor.equals("'null'")) {  
		                                        valor = "NULL";  
		                                    }  
		                                } else {  
		                                    if (valorAtrib.getType().getName().equals("boolean")) {  
		                                        valor = String.valueOf(valorAtrib.get(objeto));  
		                                    }  
		                                }  
		                            }  
		                        }  
		                    }  
		                }  
		            } 
		        }
	            return valor;  
	        } catch (IllegalArgumentException ex) {  
	            //ex.printStackTrace();  
	            //new ConexaoException(ex.getClass().getName(), "Entre em contato com suporte", ex.getMessage(), ex.getStackTrace());  
	        } catch (IllegalAccessException ex) {  
	            //ex.printStackTrace();  
	            //new ConexaoException(ex.getClass().getName(), "Entre em contato com suporte", ex.getMessage(), ex.getStackTrace());  
	        }  
	        return null;  
	}    
	
	@Override
    public void save(T objeto) throws Exception{
		conn = ConnectionFactory.getConnection();		
 		String sql = setCodigo(objeto);
	    stmt = conn.prepareStatement(sql);
	    
        System.out.println(sql);
        
	    stmt.execute();
	    stmt.close();
		
	    conn.close();
	} 


}
