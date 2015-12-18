package br.com.caelum.geradordeprovas.interceptors.chain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.geradordeprovas.interceptors.AutorizadorChain;

public class UrisPermitidasChain implements AutorizadorChain {

	private AutorizadorChain proximo;
	
	private List<String> uris = new ArrayList<>();
	
	public UrisPermitidasChain() {
		uris = Arrays.asList("login","efetuaLogin", 
							 "github-login", "github-logado",
							 "/github-error", "oauth/callback");
	}
	
	@Override
	public boolean autoriza(HttpServletRequest request) {
		if(uriAcessadaEhPermitida(request.getRequestURI())) 
			return true;
		
		if(proximo != null) 
			return proximo.autoriza(request);
		
		return true;
	}

	private boolean uriAcessadaEhPermitida(String uri) {
		for (String permitida : uris) {
			if(uri.endsWith(permitida))
				return true;
		}
		return false;
	}

	@Override
	public void setProximo(AutorizadorChain proximo) {
		this.proximo = proximo;
	}

}
