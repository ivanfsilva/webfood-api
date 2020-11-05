package br.com.ivanfsilva.webfood.domain.repository;

import java.util.List;

import br.com.ivanfsilva.webfood.domain.model.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao permissao);
	void remover(Permissao permissao);

}
