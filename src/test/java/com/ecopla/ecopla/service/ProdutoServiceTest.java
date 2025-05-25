package com.ecopla.ecopla.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.eq;

import com.ecopla.ecopla.model.Produto;
import com.ecopla.ecopla.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repo;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private ProdutoService service;

    @Test
    void listarTodos_ShouldReturnList() {
        when(repo.findAll()).thenReturn(List.of(
                new Produto("1","Produto A",10.0,"Madeira","Desc","Azul")
        ));
        var list = service.listarTodos();
        assertEquals(1, list.size());
        assertEquals("Produto A", list.get(0).getNome());
    }

    @Test
    void buscarPorId_WhenExists_ShouldReturnProduto() {
        Produto p = new Produto("1","Produto A",10.0,"Madeira","Desc","Azul");
        when(repo.findById("1")).thenReturn(Optional.of(p));
        var found = service.buscarPorId("1");
        assertTrue(found.isPresent());
        assertEquals("Produto A", found.get().getNome());
    }

    @Test
    void buscarPorId_WhenNotExists_ShouldReturnEmpty() {
        when(repo.findById("2")).thenReturn(Optional.empty());
        var found = service.buscarPorId("2");
        assertTrue(found.isEmpty());
    }

    @Test
    void criar_ShouldSetNullIdAndSave() {
        Produto input = new Produto("X","Novo",5.0,"Metal","Desc","Vermelho");
        when(repo.save(any(Produto.class))).thenAnswer(inv -> {
            Produto arg = inv.getArgument(0);
            arg.setId("10");
            return arg;
        });
        var created = service.criar(input);
        assertEquals("10", created.getId());
        assertEquals("Novo", created.getNome());
    }

    @Test
    void atualizar_ShouldSaveWithId() {
        Produto input = new Produto(null,"Up",7.0,"Plástico","Desc","Verde");
        when(repo.save(any(Produto.class))).thenAnswer(inv -> inv.getArgument(0));
        var updated = service.atualizar("5", input);
        assertEquals("5", updated.getId());
        assertEquals("Up", updated.getNome());
    }

    @Test
    void deletar_ShouldInvokeRepository() {
        service.deletar("3");
        verify(repo).deleteById("3");
    }

    @Test
    void search_ShouldUseMongoTemplateAndReturnResults() {
        // given
        List<Produto> expected = List.of(
            new Produto("1", "Filtro", 20.0, "Madeira", "Descrição", "Vermelho")
        );
        when(mongoTemplate.find(any(Query.class), eq(Produto.class))).thenReturn(expected);

        // when
        List<Produto> result = service.search("Filtro", 10.0, 30.0, "Madeira", "Vermelho");

        // then
        assertEquals(expected, result);
        verify(mongoTemplate).find(any(Query.class), eq(Produto.class));
    }
}