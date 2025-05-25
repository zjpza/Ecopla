package com.ecopla.ecopla.repository;

import com.ecopla.ecopla.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
    List<Pedido> findByUserId(String userId);
}