package br.com.joserlj.catalogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.joserlj.catalogo.dao.MusicaDao;
import br.com.joserlj.catalogo.domain.Musica;

@Service
@Transactional
public class MusicaServiceImpl implements MusicaService {

	@Autowired
	private MusicaDao musicaDao;

	@Autowired
	private CatalogoService catalogoService;

	@Override
	public void salvar(Musica musica, long catalogoId) {
		musica.setCatalogo(catalogoService.recuperarPorId(catalogoId));
		musicaDao.salvar(musica);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Musica> recuperarPorCatalogo(long catalogoId) {
		return musicaDao.recuperarPorCatalogo(catalogoId);
	}

	@Override
	@Transactional(readOnly = true)
	public Musica recuperarPorCatalogoIdEMusicaId(long catalogoId, long musicaId) {
		return musicaDao.recuperarPorCatalogoIdEMusicaId(catalogoId, musicaId);
	}

	@Override
	public void atualizar(Musica musica, long catalogoId) {
		musica.setCatalogo(catalogoService.recuperarPorId(catalogoId));
		musicaDao.atualizar(musica);
	}

	@Override
	public void excluir(long catalogoId, long musicaId) {
		musicaDao.excluir(recuperarPorCatalogoIdEMusicaId(catalogoId, musicaId).getId());
	}
}