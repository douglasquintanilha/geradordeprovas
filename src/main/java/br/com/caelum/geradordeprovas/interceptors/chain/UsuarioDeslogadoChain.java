package br.com.caelum.geradordeprovas.interceptors.chain;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.geradordeprovas.interceptors.AutorizadorChain;

public class UsuarioDeslogadoChain implements AutorizadorChain {
	
	private AutorizadorChain proximo;
	
	@Override
	public boolean autoriza(HttpServletRequest request) {
		if(usuarioEstaDeslogado(request)) 
			return false;
		
		if(proximo != null) 
			return proximo.autoriza(request);
		
		return true;
		
	}

	private boolean usuarioEstaDeslogado(HttpServletRequest request) {
		return request.getSession().getAttribute("usuario") == null;
	}

	@Override
	public void setProximo(AutorizadorChain proximo) {
		this.proximo = proximo;
	}

}
