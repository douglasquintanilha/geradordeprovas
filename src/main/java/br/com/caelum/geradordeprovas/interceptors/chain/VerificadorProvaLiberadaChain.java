package br.com.caelum.geradordeprovas.interceptors.chain;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.geradordeprovas.interceptors.AutorizadorChain;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Usuario;

public class VerificadorProvaLiberadaChain implements AutorizadorChain {

	private AutorizadorChain proximo;

	@Override
	public boolean autoriza(HttpServletRequest request) {

		Usuario usuario = getUsuarioLogado(request);
		String provaId = getProvaId(request);

		if (provaId.equals("")) {
			if (proximo != null) {
				return proximo.autoriza(request);
			}
		}
		else {
			boolean validator = false;
			for (Prova prova : usuario.getProvas()) {
				if (prova.getId().equals(provaId))
					validator = true;
			}
			return validator;
		}
		return false;

	}

	private String getProvaId(HttpServletRequest req) {
		if (req.getParameter("provaId") == null)
			return "";
		else
			return req.getParameter("provaId");
	}

	private Usuario getUsuarioLogado(HttpServletRequest request) {
		return (Usuario) request.getSession().getAttribute("usuario");
	}

	@Override
	public void setProximo(AutorizadorChain proximo) {
		this.proximo = proximo;
	}

}
