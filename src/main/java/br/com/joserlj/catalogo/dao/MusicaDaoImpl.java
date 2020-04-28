package br.com.joserlj.catalogo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.joserlj.catalogo.domain.Musica;

@Repository
public class MusicaDaoImpl implements MusicaDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void salvar(Musica musica) {
		em.persist(musica);
	}

	@Override
	public List<Musica> recuperarPorCatalogo(long catalogoId) {
        return em.createQuery("select m from Musica m where m.catalogo.id = :catalogoId", Musica.class)
                .setParameter("catalogoId", catalogoId)
                .getResultList();
    }

	@Override
	public Musica recuperarPorCatalogoIdEMusicaId(long catalogoId, long musicaId) {
        return em.createQuery("select m from Musica m where m.catalogo.id = :catalogoId and m.id = :musicaId", Musica.class)
                .setParameter("catalogoId", catalogoId)
                .setParameter("musicaId", musicaId)
                .getSingleResult();
    }

	@Override
	public void atualizar(Musica musica) {
		em.merge(musica);
	}

	@Override
	public void excluir(long musicaId) {
		em.remove(em.getReference(Musica.class, musicaId));
	}
}
