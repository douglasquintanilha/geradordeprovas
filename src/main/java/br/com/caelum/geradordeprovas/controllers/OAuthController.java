package br.com.caelum.geradordeprovas.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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
import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Usuario;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Controller
@RequestMapping("/oauth")
public class OAuthController {

	@Autowired
	private OAuthService service;
	private Token EMPTY_TOKEN = null;

	@Autowired
	private Constantes constantes;

	@Autowired
	private UsuarioDao usuarioDao;

	@RequestMapping("/github-login")
	public String redirectToGithub() {
		final Token EMPTY_TOKEN = null;
		return "redirect:" + service.getAuthorizationUrl(EMPTY_TOKEN);
	}

	@RequestMapping("/callback")
	public ModelAndView callback(@RequestParam("code") String authToken, Model model, HttpSession sessao)
			throws IOException {
		Verifier verifier = new Verifier(authToken);
		Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);

		OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.github.com/user");
		service.signRequest(accessToken, request);
		Response response = request.send();

		Usuario usuario = new Usuario();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode myObject = (ObjectNode) mapper.readTree(response.getBody());
		usuario.setLogin(myObject.get("login").asText());

		OAuthRequest requestOrg = new OAuthRequest(Verb.GET, "https://api.github.com/orgs/caelum/members/"
				+ usuario.getLogin());
		service.signRequest(accessToken, requestOrg);
		Response responseOrg = requestOrg.send();

		OAuthRequest requestEmail = new OAuthRequest(Verb.GET, "https://api.github.com/user/emails");
		service.signRequest(accessToken, requestEmail);
		Response responseEmail = requestEmail.send();

		String emails = responseEmail.getBody();
		JsonParser parser = new JsonParser();
		Object obj = parser.parse(emails);
		JsonArray arrayEmails = (JsonArray) obj;
		String login = arrayEmails.get(0).getAsJsonObject().get("email").toString().replace("\"", "");
		usuario.setLogin(login);
		sessao.setAttribute("usuario", usuario);

		if (responseOrg.getCode() == 204) {
			sessao.setAttribute("caelumOrg", true);
			return new ModelAndView("redirect:/oauth/github-logado");
		} else {
			if (responseOrg.getCode() == 302 || responseOrg.getCode() == 404) {
				sessao.setAttribute("caelumOrg", false);
				return new ModelAndView("redirect:/oauth/github-logado");
			}
		}
		return new ModelAndView(new RedirectView("github-error"));
	}

	@Transactional
	@RequestMapping("/github-logado")
	public ModelAndView logado(HttpSession sessao) {
		Usuario usuario = (Usuario) sessao.getAttribute("usuario");
		boolean caelumOrg = (boolean) sessao.getAttribute("caelumOrg");
		usuario = usuarioDao.usuarioDoGithub(usuario, caelumOrg);
		sessao.setAttribute("usuario", usuario);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/github-error")
	public ModelAndView logado() {
		ModelAndView mv = new ModelAndView("login-error");
		return mv;
	}

	@RequestMapping("/google-login")
	public String redirectGoogle() {
		String apiKey = "120353188813-r7gukk2sa6d01db99utmhq2v8dt99kcp.apps.googleusercontent.com";
		String apiSecret = "UZFA6qGwYVAjgPTan1aZWBAg";
		String callbackUrl = "http://localhost:8000/GeradorDeProvas/oauth/callback-google/";

		OAuth20Service oauthService = new ServiceBuilder().apiKey(apiKey).apiSecret(apiSecret).callback(callbackUrl)
				.scope("profile").build(GoogleApi20.instance());

		String authUrl = oauthService.getAuthorizationUrl();
		return "redirect:" + authUrl;
	}

	@RequestMapping("/callback-google")
	public ModelAndView callbackGoogle(@RequestParam("code") String authToken) {
		String apiKey = "120353188813-r7gukk2sa6d01db99utmhq2v8dt99kcp.apps.googleusercontent.com";
		String apiSecret = "UZFA6qGwYVAjgPTan1aZWBAg";
		String callbackUrl = "http://localhost:8000/GeradorDeProvas/oauth/callback-google/";

		OAuth20Service oauthService = new ServiceBuilder().apiKey(apiKey).apiSecret(apiSecret).callback(callbackUrl)
				.scope("profile").build(GoogleApi20.instance());

		System.out.println("Consegui o AuthToken:" + authToken);
		OAuth2AccessToken accessToken = oauthService.getAccessToken(authToken);

		System.out.println(accessToken);

		ModelAndView mv = new ModelAndView("login-error");
		return mv;
	}
}