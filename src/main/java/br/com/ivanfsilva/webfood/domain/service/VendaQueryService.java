package br.com.ivanfsilva.webfood.domain.service;

import java.util.List;

import br.com.ivanfsilva.webfood.domain.filter.VendaDiariaFilter;
import br.com.ivanfsilva.webfood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {
	
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);

}
