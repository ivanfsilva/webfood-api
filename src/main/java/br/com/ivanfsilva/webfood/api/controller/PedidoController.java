package br.com.ivanfsilva.webfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivanfsilva.webfood.api.assembler.PedidoInputDisassembler;
import br.com.ivanfsilva.webfood.api.assembler.PedidoModelAssembler;
import br.com.ivanfsilva.webfood.api.assembler.PedidoResumoModelAssembler;
import br.com.ivanfsilva.webfood.api.model.PedidoModel;
import br.com.ivanfsilva.webfood.api.model.PedidoResumoModel;
import br.com.ivanfsilva.webfood.api.model.input.PedidoInput;
import br.com.ivanfsilva.webfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.ivanfsilva.webfood.domain.exception.NegocioException;
import br.com.ivanfsilva.webfood.domain.model.Pedido;
import br.com.ivanfsilva.webfood.domain.model.Usuario;
import br.com.ivanfsilva.webfood.domain.repository.PedidoRepository;
import br.com.ivanfsilva.webfood.domain.repository.filter.PedidoFilter;
import br.com.ivanfsilva.webfood.domain.service.EmissaoPedidoService;
import br.com.ivanfsilva.webfood.infrastrutucture.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	@GetMapping
	public List<PedidoResumoModel> pesquisar(PedidoFilter filtro) {
		List<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro));
		
		return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		
		return pedidoModelAssembler.toModel(pedido);
	}
}           
