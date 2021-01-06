package br.com.cursojsf.repository;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.cursojsf.entidades.Estado;
import br.com.cursojsf.jpautil.JPAUtil;

public class IDaoFornecedorImpl implements IDAOFornecedor {

	@Override
	public List<SelectItem> listaEstados() {
		
		//instância uma lista de selectItem como vazia
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		
		//primeiro tem que abrir uma transação
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		//caregando uma lista de estados e depois retorna todos os estados cadastrados no banco de dados para a tela
		List<Estado> estados = entityManager.createQuery("from Estado").getResultList();
		
		//o for vai varrer a lista de estados
		for (Estado estado : estados) {
			//passando o objeto inteiro(value) e o label(o que mostra o nome do estado para o usuario)
			selectItems.add(new SelectItem(estado, estado.getNome()));
		}
		
		return selectItems;
	}

	

}
