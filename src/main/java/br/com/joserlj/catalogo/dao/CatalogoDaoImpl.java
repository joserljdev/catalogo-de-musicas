package br.com.joserlj.catalogo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.joserlj.catalogo.domain.Catalogo;

@Repository
public class CatalogoDaoImpl implements CatalogoDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void salvar(Catalogo catalogo) {
		em.persist(catalogo);
	}

	@Override
	public List<Catalogo> recuperar() {
		return em.createQuery("select c from Catalogo c", Catalogo.class).getResultList();
	}
	
	@Override
	public Catalogo recuperarPorId(long id) {
		return em.find(Catalogo.class, id);
	}

	@Override
	public List<Catalogo> recuperarPorUsuario(long usuarioId) {
		return em.createQuery("select c from Catalogo c where c.usuario.id = :usuarioId", Catalogo.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
	}
	
	@Override
	public Catalogo recuperarPorCatalogoIdEUsuarioId(long catalogoId, long usuarioId) {
		return em.createQuery("select c from Catalogo c where c.usuario.id = :usuarioId and c.id = :catalogoId", Catalogo.class)
                .setParameter("catalogoId", catalogoId)
                .setParameter("usuarioId", usuarioId)
                .getSingleResult();		
	}

	@Override
	public void atualizar(Catalogo catalogo) {
		em.merge(catalogo);
	}

	@Override
	public void excluir(long catalogoId) {
		em.remove(em.getReference(Catalogo.class, catalogoId));
	}
}