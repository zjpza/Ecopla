package com.ecopla.ecopla.controller;

import com.ecopla.ecopla.model.Cart;
import com.ecopla.ecopla.model.CartItem;
import com.ecopla.ecopla.service.CartService;
import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(Principal principal) {
        return ResponseEntity.ok(service.getCart(principal.getName()));
    }

    @PostMapping("/items")
    public ResponseEntity<Cart> addItem(Principal principal,
                                        @RequestBody CartItem item) {
        return ResponseEntity.ok(service.addItem(principal.getName(), item));
    }

    @PutMapping("/items")
    public ResponseEntity<Cart> updateItem(Principal principal,
                                           @RequestBody CartItem item) {
        return ResponseEntity.ok(service.updateItem(principal.getName(), item));
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Cart> removeItem(Principal principal,
                                           @PathVariable String productId) {
        return ResponseEntity.ok(service.removeItem(principal.getName(), productId));
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart(Principal principal) {
        service.clearCart(principal.getName());
        return ResponseEntity.noContent().build();
    }
}