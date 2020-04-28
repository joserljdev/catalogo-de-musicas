package br.com.joserlj.catalogo.service;

import java.util.List;

import br.com.joserlj.catalogo.domain.Catalogo;

public interface CatalogoService {
	void salvar(Catalogo catalogo, long id);

	List<Catalogo> recuperar();

	Catalogo recuperarPorId(long id);
	
	Catalogo recuperarPorCatalogoIdEUsuarioId(long catalogoId, long usuarioId);
	
	List<Catalogo> recuperarPorUsuario(long id);

	void atualizar(Catalogo catalogo, long id);

	void excluir(long usuarioId, long catalogoId);
}