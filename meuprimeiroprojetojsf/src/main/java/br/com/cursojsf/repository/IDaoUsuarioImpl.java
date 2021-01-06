package br.com.cursojsf.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.cursojsf.entidades.Usuario;
import br.com.cursojsf.jpautil.JPAUtil;

public class IDaoUsuarioImpl implements IDaoUsuario{

	@Override
	public Usuario consultarUsuario(String login, String senha) {
		
		Usuario usuario = null;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		//O 'getSingleResult()' retorna apenas um resultado
		usuario = (Usuario) entityManager
				.createQuery("select u from Usuario u where u.login = '" + login + "' and u.senha = '" + senha + "'")
				.getSingleResult();
		
		transaction.commit();//confirma o objeto salvo no banco
		entityManager.close();//fecha a conexao
		
		return usuario;
	}

}
