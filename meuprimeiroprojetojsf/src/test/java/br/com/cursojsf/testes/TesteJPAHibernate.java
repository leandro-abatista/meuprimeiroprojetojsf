package br.com.cursojsf.testes;

import javax.persistence.Persistence;

public class TesteJPAHibernate {
	
	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("meuprimeiroprojetojsf");
	}

}
