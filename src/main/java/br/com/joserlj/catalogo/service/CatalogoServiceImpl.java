package br.com.joserlj.catalogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.joserlj.catalogo.dao.CatalogoDao;
import br.com.joserlj.catalogo.domain.Catalogo;

@Service
@Transactional
public class CatalogoServiceImpl implements CatalogoService {

	@Autowired
	private CatalogoDao catalogoDao;

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public void salvar(Catalogo catalogo, long usuarioId) {
		catalogo.setUsuario(usuarioService.recuperarPorId(usuarioId));
		catalogoDao.salvar(catalogo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Catalogo> recuperar() {
		return catalogoDao.recuperar();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Catalogo> recuperarPorUsuario(long usuarioId) {
		return catalogoDao.recuperarPorUsuario(usuarioId);
	}
		
	@Override
	@Transactional(readOnly = true)
	public Catalogo recuperarPorId(long id) {
		return catalogoDao.recuperarPorId(id);
	}

	@Override
	public void atualizar(Catalogo catalogo, long usuarioId) {
		catalogo.setUsuario(usuarioService.recuperarPorId(usuarioId));
		catalogoDao.atualizar(catalogo);
	}

	@Override
	public void excluir(long usuarioId, long catalogoId) {
		catalogoDao.excluir(recuperarPorCatalogoIdEUsuarioId(catalogoId, usuarioId).getId());
	}

	@Override
	@Transactional(readOnly = true)
	public Catalogo recuperarPorCatalogoIdEUsuarioId(long catalogoId, long usuarioId) {
		return catalogoDao.recuperarPorCatalogoIdEUsuarioId(catalogoId, usuarioId);
	}
}