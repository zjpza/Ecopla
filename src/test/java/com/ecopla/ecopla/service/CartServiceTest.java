package com.ecopla.ecopla.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.ecopla.ecopla.model.Cart;
import com.ecopla.ecopla.model.CartItem;
import com.ecopla.ecopla.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    @Mock private CartRepository repo;
    @InjectMocks private CartService service;

    @Test void getCart_NewUser_ShouldReturnEmptyCart() {
        when(repo.findById("u")).thenReturn(Optional.empty());
        var cart = service.getCart("u");
        assertEquals("u", cart.getUserId());
        assertTrue(cart.getItems().isEmpty());
    }
    @Test void addItem_ShouldAddAndSave() {
        var cart = new Cart("u");
        when(repo.findById("u")).thenReturn(Optional.of(cart));
        when(repo.save(any())).thenAnswer(i->i.getArgument(0));
        var updated = service.addItem("u", new CartItem("p",2));
        assertFalse(updated.getItems().isEmpty());
    }
}