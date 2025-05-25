package com.ecopla.ecopla.controller;

import com.ecopla.ecopla.model.Pedido;
import com.ecopla.ecopla.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(Authentication auth) {
        String userId = auth.getName();
        Pedido pedido = pedidoService.criarPedidoParaUsuario(userId);
        return ResponseEntity.status(201).body(pedido);
    }

    @GetMapping("/mine")
    public ResponseEntity<List<Pedido>> listarMeusPedidos(Authentication auth) {
        String userId = auth.getName();
        return ResponseEntity.ok(pedidoService.buscarPedidosDoUsuario(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable String id, Authentication auth) {
        try {
            Pedido pedido = pedidoService.buscarPorId(id);
            String userId = auth.getName();
            boolean isAdmin = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            if (!isAdmin && !pedido.getUserId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.buscarTodosPedidos());
    }
}