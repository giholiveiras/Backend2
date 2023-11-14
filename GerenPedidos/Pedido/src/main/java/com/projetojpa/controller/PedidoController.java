package com.projetojpa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Pedido;
import com.projetojpa.services.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name="pedido", description = "API REST DE GERENCIAMENTO DE PEDIDOS")
@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins="*")

public class PedidoController {
	private final PedidoService PedidoService;

	@Autowired
	public PedidoController(PedidoService pedidoService) {
		this.PedidoService = pedidoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza pedido por iD")
	public ResponseEntity<Pedido> buscaPedidoControlId(@PathVariable Long id) {
		Pedido pedido = PedidoService.buscaPedidoId(id);
		if (pedido != null) {
			return ResponseEntity.ok(pedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	@Operation(summary = "Apresenta todos os pedidos")
	public ResponseEntity<List<Pedido>> buscaTodosPedidoControl() {
		List<Pedido> Pedido = PedidoService.buscaTodosPedidos();
		return ResponseEntity.ok(Pedido);
	}

	@PostMapping ("/")
	@Operation(summary = "Cadastra um Pedido")
	public ResponseEntity<Pedido> salvaPedidoControl (@RequestBody @Valid Pedido pedido){
		Pedido salvaPedido = PedidoService.salvarPedido(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaPedido);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Altera um Pedido")
	public ResponseEntity<Pedido> alteraPedidoControl (@PathVariable Long id, @RequestBody @Valid Pedido pedido){
		Pedido alteraPedido = PedidoService.alterarPedido(id, pedido);
		if (alteraPedido != null) {
			return ResponseEntity.ok(pedido);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("{id}")
	@Operation(summary = "Exclui um pedido")
	public ResponseEntity<Pedido> apagaPedidoControl(@PathVariable Long id){
		boolean apagar = PedidoService.apagarPedido(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
