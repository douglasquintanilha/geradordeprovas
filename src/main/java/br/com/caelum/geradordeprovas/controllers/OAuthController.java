package br.com.caelum.geradordeprovas.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.caelum.geradordeprovas.configuration.Constantes;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.oauth.GithubApi;

@Controller
@RequestMapping("/oauth")
public class OAuthController {

	private OAuthService service;
	private Token EMPTY_TOKEN = null;
	
	@Autowired
	private Constantes constantes;
	
	@Profile("producao")
	@PostConstruct
	public void prepareOAuthServiceProducao() throws IOException {
		this.service = new ServiceBuilder()
				.provider(GithubApi.class)
				.apiKey(constantes.getProperty("apiKeyProducao"))
				.apiSecret(constantes.getProperty("apiSecretProducao"))
				.callback(constantes.getProperty("apiUrlCallbackProducao"))
				.build();
	}
	
	@Profile("dev")
	@PostConstruct
	public void prepareOAuthServiceDev() throws IOException {
		this.service = new ServiceBuilder()
				.provider(GithubApi.class)
				.apiKey(constantes.getProperty("apiKeyDev"))
				.apiSecret(constantes.getProperty("apiSecretDev"))
				.callback(constantes.getProperty("apiUrlCallbackDev"))
				.build();
	}

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
			return new ModelAndView(new RedirectView("/GeradorDeProvas/oauth/github-logado"));
		}
		return new ModelAndView(new RedirectView("/GeradorDeProvas/oauth/github-error"));
	} 

	@Profile("producao")
	@RequestMapping("/github-logado")
	public ModelAndView logadoProducao(HttpSession sessao) {
			Usuario usuario = new Usuario();
			usuario.setAdmin(true);
			sessao.setAttribute("usuario", usuario);
			ModelAndView mv = new ModelAndView(new RedirectView("http://caelumprovas-dquintanilha.rhcloud.com/admin/index"));
			return mv;
	}

	@Profile("dev")
	@RequestMapping("/github-logado")
	public ModelAndView logadoDev(HttpSession sessao) {
			Usuario usuario = new Usuario();
			usuario.setAdmin(true);
			sessao.setAttribute("usuario", usuario);
			ModelAndView mv = new ModelAndView(new RedirectView("/GeradorDeProvas/admin/index"));
			return mv;
	}
	
	@RequestMapping("/github-error")
	public ModelAndView logado() {
			ModelAndView mv = new ModelAndView("login-error");
			return mv;
	}

}
