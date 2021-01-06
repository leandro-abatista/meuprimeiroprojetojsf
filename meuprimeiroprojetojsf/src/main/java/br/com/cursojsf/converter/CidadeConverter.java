package br.com.cursojsf.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.cursojsf.entidades.Cidade;
import br.com.cursojsf.jpautil.JPAUtil;

@FacesConverter(forClass = Cidade.class, value = "cidadeConverter")
public class CidadeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Esse método retorna um objeto inteiro
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String idCidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		// find significa buscar
		Cidade cidade = (Cidade) entityManager.find(Cidade.class, Long.parseLong(idCidade));
		return cidade;
	}

	/**
	 * Retorna apenas o código em String
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cidade) {

		if (cidade == null) {
			return null;
		}

		if (cidade instanceof Cidade) {
			return ((Cidade) cidade).getId().toString();// retorna apenas o id
		} else {
			return cidade.toString();
		}

	}

}
