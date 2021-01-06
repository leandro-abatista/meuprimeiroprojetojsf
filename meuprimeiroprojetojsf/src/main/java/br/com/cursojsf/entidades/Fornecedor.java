package br.com.cursojsf.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Fornecedor extends GenericDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 150)
	private String razaoSocial;
	@Column(nullable = false, length = 150)
	private String nomeFantasia;
	@Column(nullable = false, length = 25)
	private String cnpj;
	@Column(nullable = true, length = 20)
	private String inscricaoEstadual;
	@Column(nullable = false)
	private boolean situacaoFuncionamento;
	@Column(nullable = true, length = 16)
	private String telFixo;
	@Column(nullable = false, length = 17)
	private String telCelular1;
	@Column(nullable = true, length = 17)
	private String telCelular2;
	@Column(nullable = true, length = 120)
	private String email;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	@Transient//não fica persistente ou não grava no banco, apenas fica na memória
	private Estado estados;
	@ManyToOne//pode ter muitos fornecedores em uma cidade
	private Cidade cidades;
	
	public Estado getEstados() {
		return estados;
	}
	
	public void setEstados(Estado estados) {
		this.estados = estados;
	}
	
	public Cidade getCidades() {
		return cidades;
	}
	
	public void setCidades(Cidade cidades) {
		this.cidades = cidades;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public boolean isSituacaoFuncionamento() {
		return situacaoFuncionamento;
	}

	public void setSituacaoFuncionamento(boolean situacaoFuncionamento) {
		this.situacaoFuncionamento = situacaoFuncionamento;
	}

	public String getTelFixo() {
		return telFixo;
	}

	public void setTelFixo(String telFixo) {
		this.telFixo = telFixo;
	}

	public String getTelCelular1() {
		return telCelular1;
	}

	public void setTelCelular1(String telCelular1) {
		this.telCelular1 = telCelular1;
	}

	public String getTelCelular2() {
		return telCelular2;
	}

	public void setTelCelular2(String telCelular2) {
		this.telCelular2 = telCelular2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
