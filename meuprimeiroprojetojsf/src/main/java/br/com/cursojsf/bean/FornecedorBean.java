package br.com.cursojsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.omnifaces.util.Messages;

import br.com.cursojsf.dao.DaoGeneric;
import br.com.cursojsf.entidades.Cidade;
import br.com.cursojsf.entidades.Estado;
import br.com.cursojsf.entidades.Fornecedor;
import br.com.cursojsf.jpautil.JPAUtil;
import br.com.cursojsf.repository.IDAOFornecedor;
import br.com.cursojsf.repository.IDaoFornecedorImpl;

@ViewScoped
@ManagedBean(name = "fornecedorBean")
public class FornecedorBean {

	private DaoGeneric<Fornecedor> daoGeneric = new DaoGeneric<Fornecedor>();
	private Fornecedor fornecedor = new Fornecedor();
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	private List<SelectItem> estados;
	private List<SelectItem> cidades;

	private IDAOFornecedor idaoFornecedor = new IDaoFornecedorImpl();

	public String salvar() {
		try {

			fornecedor = daoGeneric.merge(fornecedor);
			this.carregarListaFornecedores();
			this.novo();
			Messages.addGlobalInfo("Registro salvo com sucesso!");
			return "";

		} catch (Exception e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo registro!");
		}
		return "";
	}

	public void novo() {
		fornecedor = new Fornecedor();
	}

	public String remove() {
		try {

			daoGeneric.deletePorId(fornecedor);
			this.carregarListaFornecedores();
			Messages.addGlobalInfo("Registro excluído com sucesso!");
			return "";

		} catch (Exception e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir o registro!");
		}
		return "";
	}

	@PostConstruct
	public void carregarListaFornecedores() {
		fornecedores = daoGeneric.getListEntity(Fornecedor.class);
	}

	// essa parte esta sendo chamada la tela na parte do selectOneMenu no
	// selectItems
	public List<SelectItem> getEstados() {
		estados = idaoFornecedor.listaEstados();
		return estados;
	}

	@SuppressWarnings("unchecked")
	public void carregaCidades(AjaxBehaviorEvent event) {
		Estado estado = (Estado) ((HtmlSelectOneMenu) event.getSource()).getValue();// aqui ta pegando o objeto inteiro
																					// que foi selecionado no combobox

		if (estado != null) {
			fornecedor.setEstados(estado);

			List<Cidade> cidades = JPAUtil.getEntityManager()
					.createQuery("from Cidade where estados.id = " + estado.getId())
					.getResultList();

			List<SelectItem> selectItemsCidades = new ArrayList<SelectItem>();

			for (Cidade cidade : cidades) {
				selectItemsCidades.add(new SelectItem(cidade, cidade.getNome()));
			}

			setCidades(selectItemsCidades);
		}
	}
	
	public void editar() {
		//primeira coisa para evitar o pintexception é verificar se o combocidades é null
		if (fornecedor.getCidades() != null) {
			Estado estado = fornecedor.getCidades().getEstados();//aqui já pega o estado deste fornecedor
			fornecedor.setEstados(estado);
			
			//seleciona a cidade de acordo com o estado
			List<Cidade> cidades = JPAUtil.getEntityManager()
					.createQuery("from Cidade where estados.id = " + estado.getId())
					.getResultList();

			List<SelectItem> selectItemsCidades = new ArrayList<SelectItem>();

			for (Cidade cidade : cidades) {
				selectItemsCidades.add(new SelectItem(cidade, cidade.getNome()));
			}

			setCidades(selectItemsCidades);
		}
	}

	public DaoGeneric<Fornecedor> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Fornecedor> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<SelectItem> getCidades() {
		return cidades;
	}

	public void setCidades(List<SelectItem> cidades) {
		this.cidades = cidades;
	}

}
