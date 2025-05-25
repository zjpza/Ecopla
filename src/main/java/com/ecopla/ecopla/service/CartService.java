package com.ecopla.ecopla.service;

import com.ecopla.ecopla.model.Cart;
import com.ecopla.ecopla.model.CartItem;
import com.ecopla.ecopla.repository.CartRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class CartService {
    private final CartRepository repo;
    public CartService(CartRepository repo) { this.repo = repo; }
    public Cart getCart(String userId) {
        return repo.findById(userId).orElseGet(() -> new Cart(userId));
    }
    public Cart addItem(String userId, CartItem item) {
        Cart cart = getCart(userId);
        cart.getItems().removeIf(ci -> ci.getProductId().equals(item.getProductId()));
        cart.getItems().add(item);
        return repo.save(cart);
    }
    public Cart updateItem(String userId, CartItem item) { return addItem(userId, item); }
    public Cart removeItem(String userId, String productId) {
        Cart cart = getCart(userId);
        cart.getItems().removeIf(ci -> ci.getProductId().equals(productId));
        return repo.save(cart);
    }
    public void clearCart(String userId) { repo.deleteById(userId); }

    public List<CartItem> getCartItems(String userId) {
        return getCart(userId).getItems();
    }
}