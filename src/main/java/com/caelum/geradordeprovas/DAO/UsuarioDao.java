package com.caelum.geradordeprovas.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.caelum.geradordeprovas.models.Usuario;

@Repository
public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Usuario usuario) {
		manager.persist(usuario);
	}

	public boolean existeUsuario(Usuario usuario) {

		if (usuario == null) {
			return false;
		}

		else {
			String login = new String(usuario.getLogin());
			Usuario us = manager.find(Usuario.class, login);
			if (us.getSenha().equals(usuario.getSenha())) {
				return true;
			}
		}
		return false;
	}

	public List<Usuario> list() {
		return manager.createQuery("from Usuario u", Usuario.class)
				.getResultList();
	}
}
