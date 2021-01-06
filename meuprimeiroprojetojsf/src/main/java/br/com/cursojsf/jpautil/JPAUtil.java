package br.com.cursojsf.jpautil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory;

	// toda vez que a classe JPAUtil for chamada e não tiver uma instância, o static
	// inicializa essa instância
	// e aí retorna um entity manager
	static {
		//essa condição garante que o entitymanager vai ser inicializado apenas uma vez
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
		}

	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public static Object getPrimaryKey(Object entidade) {
		return factory.getPersistenceUnitUtil().getIdentifier(entidade);
	}

}
