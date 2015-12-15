package br.com.caelum.geradordeprovas.interceptors;

import javax.servlet.http.HttpServletRequest;

public interface AutorizadorChain {
	
	public boolean autoriza(HttpServletRequest request);
	public void setProximo(AutorizadorChain proximo);

}
