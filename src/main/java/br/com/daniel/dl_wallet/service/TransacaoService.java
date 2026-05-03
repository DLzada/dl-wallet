package br.com.daniel.dl_wallet.service;

import br.com.daniel.dl_wallet.database.model.TransacaoEntity;
import br.com.daniel.dl_wallet.database.model.UsuarioEntity;
import br.com.daniel.dl_wallet.database.repository.ITransacaoRepository;
import br.com.daniel.dl_wallet.database.repository.IUsuarioRepository;
import br.com.daniel.dl_wallet.dto.ExtratoResponseDTO;
import br.com.daniel.dl_wallet.dto.TransacaoRequestDTO;
import br.com.daniel.dl_wallet.enums.CategoriaTransacao;
import br.com.daniel.dl_wallet.enums.TipoTransacao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransacaoService {
    private final ITransacaoRepository transacaoRepository;
    private final IUsuarioRepository usuarioRepository;

    @Transactional
    public TransacaoEntity salvar(TransacaoRequestDTO dto){
        UsuarioEntity usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

        if (dto.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor da transação deve ser maior que zero.");
        }

        validarCategoria(dto);

        TransacaoEntity transacao = TransacaoEntity.builder()
                .descricao(dto.getDescricao())
                .valor(dto.getValor())
                .categoria(dto.getCategoria())
                .tipo(dto.getTipoTransacao())
                .data(LocalDate.now())
                .usuario(usuario)
                .build();

        return transacaoRepository.save(transacao);
    }

    private void validarCategoria(TransacaoRequestDTO dto){
        if(dto.getTipoTransacao() == TipoTransacao.ENTRADA){
            if(dto.getCategoria() != CategoriaTransacao.SALARIO && dto.getCategoria() != CategoriaTransacao.OUTROS){
                throw new RuntimeException("Categoria inválida para uma transação de Entrada!");
            }
        }else {
            if (dto.getCategoria() == CategoriaTransacao.SALARIO){
                throw new RuntimeException("Categoria inválida para um transação de Saida!");
            }
        }
    }

    public List<TransacaoEntity> listarPorUsuario(Long usuarioId){
        return transacaoRepository.findAllByUsuarioId(usuarioId);
    }

    public BigDecimal calcularSaldo(Long usuarioId){

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado com o ID: " + usuarioId);
        }

        List<TransacaoEntity> transacoes = transacaoRepository.findAllByUsuarioId(usuarioId);

        return transacoes.stream()
                .map(t-> t.getTipo() == TipoTransacao.ENTRADA ? t.getValor() : t.getValor().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<TransacaoEntity> buscarExtratoPorPeriodo(Long usuarioId, LocalDate inicio, LocalDate fim){
        if(!usuarioRepository.existsById(usuarioId)){
            throw new RuntimeException("Usuário nao encontrado");
        }

        if (inicio.isAfter(fim)) {
            throw new RuntimeException("A data de início não pode ser maior que a data de fim!");
        }

        return transacaoRepository.findAllByUsuarioIdAndDataBetween(usuarioId, inicio, fim);
    }

    public ExtratoResponseDTO buscarExtratoCompleto(Long usuarioId){
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new RuntimeException("Usuario nao encontrado"));

        List<TransacaoEntity> transacoes = transacaoRepository.findAllByUsuarioId(usuarioId);

        BigDecimal saldo = calcularSaldo(usuarioId);

        return ExtratoResponseDTO.builder()
                .nomeUsuario(usuario.getNome())
                .saldoAtual(saldo)
                .transacoes(transacoes)
                .build();

    }

    public Map<CategoriaTransacao, BigDecimal> obterResumoPorCategoria(Long usuarioId){
        if(!usuarioRepository.existsById(usuarioId)){
            throw new RuntimeException("O usuario nao foi encontrado!");
        }

        List<TransacaoEntity> transacoes = transacaoRepository.findAllByUsuarioId(usuarioId);

        return transacoes.stream()
                .collect(Collectors.groupingBy(
                        TransacaoEntity::getCategoria,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                TransacaoEntity::getValor,
                                BigDecimal::add
                        )
                ));
    }
}
