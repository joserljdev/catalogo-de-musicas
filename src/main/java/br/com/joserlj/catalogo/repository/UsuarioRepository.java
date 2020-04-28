package br.com.joserlj.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joserlj.catalogo.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	Usuario findByEmail(String email);
}