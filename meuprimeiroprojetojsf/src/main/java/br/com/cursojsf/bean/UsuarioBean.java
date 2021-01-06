package br.com.cursojsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.omnifaces.util.Messages;

import br.com.cursojsf.dao.DaoGeneric;
import br.com.cursojsf.entidades.Usuario;
import br.com.cursojsf.repository.IDaoUsuario;
import br.com.cursojsf.repository.IDaoUsuarioImpl;

@ViewScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioBean {

	private DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	private IDaoUsuario iDaoUsuario = new IDaoUsuarioImpl();

	public String salvar() {
		try {

			usuario = daoGeneric.merge(usuario);
			this.carregarListaUsuarios();
			this.novo();
			Messages.addGlobalInfo("Registro salvo com sucesso!");
			return "";

		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo registro!");
			e.printStackTrace();
		}
		return "";
	}

	public void novo() {
		usuario = new Usuario();
	}

	public String limpar() {
		/* Executa algum processo antes de limpar */
		this.novo();
		return "";
	}

	public String remove() {
		try {

			daoGeneric.deletePorId(usuario);
			this.carregarListaUsuarios();
			Messages.addGlobalInfo("Registro excluído com sucesso!");
			return "";

		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir o registro!");
			e.printStackTrace();
		}
		return "";
	}

	@PostConstruct
	public void carregarListaUsuarios() {
		usuarios = daoGeneric.getListEntity(Usuario.class);
	}

	public String logar() {
		try {

			Usuario user = iDaoUsuario.consultarUsuario(usuario.getLogin(), usuario.getSenha());

			if (usuario != null) {// achou o usuário
				// adicionar o usuario na sessão
				FacesContext context = FacesContext.getCurrentInstance();
				ExternalContext externalContext = context.getExternalContext();
				externalContext.getSessionMap().put("usuarioLogado", user);
				Messages.addGlobalInfo("Usuário autenticado com sucesso!");
				return "menu.jsf";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Login e/ou Senha inválidos!");
		}

		return "index.jsf";

	}

	public String deslogar() {
		// remove o usuario que ta logado
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("usuarioLogado");
		// req recebe o contexto e invalida a sessão
		HttpServletRequest req = (HttpServletRequest) context.getCurrentInstance().getExternalContext().getRequest();
		req.getSession().invalidate();
		Messages.addGlobalInfo("Usuário deslogado com sucesso!");
		return "index.jsf";
	}

	public boolean permissaoAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Usuario u = (Usuario) externalContext.getSessionMap().get("usuarioLogado");

		return u.getPerfil().equals(acesso);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
