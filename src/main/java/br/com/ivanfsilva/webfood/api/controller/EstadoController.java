package br.com.ivanfsilva.webfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivanfsilva.webfood.api.assembler.EstadoInputDisassembler;
import br.com.ivanfsilva.webfood.api.assembler.EstadoModelAssembler;
import br.com.ivanfsilva.webfood.api.model.EstadoModel;
import br.com.ivanfsilva.webfood.api.model.input.EstadoInput;
import br.com.ivanfsilva.webfood.domain.model.Estado;
import br.com.ivanfsilva.webfood.domain.repository.EstadoRepository;
import br.com.ivanfsilva.webfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler; 
	
	@GetMapping
	public List<EstadoModel> listar() {
	    List<Estado> todosEstados = estadoRepository.findAll();
	    
	    return estadoModelAssembler.toCollectionModel(todosEstados);
	}

	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
	    Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
	    
	    return estadoModelAssembler.toModel(estado);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
	    Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
	    
	    estado = cadastroEstado.salvar(estado);
	    
	    return estadoModelAssembler.toModel(estado);
	}

	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId,
	        @RequestBody @Valid EstadoInput estadoInput) {
	    Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
	    
	    estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
	    
	    estadoAtual = cadastroEstado.salvar(estadoAtual);
	    
	    return estadoModelAssembler.toModel(estadoAtual);
	}       
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
	    cadastroEstado.excluir(estadoId);	
	}
	
}
