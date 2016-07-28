package br.com.caelum.geradordeprovas.databuilder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.caelum.geradordeprovas.models.Avaliacao;
import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.util.Criptografia;

public class UsuarioBuilder {

	private Long id;

	private String login;

	private String senha;

	private boolean admin;

	private Set<Prova> provas = new HashSet<>();

	private Set<Avaliacao> avaliacoes = new HashSet<>();

	public Usuario geraUsuarioSemId() {
		Usuario usuario = new Usuario();

		usuario.setLogin(this.login);
		usuario.setSenha(this.senha);
		usuario.setAdmin(this.admin);

		return usuario;
	}

	public UsuarioBuilder comId(Long id) {
		this.id = id;
		return this;
	}

	public UsuarioBuilder comLogin(String login) {
		this.login = login;
		return this;
	}

	public UsuarioBuilder comSenha(String senha) {
		this.senha = new Criptografia().criptografaSenha(senha);
		return this;
	}

	public UsuarioBuilder comAdmin(boolean eh) {
		this.admin = eh;
		return this;
	}

	public UsuarioBuilder comProvas(Collection<Prova> provas) {
		this.provas.addAll(provas);
		return this;
	}

	public UsuarioBuilder comAvaliacoes(Collection<Avaliacao> avaliacoes) {
		this.avaliacoes.addAll(avaliacoes);
		return this;
	}

}
