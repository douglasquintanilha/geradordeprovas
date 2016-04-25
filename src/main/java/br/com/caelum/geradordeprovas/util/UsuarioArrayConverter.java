package br.com.caelum.geradordeprovas.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Usuario;

public class UsuarioArrayConverter implements Converter<String[],Set<Usuario>>{

	private UsuarioDao usuarioDao;

	public UsuarioArrayConverter(UsuarioDao usuarioDao){
		this.usuarioDao = usuarioDao;
	}
	
	@Override
	public Set<Usuario> convert(String[] usuariosId) {
	
		Set<Usuario> usuariosSet = new HashSet<>();
		for(String usuarioId : usuariosId){
			Usuario usuario = usuarioDao.find(Long.parseLong(usuarioId));
			usuariosSet.add(usuario);
		}
		
		return usuariosSet;
		
	}
}
