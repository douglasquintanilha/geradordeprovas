package br.com.caelum.geradordeprovas.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.caelum.geradordeprovas.configuration.Constantes;
import br.com.caelum.geradordeprovas.models.Usuario;



@Controller
@RequestMapping("/oauth")
public class OAuthController {

	@Autowired
	private OAuthService service;
	private Token EMPTY_TOKEN = null;
	
	@Autowired
	private Constantes constantes;
	

	@RequestMapping("/github-login")
	public String redirectToGithub() {
		final Token EMPTY_TOKEN = null;
		return "redirect:" + service.getAuthorizationUrl(EMPTY_TOKEN);
	}

	@RequestMapping("/callback")
	public ModelAndView callback(@RequestParam("code") String authToken, Model model ) throws IOException  {
		Verifier verifier = new Verifier(authToken);
		Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
		
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.github.com/user");
		service.signRequest(accessToken, request);
		Response response = request.send();
		
		Usuario usuario = new Usuario();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode myObject = (ObjectNode) mapper.readTree(response.getBody());
		usuario.setLogin(myObject.get("login").asText()); 
		
		OAuthRequest requestOrg = new OAuthRequest(Verb.GET, "https://api.github.com/orgs/caelum/members/"+usuario.getLogin());
		service.signRequest(accessToken, requestOrg);
		Response responseOrg = requestOrg.send();		 
		

		if(responseOrg.getCode() == 204){
			return new ModelAndView("redirect:/oauth/github-logado");
		}
		return new ModelAndView(new RedirectView("github-error"));
	} 

	@RequestMapping("/github-logado")
	public ModelAndView logado(HttpSession sessao) {
			Usuario usuario = new Usuario();
			usuario.setAdmin(true);
			sessao.setAttribute("usuario", usuario);
			ModelAndView mv = new ModelAndView("redirect:../admin/index");
			return mv;
	}
	
	@RequestMapping("/github-error")
	public ModelAndView logado() {
			ModelAndView mv = new ModelAndView("login-error");
			return mv;
	}
}
