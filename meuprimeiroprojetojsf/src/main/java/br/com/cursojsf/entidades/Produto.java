package br.com.cursojsf.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Produto extends GenericDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = true, length = 120)
	private String descricao;
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	@Column(scale = 10, precision = 2)
	private Double valorCompra;
	@Column(scale = 10, precision = 2)
	private Double valorVenda;
	@Column(nullable = true)
	private Integer quantidade;

	/**
	 * Essa parte é para upload de imagem
	 */
	@Column(columnDefinition = "text") // grava arquivos em base64
	private String fotoIconBase64;// imagem no formato texto que será gravado no banco de dados

	private String extensao;// extensao jpg, png, jpeg
	@Lob // grava arquivos no banco de dados
	@Basic(fetch = FetchType.LAZY)
	private byte[] fotoIconBase64Original;

	/**
	 * Getters e Setters
	 */
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getFotoIconBase64() {
		return fotoIconBase64;
	}

	public void setFotoIconBase64(String fotoIconBase64) {
		this.fotoIconBase64 = fotoIconBase64;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public byte[] getFotoIconBase64Original() {
		return fotoIconBase64Original;
	}

	public void setFotoIconBase64Original(byte[] fotoIconBase64Original) {
		this.fotoIconBase64Original = fotoIconBase64Original;
	}

}
