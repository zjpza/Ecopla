package com.ecopla.ecopla.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

@Document("produtos")
public class Produto {
    @Id
    private String id;

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private Double preco;

    private String material;
    private String descricao;
    private String cor;

    public Produto() {}

    public Produto(String id, String nome, Double preco, String material, String descricao, String cor) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.material = material;
        this.descricao = descricao;
        this.cor = cor;
    }

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
}