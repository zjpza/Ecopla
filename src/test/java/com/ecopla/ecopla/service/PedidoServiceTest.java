package com.ecopla.ecopla.service;

import com.ecopla.ecopla.model.CartItem;
import com.ecopla.ecopla.model.Pedido;
import com.ecopla.ecopla.model.PedidoItem;
import com.ecopla.ecopla.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.util.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepo;

    @Mock
    private CartService cartService;

    @InjectMocks
    private PedidoService pedidoService;

    private final String USER_ID = "user123";

    @BeforeEach
    void setup() {
        // nada a fazer aqui
    }

    @Test
    void criarPedidoParaUsuario_deveSalvarPedidoComItensETotalELimparCarrinho() {
        // mock dos itens do carrinho
        CartItem ci1 = mock(CartItem.class);
        when(ci1.getProductId()).thenReturn("p1");
        when(ci1.getNome()).thenReturn("Produto A");
        when(ci1.getPreco()).thenReturn(10.0);
        when(ci1.getQuantidade()).thenReturn(2);

        CartItem ci2 = mock(CartItem.class);
        when(ci2.getProductId()).thenReturn("p2");
        when(ci2.getNome()).thenReturn("Produto B");
        when(ci2.getPreco()).thenReturn(5.0);
        when(ci2.getQuantidade()).thenReturn(1);

        when(cartService.getCartItems(USER_ID)).thenReturn(List.of(ci1, ci2));

        // captura do Pedido que será salvo
        ArgumentCaptor<Pedido> captor = ArgumentCaptor.forClass(Pedido.class);
        when(pedidoRepo.save(captor.capture())).thenAnswer(inv -> inv.getArgument(0));

        // executa
        Pedido resultado = pedidoService.criarPedidoParaUsuario(USER_ID);

        // verifica total (2×10 + 1×5 = 25) e campos básicos
        assertThat(resultado.getUserId()).isEqualTo(USER_ID);
        assertThat(resultado.getTotal()).isEqualTo(25.0);
        assertThat(resultado.getStatus()).isEqualTo("PENDENTE");
        // verifica itens
        List<PedidoItem> itens = resultado.getItens();
        assertThat(itens).hasSize(2)
                .extracting(PedidoItem::getProductId)
                .containsExactlyInAnyOrder("p1", "p2");

        // garantiu limpeza do carrinho
        verify(cartService).clearCart(USER_ID);
    }

    @Test
    void buscarPedidosDoUsuario_deveRetornarListaDoRepo() {
        List<Pedido> mockList = List.of(new Pedido(), new Pedido());
        when(pedidoRepo.findByUserId(USER_ID)).thenReturn(mockList);

        List<Pedido> resultado = pedidoService.buscarPedidosDoUsuario(USER_ID);

        assertThat(resultado).isSameAs(mockList);
    }

    @Test
    void buscarTodosPedidos_deveChamarFindAll() {
        List<Pedido> mockList = List.of(new Pedido());
        when(pedidoRepo.findAll()).thenReturn(mockList);

        List<Pedido> resultado = pedidoService.buscarTodosPedidos();

        assertThat(resultado).isSameAs(mockList);
    }

    @Test
    void buscarPorId_quandoExistir_retornaPedido() {
        Pedido p = new Pedido();
        when(pedidoRepo.findById("42")).thenReturn(Optional.of(p));

        Pedido resultado = pedidoService.buscarPorId("42");

        assertThat(resultado).isSameAs(p);
    }

    @Test
    void buscarPorId_quandoNaoExistir_disparaRuntimeException() {
        when(pedidoRepo.findById("nope")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> pedidoService.buscarPorId("nope"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Pedido não encontrado");
    }
}