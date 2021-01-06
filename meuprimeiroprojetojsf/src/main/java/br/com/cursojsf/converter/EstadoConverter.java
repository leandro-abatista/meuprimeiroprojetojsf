package br.com.cursojsf.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.cursojsf.entidades.Estado;
import br.com.cursojsf.jpautil.JPAUtil;

@FacesConverter(forClass = Estado.class, value = "estadoConverter")
public class EstadoConverter implements Converter, Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Esse método retorna um objeto inteiro
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String idEstado) {
		EntityManager  entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		//find significa buscar
		Estado estado = (Estado) entityManager.find(Estado.class, Long.parseLong(idEstado));
		return estado;
	}

	/**
	 * Retorna apenas o código em String
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object estado) {
		
		if (estado == null) {
			return null;//aqui é so para não lançar uma exceção
		}
		
		//Se o estado é uma instância de Estado, retorna o objeto completo e converte para string
		if (estado instanceof Estado) {
			return ((Estado) estado).getId().toString();//retorna apenas o id
		} else {
			return estado.toString();//aqui é só se vier o id
		}
	}

}
