// src/main/java/com/ecopla/ecopla/service/PedidoService.java
package com.ecopla.ecopla.service;

import com.ecopla.ecopla.model.CartItem;
import com.ecopla.ecopla.model.Pedido;
import com.ecopla.ecopla.model.PedidoItem;
import com.ecopla.ecopla.repository.PedidoRepository;
import com.ecopla.ecopla.service.CartService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final CartService cartService;

    public PedidoService(PedidoRepository pedidoRepo,
                         CartService cartService) {
        this.pedidoRepo  = pedidoRepo;
        this.cartService = cartService;
    }

    public Pedido criarPedidoParaUsuario(String userId) {
        List<CartItem> cartItems = cartService.getCartItems(userId);
        double total = cartItems.stream()
                .mapToDouble(ci -> ci.getPreco() * ci.getQuantidade())
                .sum();

        List<PedidoItem> pedidoItems = cartItems.stream()
                .map(ci -> new PedidoItem(ci.getProductId(), ci.getNome(), ci.getPreco(), ci.getQuantidade()))
                .toList();

        Pedido pedido = new Pedido();
        pedido.setUserId(userId);
        pedido.setItens(pedidoItems);
        pedido.setTotal(total);
        pedido.setStatus("PENDENTE");

        Pedido saved = pedidoRepo.save(pedido);
        cartService.clearCart(userId);
        return saved;
    }

    public List<Pedido> buscarPedidosDoUsuario(String userId) {
        return pedidoRepo.findByUserId(userId);
    }


    public List<Pedido> buscarTodosPedidos() {
        return pedidoRepo.findAll();
    }

    public Pedido buscarPorId(String id) {
        return pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}