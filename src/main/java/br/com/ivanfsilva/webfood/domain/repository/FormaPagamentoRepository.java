package br.com.ivanfsilva.webfood.domain.repository;

import java.util.List;

import br.com.ivanfsilva.webfood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {
	
	List<FormaPagamento> listar();
	FormaPagamento buscar(Long id);
	FormaPagamento salvar(FormaPagamento formaPagamento);
	void remover(FormaPagamento formaPagamento);

}
