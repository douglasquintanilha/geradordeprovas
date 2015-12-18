package br.com.caelum.geradordeprovas.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.caelum.geradordeprovas.interceptors.chain.UrisPermitidasChain;
import br.com.caelum.geradordeprovas.interceptors.chain.UsuarioDeslogadoChain;
import br.com.caelum.geradordeprovas.interceptors.chain.UsuarioLogadoChain;
import br.com.caelum.geradordeprovas.interceptors.chain.VerificadorPermissaoAdminChain;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) 
																				throws Exception {

		AutorizadorChain urisPermitidas = new UrisPermitidasChain();
		AutorizadorChain usuarioDeslogado = new UsuarioDeslogadoChain();
		AutorizadorChain usuarioLogado = new UsuarioLogadoChain();
		AutorizadorChain verificadorPermissao = new VerificadorPermissaoAdminChain();
		
		urisPermitidas.setProximo(usuarioDeslogado);
		usuarioDeslogado.setProximo(usuarioLogado);
		usuarioLogado.setProximo(verificadorPermissao);
		
		if(!urisPermitidas.autoriza(request)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		
		return true;
	}
}