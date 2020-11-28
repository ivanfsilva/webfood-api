package br.com.ivanfsilva.webfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ivanfsilva.webfood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}
