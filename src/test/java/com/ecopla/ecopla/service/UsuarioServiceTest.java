package com.ecopla.ecopla.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ecopla.ecopla.model.Usuario;
import com.ecopla.ecopla.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repo;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UsuarioService service;

    @Test
    void registerUser_ShouldEncodePasswordAndSave() {
        Usuario u = new Usuario(null, "nome", "email@ex.com", 20, "rawPwd", null);
        when(repo.existsByEmail(u.getEmail())).thenReturn(false);
        when(encoder.encode("rawPwd")).thenReturn("encPwd");
        when(repo.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));

        Usuario saved = service.registerUser(u);

        assertEquals("encPwd", saved.getSenha());
        assertTrue(saved.getRoles().contains("ROLE_USER"));
        verify(repo).save(saved);
    }

    @Test
    void registerUser_WhenEmailExists_ShouldThrow() {
        Usuario u = new Usuario(null, "nome", "email@ex.com", 20, "pwd", null);
        when(repo.existsByEmail(u.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class,
                () -> service.registerUser(u));
        verify(repo, never()).save(any());
    }

    @Test
    void loadUserByUsername_WhenFound_ShouldReturnUserDetails() {
        Usuario u = new Usuario("1","nome","email@ex.com",20,"pwd", List.of("ROLE_USER"));
        when(repo.findByEmail("email@ex.com")).thenReturn(Optional.of(u));

        UserDetails userDetails = service.loadUserByUsername("email@ex.com");

        assertEquals("email@ex.com", userDetails.getUsername());
        assertEquals("pwd", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void loadUserByUsername_WhenNotFound_ShouldThrow() {
        when(repo.findByEmail("nãoexiste")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> service.loadUserByUsername("nãoexiste"));
    }
}