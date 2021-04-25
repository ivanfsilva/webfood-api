package br.com.ivanfsilva.webfood.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.ivanfsilva.webfood.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

}
