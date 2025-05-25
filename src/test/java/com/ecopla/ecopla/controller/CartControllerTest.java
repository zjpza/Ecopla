package com.ecopla.ecopla.controller;

import com.ecopla.ecopla.model.Cart;
import com.ecopla.ecopla.model.CartItem;
import com.ecopla.ecopla.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
@AutoConfigureMockMvc(addFilters = false)
class CartControllerTest {

    @Autowired private MockMvc mvc;
    @MockBean
    private CartService service;

    @Test
    void getCart_ShouldReturnCart() throws Exception {
        Cart cart = new Cart("user");
        cart.setItems(List.of(new CartItem("p", 3)));
        when(service.getCart("user")).thenReturn(cart);

        mvc.perform(get("/api/cart").principal(() -> "user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("user"))
                .andExpect(jsonPath("$.items[0].productId").value("p"));
    }

    @Test
    void addItem_ShouldReturnUpdatedCart() throws Exception {
        Cart cart = new Cart("user");
        cart.setItems(List.of(new CartItem("p", 2)));
        when(service.addItem(eq("user"), any(CartItem.class))).thenReturn(cart);

        mvc.perform(post("/api/cart/items")
                        .principal(() -> "user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\":\"p\",\"quantity\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].quantity").value(2));
    }

    @Test
    void removeItem_ShouldReturnUpdatedCart() throws Exception {
        Cart cart = new Cart("user");
        when(service.removeItem("user", "p")).thenReturn(cart);

        mvc.perform(delete("/api/cart/items/p").principal(() -> "user"))
                .andExpect(status().isOk());
    }

    @Test
    void clearCart_ShouldReturnNoContent() throws Exception {
        doNothing().when(service).clearCart("user");

        mvc.perform(delete("/api/cart").principal(() -> "user"))
                .andExpect(status().isNoContent());
    }
}