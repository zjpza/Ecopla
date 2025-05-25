package com.ecopla.ecopla.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ecopla.ecopla.model.Produto;
import com.ecopla.ecopla.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private final ProdutoRepository repo;
    private final MongoTemplate mongoTemplate;

    public ProdutoService(ProdutoRepository repo, MongoTemplate mongoTemplate) {
        this.repo = repo;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Produto> listarTodos() {
        return repo.findAll();
    }

    public Optional<Produto> buscarPorId(String id) {
        return repo.findById(id);
    }

    public Produto criar(Produto p) {
        p.setId(null);
        return repo.save(p);
    }

    public Produto atualizar(String id, Produto p) {
        p.setId(id);
        return repo.save(p);
    }

    public void deletar(String id) {
        repo.deleteById(id);
    }

    public List<Produto> search(String nome,
                                Double minPrice,
                                Double maxPrice,
                                String material,
                                String cor) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        if (nome != null) {
            criteriaList.add(Criteria.where("nome").regex(".*" + nome + ".*", "i"));
        }
        if (minPrice != null) {
            criteriaList.add(Criteria.where("preco").gte(minPrice));
        }
        if (maxPrice != null) {
            criteriaList.add(Criteria.where("preco").lte(maxPrice));
        }
        if (material != null) {
            criteriaList.add(Criteria.where("material").regex(".*" + material + ".*", "i"));
        }
        if (cor != null) {
            criteriaList.add(Criteria.where("cor").regex(".*" + cor + ".*", "i"));
        }
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }
        return mongoTemplate.find(query, Produto.class);
    }
}