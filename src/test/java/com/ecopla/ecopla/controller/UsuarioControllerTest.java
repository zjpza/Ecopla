package com.ecopla.ecopla.controller;

import com.ecopla.ecopla.model.Usuario;
import com.ecopla.ecopla.service.UsuarioService;
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

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)  // desativa filtros de segurança
class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioService service;

    @Test
    void listar_ShouldReturnListOfUsuarios() throws Exception {
        when(service.listarTodos()).thenReturn(List.of(
                new Usuario("1","nome","email",25,null,List.of("ROLE_USER"))
        ));

        mvc.perform(get("/api/usuarios")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].email").value("email"));
    }

    @Test
    void buscar_WhenExists_ShouldReturnUsuario() throws Exception {
        Usuario u = new Usuario("1","nome","email",25,null,List.of("ROLE_USER"));
        when(service.buscarPorId("1")).thenReturn(Optional.of(u));

        mvc.perform(get("/api/usuarios/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nome").value("nome"));
    }

    @Test
    void buscar_WhenNotFound_ShouldReturn404() throws Exception {
        when(service.buscarPorId("2")).thenReturn(Optional.empty());

        mvc.perform(get("/api/usuarios/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}