// src/main/java/com/ecopla/ecopla/model/CartItem.java
package com.ecopla.ecopla.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItem {
    private String productId;
    private String nome;
    private double preco;
    private int quantidade;

    public CartItem() {}

    public CartItem(String productId, int quantidade) {
        this.productId = productId;
        this.quantidade = quantidade;
    }

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    @JsonProperty("quantity")
    public int getQuantidade() {
                return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}