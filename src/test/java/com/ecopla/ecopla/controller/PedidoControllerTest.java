package com.ecopla.ecopla.controller;

import com.ecopla.ecopla.model.Pedido;
import com.ecopla.ecopla.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@AutoConfigureMockMvc
@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    void listar_deveRetornar200ComLista() throws Exception {
        List<Pedido> lista = List.of(new Pedido(), new Pedido());
        when(pedidoService.buscarPedidosDoUsuario("user1")).thenReturn(lista);

        mvc.perform(get("/api/pedidos/mine").with(user("user1")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void buscarPorId_quandoEncontrar_retorna200() throws Exception {
        Pedido p = new Pedido();
        p.setId("123");
        p.setUserId("user1");
        when(pedidoService.buscarPorId("123")).thenReturn(p);
        mvc.perform(get("/api/pedidos/123").with(user("user1")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"));
    }

    @Test
    void buscarPorId_quandoNaoEncontrar_retorna404() throws Exception {
        when(pedidoService.buscarPorId("nope"))
                .thenThrow(new RuntimeException("Pedido não encontrado"));
        mvc.perform(get("/api/pedidos/nope").with(user("user1")))
                .andExpect(status().isNotFound());
    }

    @Test
    void criar_deveRetornar201ComBody() throws Exception {
        Pedido salvo = new Pedido();
        salvo.setId("xyz");
        salvo.setUserId("user1");
        when(pedidoService.criarPedidoParaUsuario("user1")).thenReturn(salvo);
        mvc.perform(post("/api/pedidos").with(user("user1")).with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("xyz"));
    }
}