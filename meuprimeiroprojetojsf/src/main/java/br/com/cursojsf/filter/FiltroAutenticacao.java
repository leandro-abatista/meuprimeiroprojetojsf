package br.com.cursojsf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.cursojsf.entidades.Usuario;
import br.com.cursojsf.jpautil.JPAUtil;

@WebFilter(urlPatterns = {"/*"})
public class FiltroAutenticacao implements Filter{
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		String url = req.getServletPath();
		
		if (usuarioLogado == null && !url.equalsIgnoreCase("index.jsf")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsf");
			dispatcher.forward(request, response);
			return;
		}
		
		//executa as ações do request e response
		chain.doFilter(request, response);//toda requisição e resposta passa por aqui
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		JPAUtil.getEntityManager();
	}

}
