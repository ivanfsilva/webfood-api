package br.com.ivanfsilva.webfood.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.ivanfsilva.webfood.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteModel {
	
	@JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
	private Long id;
	
	@JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
	private String nome;
	
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;
	
	private Boolean ativo;
	private EnderecoModel endereco;
	private Boolean aberto;

}
