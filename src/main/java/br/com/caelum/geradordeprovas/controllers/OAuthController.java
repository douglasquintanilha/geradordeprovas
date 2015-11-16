package br.com.caelum.geradordeprovas.controllers;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.oauth.GithubApi;

@Controller
@RequestMapping("/oauth")
public class OAuthController {

	private OAuthService service;
	private Token EMPTY_TOKEN = null;

	@PostConstruct
	public void prepareOAuthService() {
		this.service = new ServiceBuilder()
				.provider(GithubApi.class)
				.apiKey("3043231979046f1d8a4b")
				.apiSecret("3cdb01d3be4bd90011341b4bd5e46827737168c5")
				.callback("http://localhost:8000/GeradorDeProvas/oauth/callback")
				.build();
	}

	@RequestMapping("/github-login")
	public String redirectToGithub() {
		final Token EMPTY_TOKEN = null;
		return "redirect:" + service.getAuthorizationUrl(EMPTY_TOKEN);
	}

	@RequestMapping("/callback")
	public ModelAndView callback(@RequestParam("code") String authToken, Model model) {
		Verifier verifier = new Verifier(authToken);
		Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
		
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.github.com/user");
		service.signRequest(accessToken, request);
		Response response = request.send();
		
		Usuario usuario = new Usuario();
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode myObject = (ObjectNode) mapper.readTree(response.getBody());
			usuario.setLogin(myObject.get("login").asText()); 
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OAuthRequest requestOrg = new OAuthRequest(Verb.GET, "https://api.github.com/orgs/caelum/members/"+usuario.getLogin());
		service.signRequest(accessToken, requestOrg);
		Response responseOrg = requestOrg.send();		 
		

		if(responseOrg.getCode() == 204){
			return new ModelAndView(new RedirectView("/GeradorDeProvas/oauth/github-logado"));
		}
		return new ModelAndView(new RedirectView("/GeradorDeProvas/oauth/github-error"));
	} 

	@RequestMapping("/github-logado")
	public ModelAndView logado(HttpSession sessao) {
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