package br.com.joserlj.catalogo.dao;

import java.util.List;

import br.com.joserlj.catalogo.domain.Musica;

public interface MusicaDao {

	void salvar(Musica musica);

	List<Musica> recuperarPorCatalogo(long catalogoId);

	Musica recuperarPorCatalogoIdEMusicaId(long catalogoId, long musicaId);

	void atualizar(Musica musica);

	void excluir(long musicaId);
}
