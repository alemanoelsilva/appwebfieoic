package br.com.appwebic.conversacao;

import java.beans.FeatureDescriptor;
import java.util.Collections;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.PropertyNotFoundException;
import javax.el.PropertyNotWritableException;


public class ConversacaoELResolver extends ELResolver {

	@Override
	public Object getValue(ELContext elContext, Object base, Object property) {
		
		if (property == null) {
			throw new PropertyNotFoundException("A Propriedade não pode ser nula!");
		}

		if (base == null) {

			
			if(Conversacao.NOME_ESCOPO.equals(property.toString()))
			{
				Conversacao conversacao = Conversacao.instancia();
				elContext.setPropertyResolved(true);
				return conversacao;
			}
			
			Conversacao conversacao = Conversacao.instancia();
			return getValue(conversacao, property.toString(), elContext);
			
		} else if (base instanceof Conversacao) {

			return getValue((Conversacao) base, property.toString(), elContext);

		}
		
		return null;
	}

	private Object getValue(Conversacao conversacao, String property,
			ELContext elContext) {
		Object objeto = conversacao.get(property);
		elContext.setPropertyResolved(objeto != null);
		return objeto;
	}

	// --------------- outros métodos -----------------------

	@Override
	public Class<?> getCommonPropertyType(ELContext arg0, Object arg1) {
		return String.class;
	}

	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext arg0,
			Object arg1) {
		return Collections.<FeatureDescriptor> emptyList().iterator();
	}

	@Override
	public Class<?> getType(ELContext arg0, Object arg1, Object arg2)
			throws NullPointerException, PropertyNotFoundException, ELException {
		return Object.class;
	}

	@Override
	public boolean isReadOnly(ELContext arg0, Object arg1, Object arg2)
			throws NullPointerException, PropertyNotFoundException, ELException {
		return true;
	}

	@Override
	public void setValue(ELContext arg0, Object arg1, Object arg2, Object arg3)
			throws NullPointerException, PropertyNotFoundException,
			PropertyNotWritableException, ELException {

	}

}
