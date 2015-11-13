package br.com.caelum.geradordeprovas.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.caelum.geradordeprovas.models.Usuario;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object controller) throws Exception {

		String uri = request.getRequestURI();

		if (uri.endsWith("loginForm") 
				|| uri.endsWith("efetuaLogin")
				|| uri.endsWith("loginGitHub")
				|| uri.endsWith("github-login")
				|| uri.endsWith("github-logado")
				|| uri.endsWith("/github-error")
				|| uri.endsWith("oauth/callback")){
			return true;
		}

		if (request.getSession().getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			System.out.println("É admin? :" + usuario.isAdmin());
			if (usuario.isAdmin()) {
				return true;
			}

			if (!usuario.isAdmin() && (uri.contains("/admin"))) {
				return false;
			}

			return true;
		}
		System.out.println("Denied " + uri + "Sessao:" + request.getSession().getAttribute("usuario") );
		response.sendRedirect("/GeradorDeProvas/loginForm");
		return false;
	}
}