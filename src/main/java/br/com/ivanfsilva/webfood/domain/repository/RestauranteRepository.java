package br.com.ivanfsilva.webfood.domain.repository;

import java.util.List;

import br.com.ivanfsilva.webfood.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante salvar(Restaurante restaurante);
	void remover(Restaurante restaurante);
	
}
