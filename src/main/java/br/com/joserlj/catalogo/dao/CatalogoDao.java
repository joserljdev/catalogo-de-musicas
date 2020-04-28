package br.com.joserlj.catalogo.dao;

import java.util.List;

import br.com.joserlj.catalogo.domain.Catalogo;

public interface CatalogoDao {
	
	void salvar(Catalogo catalogo);

	List<Catalogo> recuperar();
	
	List<Catalogo> recuperarPorUsuario(long usuarioId);

	Catalogo recuperarPorId(long id);

	void atualizar(Catalogo catalogo);

	void excluir(long id);

	Catalogo recuperarPorCatalogoIdEUsuarioId(long catalogoId, long usuarioId);
}
