package br.com.joserlj.catalogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.joserlj.catalogo.dao.UsuarioDao;
import br.com.joserlj.catalogo.domain.Usuario;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public void salvar(Usuario usuario) {
		usuarioDao.salvar(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> recuperar() {
		return usuarioDao.recuperar();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario recuperarPorId(long usuarioId) {
		return usuarioDao.recuperarPorId(usuarioId);
	}

	@Override
	public void atualizar(Usuario usuario) {
		usuarioDao.atualizar(usuario);
	}

	@Override
	public void excluir(long usuarioId) {
		usuarioDao.excluir(usuarioId);
	}
}