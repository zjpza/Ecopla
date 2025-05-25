package com.ecopla.ecopla.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Document(collection = "pedidos")
public class Pedido {
    @Id
    private String id;
    private String userId;
    private Date data;
    private List<PedidoItem> itens;
    private double total;
    private String status;

    public Pedido() {}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public List<PedidoItem> getItens() {
        return itens;
    }
    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}