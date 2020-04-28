package br.com.joserlj.catalogo.service;

import java.util.List;

import br.com.joserlj.catalogo.domain.Musica;

public interface MusicaService {
	void salvar(Musica musica, long catalogoId);

	List<Musica> recuperarPorCatalogo(long catalogoId);

	public Musica recuperarPorCatalogoIdEMusicaId(long catalogoId, long musicaId);

	void atualizar(Musica musica, long catalogoId);

	void excluir(long catalogoId, long musicaId);
}
