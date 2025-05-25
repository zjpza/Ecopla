package com.ecopla.ecopla.service;

import java.util.List;
import java.util.Optional;
import com.ecopla.ecopla.model.Usuario;
import com.ecopla.ecopla.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {
    public final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // CRUD
    public List<Usuario> listarTodos() { return repo.findAll(); }
    public Optional<Usuario> buscarPorId(String id) { return repo.findById(id); }
    public Usuario atualizar(String id, Usuario u) {
        u.setId(id);
        return repo.save(u);
    }
    public void deletar(String id) { repo.deleteById(id); }

    public Usuario registerUser(Usuario u) {
        if (repo.existsByEmail(u.getEmail()))
            throw new IllegalArgumentException("Email já cadastrado");
        u.setSenha(encoder.encode(u.getSenha()));
        u.setRoles(List.of("ROLE_USER"));
        return repo.save(u);
    }

    public Usuario registerAdmin(Usuario u) {
        if (repo.existsByEmail(u.getEmail()))
            throw new IllegalArgumentException("Email já cadastrado");
        u.setSenha(encoder.encode(u.getSenha()));
        u.setRoles(List.of("ROLE_ADMIN"));
        return repo.save(u);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return User.builder()
                .username(u.getEmail())
                .password(u.getSenha())
                .authorities(u.getRoles().toArray(new String[0]))
                .build();
    }
}