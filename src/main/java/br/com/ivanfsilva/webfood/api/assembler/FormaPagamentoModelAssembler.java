package br.com.ivanfsilva.webfood.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ivanfsilva.webfood.api.model.FormaPagamentoModel;
import br.com.ivanfsilva.webfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
    }
    
    public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> formasPagamentos) {
        return formasPagamentos.stream()
                .map(formaPagamento -> toModel(formaPagamento))
                .collect(Collectors.toList());
    }
    
}
