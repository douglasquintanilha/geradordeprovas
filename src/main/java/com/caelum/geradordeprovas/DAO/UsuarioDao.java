package com.caelum.geradordeprovas.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.caelum.geradordeprovas.models.Usuario;
import com.caelum.geradordeprovas.util.Criptografia;

@Repository
public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Usuario usuario) {
		manager.persist(usuario);
	}

	public boolean validaUsuario(Usuario usuario) {

		if (usuario == null) {
			return false;
		}

		else {
			Usuario us = manager.find(Usuario.class, usuario.getLogin());
			Criptografia crypt = new Criptografia();
			String compara = crypt.criptografaSenha(usuario.getSenha());
			if (us.getSenha().equals(compara)){
				return true;
			}
		}
		return false;
	}

	public boolean ehAdmin(Usuario usuario){
		Usuario us = manager.find(Usuario.class, usuario.getLogin());
		return us.isAdmin();
	}
	
	
	public List<Usuario> list() {
		return manager.createQuery("from Usuario u", Usuario.class)
				.getResultList();
	}
}
