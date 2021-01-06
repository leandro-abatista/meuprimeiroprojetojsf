package br.com.cursojsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;

import br.com.cursojsf.dao.DaoGeneric;
import br.com.cursojsf.entidades.Lancamento;
import br.com.cursojsf.entidades.Usuario;
import br.com.cursojsf.repository.IDaoLancamento;
import br.com.cursojsf.repository.IDaoLancamentoImpl;

@ViewScoped
@ManagedBean(name = "lancamentoBean")
public class LancamentoBean {

	private Lancamento lancamento = new Lancamento();
	private DaoGeneric<Lancamento> daoGeneric = new DaoGeneric<Lancamento>();
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private IDaoLancamento iDaoLancamento = new IDaoLancamentoImpl();
	
	public String salvar() {
		try {
			
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			Usuario u = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
			
			lancamento.setUsuario(u);
			
			lancamento = daoGeneric.merge(lancamento);
			
			this.carregarListaLancamentos();
			
			Messages.addGlobalInfo("Registro salvo com sucesso!");
			
			return "";
			
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o novo registro!");
			e.printStackTrace();
		}
		return "";
	}
	
	public String novo() {
		lancamento = new Lancamento();
		return "";
	}
	
	public String limpar() {
		lancamento = new Lancamento();
		return "";
	}
	
	public String remove() {
		try {
			
			daoGeneric.deletePorId(lancamento);
			this.novo();
			this.carregarListaLancamentos();
			Messages.addGlobalInfo("Registro excluído com sucesso!");
			return "";
			
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir o registro!");
			e.printStackTrace();
		}
		return "";
	}
	
	@PostConstruct
	public void carregarListaLancamentos() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Usuario u = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
		
		lancamentos = iDaoLancamento.consultar(u.getId());
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

}
