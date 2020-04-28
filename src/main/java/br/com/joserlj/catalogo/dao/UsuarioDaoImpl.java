package br.com.joserlj.catalogo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.joserlj.catalogo.domain.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void salvar(Usuario usuario) {
		em.persist(usuario);
	}

	@Override
	public List<Usuario> recuperar() {
		return em.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}

	@Override
	public Usuario recuperarPorId(long usuarioId) {
		return em.find(Usuario.class, usuarioId);
	}

	@Override
	public void atualizar(Usuario usuario) {
		em.merge(usuario);
	}

	@Override
	public void excluir(long usuarioId) {
		em.remove(em.getReference(Usuario.class, usuarioId));
	}
}