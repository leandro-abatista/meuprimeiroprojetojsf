package br.com.cursojsf.repository;

import br.com.cursojsf.entidades.Usuario;

public interface IDaoUsuario {

	Usuario consultarUsuario(String login, String senha);
	
}
