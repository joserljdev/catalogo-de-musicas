package br.com.joserlj.catalogo.service;

import java.util.List;

import br.com.joserlj.catalogo.domain.Usuario;

public interface UsuarioService {
	void salvar(Usuario usuario);

	List<Usuario> recuperar();

	Usuario recuperarPorId(long usuarioId);

	void atualizar(Usuario usuario);

	void excluir(long usuarioId);
}