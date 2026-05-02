package br.com.daniel.dl_wallet.service;

import br.com.daniel.dl_wallet.database.model.TransacaoEntity;
import br.com.daniel.dl_wallet.database.model.UsuarioEntity;
import br.com.daniel.dl_wallet.database.repository.ITransacaoRepository;
import br.com.daniel.dl_wallet.database.repository.IUsuarioRepository;
import br.com.daniel.dl_wallet.dto.TransacaoRequestDTO;
import br.com.daniel.dl_wallet.enums.TipoTransacao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {
    private final ITransacaoRepository transacaoRepository;
    private final IUsuarioRepository usuarioRepository;

    @Transactional
    public TransacaoEntity salvar(TransacaoRequestDTO dto){
        UsuarioEntity usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

        TransacaoEntity transacao = TransacaoEntity.builder()
                .descricao(dto.getDescricao())
                .valor(dto.getValor())
                .tipo(dto.getTipoTransacao())
                .data(LocalDate.now())
                .usuario(usuario)
                .build();

        return transacaoRepository.save(transacao);
    }

    public List<TransacaoEntity> listarPorUsuario(Long usuarioId){
        return transacaoRepository.findAllByUsuarioId(usuarioId);
    }

    public BigDecimal calcularSaldo(Long usuarioId){
        List<TransacaoEntity> transacoes = transacaoRepository.findAllByUsuarioId(usuarioId);

        return transacoes.stream()
                .map(t-> t.getTipo() == TipoTransacao.ENTRADA ? t.getValor() : t.getValor().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
