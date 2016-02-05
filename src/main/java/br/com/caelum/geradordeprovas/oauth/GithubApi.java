package br.com.caelum.geradordeprovas.oauth;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;

public class GithubApi extends DefaultApi20 {
	
	@Override
	public String getAccessTokenEndpoint() {
		return "https://github.com/login/oauth/access_token";
	}

	
	@Override
	public String getAuthorizationUrl(OAuthConfig config) {
		// o param scope define o que queremos acessar
		return String.format("https://github.com/login/oauth/authorize?"
				+ "scope=user:email,read:org&client_id=%s", config.getApiKey());
	}

}
