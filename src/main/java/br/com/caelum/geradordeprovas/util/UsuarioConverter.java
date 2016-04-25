package br.com.caelum.geradordeprovas.util;

import org.springframework.core.convert.converter.Converter;

import br.com.caelum.geradordeprovas.dao.UsuarioDao;
import br.com.caelum.geradordeprovas.models.Usuario;

public class UsuarioConverter implements Converter<String, Usuario>{

	private UsuarioDao usuarioDao;

	public UsuarioConverter(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	@Override
	public Usuario convert(String usuarioId) {
		return usuarioDao.find(Long.parseLong(usuarioId));
	}

}
