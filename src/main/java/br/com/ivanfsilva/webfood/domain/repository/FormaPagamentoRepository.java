package br.com.ivanfsilva.webfood.domain.repository;

import java.util.List;

import br.com.ivanfsilva.webfood.domain.model.Cidade;

public interface FormaPagamentoRepository {
	
	List<Cidade> listar();
	Cidade buscar(Long id);
	Cidade salvar(Cidade cidade);
	void remover(Cidade cidade);

}
