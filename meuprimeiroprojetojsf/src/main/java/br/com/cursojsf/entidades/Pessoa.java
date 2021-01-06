package br.com.cursojsf.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Estrutura básica de uma classe para um projeto java web
 * 
 * @author Leandro
 *
 */
//Essa anotação persiste esta classe no banco de dados
@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 30)
	private String nome;
	@Column(nullable = false, length = 80)
	private String sobrenome;
	@Column(nullable = true)
	private Integer idade;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@Column(nullable = true, length = 120)
	private String email;
	@Column(nullable = true, length = 12)
	private String sexo;
	@Column(length = 15)
	private String[] atividadesFisicas;
	@Column(nullable = true)
	private Boolean ativo;
	@Column(nullable = true, length = 14)
	private String cpf;
	@Column(nullable = true, length = 12)
	private String rg;
	@Column(nullable = true, length = 4)
	private String emissor;
	@Column(nullable = true)
	private String[] ativfisicas;
	@Column(nullable = true, length = 10)
	private String cep;
	@Column(nullable = true, length = 100)
	private String logradouro;
	@Column(nullable = true, length = 50)
	private String complemento;
	@Column(nullable = true, length = 80)
	private String bairro;
	@Column(nullable = true, length = 50)
	private String localidade;
	@Column(nullable = true, length = 30)
	private String uf;
	@Column(nullable = true, length = 15)
	private String ibge;
	@Column(nullable = true, length = 15)
	private String gia;
	@Column(nullable = true, length = 2)
	private String ddd;
	@Column(nullable = true, length = 15)
	private String siafi;

	// é sempre bom ter um construtor vazio
	public Pessoa() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String[] getAtividadesFisicas() {
		return atividadesFisicas;
	}

	public void setAtividadesFisicas(String[] atividadesFisicas) {
		this.atividadesFisicas = atividadesFisicas;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmissor() {
		return emissor;
	}

	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}

	public String[] getAtivfisicas() {
		return ativfisicas;
	}

	public void setAtivfisicas(String[] ativfisicas) {
		this.ativfisicas = ativfisicas;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getSiafi() {
		return siafi;
	}

	public void setSiafi(String siafi) {
		this.siafi = siafi;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static Integer idade(final LocalDate dataNascimento) {
	    final LocalDate dataAtual = LocalDate.now();
	    final Period periodo = Period.between(dataNascimento, dataAtual);
	    return periodo.getYears();
	}

}
