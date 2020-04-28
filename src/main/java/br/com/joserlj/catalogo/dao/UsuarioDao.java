package br.com.joserlj.catalogo.dao;

import java.util.List;

import br.com.joserlj.catalogo.domain.Usuario;

public interface UsuarioDao {
	void salvar(Usuario usuario);

	List<Usuario> recuperar();

	Usuario recuperarPorId(long id);

	void atualizar(Usuario usuario);

	void excluir(long id);
}