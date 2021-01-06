package br.com.cursojsf.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Cidade extends GenericDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 50)
	private String nome;
	/**
	 * Muitas cidades para um estado
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Estado estados;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstados() {
		return estados;
	}

	public void setEstados(Estado estados) {
		this.estados = estados;
	}

}
