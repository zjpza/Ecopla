package com.ecopla.ecopla.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document("usuarios")
public class Usuario {
    @Id
    private String id;
    private String nome;
    private String email;
    private Integer idade;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    private List<String> roles;

    public Usuario() {}

    public Usuario(String id, String nome, String email, Integer idade, String senha, List<String> roles) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.senha = senha;
        this.roles = roles;
    }

    // getters/setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}