package br.com.daniel.dl_wallet.service;

import br.com.daniel.dl_wallet.database.model.TransacaoEntity;
import br.com.daniel.dl_wallet.database.model.UsuarioEntity;
import br.com.daniel.dl_wallet.database.repository.ITransacaoRepository;
import br.com.daniel.dl_wallet.database.repository.IUsuarioRepository;
import br.com.daniel.dl_wallet.dto.TransacaoRequestDTO;
import br.com.daniel.dl_wallet.enums.CategoriaTransacao;
import br.com.daniel.dl_wallet.enums.TipoTransacao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {
    @Mock
    private ITransacaoRepository transacaoRepository;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @Test
    @DisplayName("Deve salvar uma transação com sucesso!")
    void salvarSucesso(){
        Long usuarioId = 1L;

        TransacaoRequestDTO request = new TransacaoRequestDTO();
        request.setDescricao("Entrada Tests");
        request.setValor(new BigDecimal("100.00"));
        request.setTipoTransacao(TipoTransacao.ENTRADA);
        request.setCategoria(CategoriaTransacao.SALARIO);
        request.setUsuarioId(usuarioId);

        UsuarioEntity usuarioMock = new UsuarioEntity();
        usuarioMock.setId(usuarioId);
        usuarioMock.setNome("Daniel");

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuarioMock));
        when(transacaoRepository.save(any(TransacaoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TransacaoEntity resultado = transacaoService.salvar(request);

        assertNotNull(resultado, "O resultado nao deveria ser nulo");
        assertEquals("Entrada Tests", resultado.getDescricao());
        assertEquals(new BigDecimal("100.00"), resultado.getValor());
        assertEquals(usuarioId, resultado.getUsuario().getId());

        verify(transacaoRepository, times(1)).save(any(TransacaoEntity.class));
        verify(usuarioRepository, times(1)).findById(usuarioId);
    }
}
