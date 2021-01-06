package br.com.cursojsf.bean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.cursojsf.dao.DaoGeneric;
import br.com.cursojsf.entidades.Pessoa;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public String salvar() {
		try {
			
			pessoa = daoGeneric.merge(pessoa);
			this.carregarListaPessoas();
			Messages.addGlobalInfo("Registro salvo com sucesso!");
			//this.mostrarMensagem("Registro cadastrado com sucesso!");
			return "";
			
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o novo registro!");
			e.printStackTrace();
		}
		return "";
	}
	
	private void mostrarMensagem(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(mensagem);
		context.addMessage(null, message);
		
	}

	public String novo() {
		/*Executa algum processo antes de novo*/
		pessoa = new Pessoa();
		return "";
	}
	
	public String limpar() {
		/*Executa algum processo antes de limpar*/
		pessoa = new Pessoa();
		return "";
	}
	
	public String remove() {
		try {
			
			daoGeneric.deletePorId(pessoa);
			this.novo();
			this.carregarListaPessoas();
			Messages.addGlobalInfo("Registro excluído com sucesso!");
			//this.mostrarMensagem("Registro excluído com sucesso!");
			return "";
			
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir o registro!");
			e.printStackTrace();
		}
		return "";
	}
	
	public String cancelar() {
		this.novo();
		return "";
	}
	
	@PostConstruct
	public void carregarListaPessoas() {
		pessoas = daoGeneric.getListEntity(Pessoa.class);
	}
	
	public void pesquisaCep(AjaxBehaviorEvent event) throws Exception {
		try {
			
			URL url = new URL("https://viacep.com.br/ws/" + pessoa.getCep() + "/json/");//pega o cep
			URLConnection urlConnection = url.openConnection();//abrindo a aconexão do webservice e depois consome
			InputStream isRetorno = urlConnection.getInputStream();//busca o cep no servidor e depois da um retorno
			BufferedReader br = new BufferedReader(new InputStreamReader(isRetorno,"UTF-8"));
			
			String cep = "";
			StringBuilder jsonCep = new StringBuilder();
			
			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}
			
			Pessoa gsonAuxiliar = new Gson().fromJson(jsonCep.toString(), Pessoa.class);
			
			pessoa.setCep(gsonAuxiliar.getCep());
			pessoa.setLogradouro(gsonAuxiliar.getLogradouro());
			pessoa.setComplemento(gsonAuxiliar.getComplemento());
			pessoa.setBairro(gsonAuxiliar.getBairro());
			pessoa.setLocalidade(gsonAuxiliar.getLocalidade());
			pessoa.setUf(gsonAuxiliar.getUf());
			pessoa.setIbge(gsonAuxiliar.getIbge());
			pessoa.setGia(gsonAuxiliar.getGia());
			pessoa.setDdd(gsonAuxiliar.getDdd());
			pessoa.setSiafi(gsonAuxiliar.getSiafi());
			
			
			//System.out.println(jsonCep);
			
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar consultar o 'cep'!");
			e.printStackTrace();
			//this.mostrarMensagem("Erro ao consultar o cep!\n " + e);
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
