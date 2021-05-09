package br.com.ivanfsilva.webfood.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.ivanfsilva.webfood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}  
