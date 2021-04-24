package br.com.ivanfsilva.webfood.domain.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.ivanfsilva.webfood.domain.model.Estado;

public abstract class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
    
}   
