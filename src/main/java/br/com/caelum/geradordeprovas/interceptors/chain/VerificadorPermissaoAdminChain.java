package br.com.caelum.geradordeprovas.interceptors.chain;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.geradordeprovas.interceptors.AutorizadorChain;
import br.com.caelum.geradordeprovas.models.Usuario;

public class VerificadorPermissaoAdminChain implements AutorizadorChain {
	
	private AutorizadorChain proximo;

	@Override
	public boolean autoriza(HttpServletRequest request) {
		Usuario usuario = getUsuarioLogado(request);
		String uriAcessada = request.getRequestURI();
		
		if(!usuario.isAdmin())
			if(uriDeAdmin(uriAcessada))
				return false;
		
		if(proximo != null)
			return proximo.autoriza(request);
		
		return true;
		
	}

	private boolean uriDeAdmin(String uriAcessada) {
		return uriAcessada.contains("/admin");
	}

	private Usuario getUsuarioLogado(HttpServletRequest request) {
		return (Usuario) request.getSession().getAttribute("usuario");
	}

	@Override
	public void setProximo(AutorizadorChain proximo) {
		this.proximo = proximo;
	}

}
