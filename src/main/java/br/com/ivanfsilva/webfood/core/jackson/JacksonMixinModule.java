package br.com.ivanfsilva.webfood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.ivanfsilva.webfood.domain.model.Cidade;
import br.com.ivanfsilva.webfood.domain.model.Cozinha;
import br.com.ivanfsilva.webfood.domain.model.Restaurante;
import br.com.ivanfsilva.webfood.domain.model.mixin.CidadeMixin;
import br.com.ivanfsilva.webfood.domain.model.mixin.CozinhaMixin;
import br.com.ivanfsilva.webfood.domain.model.mixin.RestauranteMixin;

@Component
public class JacksonMixinModule extends SimpleModule{

	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
	}

}
