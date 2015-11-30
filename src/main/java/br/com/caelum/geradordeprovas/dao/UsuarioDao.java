package br.com.caelum.geradordeprovas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.geradordeprovas.models.Prova;
import br.com.caelum.geradordeprovas.models.Questao;
import br.com.caelum.geradordeprovas.models.Usuario;
import br.com.caelum.geradordeprovas.util.Criptografia;

@Repository
public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Usuario usuario) {
		manager.persist(usuario);
	}

	public Usuario getUsuarioByLogin(String login) {
		Usuario usuario = (Usuario) manager
				.createQuery("select u from Usuario u where u.login =:login")
				.setParameter("login", login)
				.getSingleResult();
	
		return usuario;
	}
	

	public Usuario validaUsuario(Usuario usuario) {

		Usuario us = getUsuarioByLogin(usuario.getLogin());
		if (us != null) {
			Criptografia crypt = new Criptografia();
			String compara = crypt.criptografaSenha(usuario.getSenha());
			if (us.getSenha().equals(compara)) {
				return us;
			}
		}

		return null;
	}

	public boolean ehAdmin(Usuario usuario) {
		Usuario us = manager.find(Usuario.class, usuario.getLogin());
		return us.isAdmin();
	}

	public List<Usuario> list() {
		return manager.createQuery("from Usuario u", Usuario.class)
				.getResultList();
	}

	public void salvaProvasLiberadas(Long id, List<Prova> provas) {

		Usuario us = manager.find(Usuario.class, id);
		us.adicionaProvas(provas);
		
		
	}

	public List<Prova> getProvasDoUsuario(String login) {
		return manager
				.createQuery(
						"select p from provas p JOIN p.Usuario u where u.login =:login",Prova.class)
				.setParameter("login", login).getResultList();

	}

}
