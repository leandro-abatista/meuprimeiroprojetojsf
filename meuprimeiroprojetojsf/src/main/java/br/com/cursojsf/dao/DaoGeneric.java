package br.com.cursojsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.cursojsf.jpautil.JPAUtil;

public class DaoGeneric<Entidade> {
	
	public void save(Entidade entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(entidade);//esspa parte salva
		transaction.commit();//confirma o objeto salvo no banco
		entityManager.close();//fecha a conexao
	}
	
	public Entidade merge(Entidade entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Entidade retorno = entityManager.merge(entidade);//esspa parte salva e atualiza
		transaction.commit();//confirma o objeto salvo no banco
		entityManager.close();//fecha a conexao
		
		return retorno;
	}
	
	public void delete(Entidade entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.remove(entidade);//esspa parte remove
		transaction.commit();//confirma o objeto salvo no banco
		entityManager.close();//fecha a conexao
	}
	
	public void deletePorId(Entidade entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Object id = JPAUtil.getPrimaryKey(entidade);
		
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id).executeUpdate();
		transaction.commit();//confirma o objeto salvo no banco
		entityManager.close();//fecha a conexao
	}
	
	public List<Entidade> getListEntity(Class<Entidade> entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		List<Entidade> retorno = entityManager.createQuery("from " + entidade.getName() + " order by id")
				.getResultList();
		
		
		transaction.commit();//confirma o objeto salvo no banco
		entityManager.close();//fecha a conexao
		
		return retorno;
	}

}
