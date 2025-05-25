package com.ecopla.ecopla.controller;

import com.ecopla.ecopla.model.Produto;
import com.ecopla.ecopla.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProdutoService service;

    @Test
    void listar_ShouldReturnListOfProdutos() throws Exception {
        when(service.listarTodos()).thenReturn(List.of(
                new Produto("1","A",10.0,"Madeira","Desc","Azul")
        ));

        mvc.perform(get("/api/produtos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].nome").value("A"));
    }

    @Test
    void buscar_WhenExists_ShouldReturnProduto() throws Exception {
        Produto p = new Produto("1","A",10.0,"Madeira","Desc","Azul");
        when(service.buscarPorId("1")).thenReturn(Optional.of(p));

        mvc.perform(get("/api/produtos/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("A"));
    }

    @Test
    void buscar_WhenNotFound_ShouldReturn404() throws Exception {
        when(service.buscarPorId("2")).thenReturn(Optional.empty());

        mvc.perform(get("/api/produtos/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void criar_ShouldReturnCreated() throws Exception {
        Produto saved = new Produto("9","A",5.0,"Madeira","Desc","Azul");
        when(service.criar(any(Produto.class))).thenReturn(saved);

        mvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"A\",\"preco\":5.0,\"material\":\"Madeira\",\"descricao\":\"Desc\",\"cor\":\"Azul\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("9"));
    }

    @Test
    void atualizar_WhenExists_ShouldReturnOk() throws Exception {
        Produto p = new Produto("1","A",5.0,"Madeira","Desc","Azul");
        when(service.buscarPorId("1")).thenReturn(Optional.of(p));
        when(service.atualizar(eq("1"), any(Produto.class))).thenReturn(p);

        mvc.perform(put("/api/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"A\",\"preco\":5.0,\"material\":\"Madeira\",\"descricao\":\"Desc\",\"cor\":\"Azul\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void deletar_WhenExists_ShouldReturnNoContent() throws Exception {
        Produto p = new Produto("1","A",10.0,"Madeira","Desc","Azul");
        when(service.buscarPorId("1")).thenReturn(Optional.of(p));

        mvc.perform(delete("/api/produtos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletar_WhenNotFound_ShouldReturn404() throws Exception {
        when(service.buscarPorId("2")).thenReturn(Optional.empty());

        mvc.perform(delete("/api/produtos/2"))
                .andExpect(status().isNotFound());
    }


    @Test
    void search_WithFilters_ShouldReturnFilteredProdutos() throws Exception {
        List<Produto> filtered = List.of(
                new Produto("1", "A", 10.0, "Madeira", "Desc", "Azul")
        );
        when(service.search(
                eq("A"),
                eq(5.0),
                eq(15.0),
                eq("Madeira"),
                eq("Azul")
        )).thenReturn(filtered);

        mvc.perform(get("/api/produtos/search")
                        .param("nome", "A")
                        .param("minPrice", "5.0")
                        .param("maxPrice", "15.0")
                        .param("material", "Madeira")
                        .param("cor", "Azul")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].nome").value("A"));
    }
}