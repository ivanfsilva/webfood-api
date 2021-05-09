package br.com.ivanfsilva.webfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivanfsilva.webfood.domain.exception.PedidoNaoEncontradoException;
import br.com.ivanfsilva.webfood.domain.model.Pedido;
import br.com.ivanfsilva.webfood.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    public Pedido buscarOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
            .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }            
}       
